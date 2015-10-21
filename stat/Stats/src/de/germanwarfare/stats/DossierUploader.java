package de.germanwarfare.stats;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedListener;
import com.vaadin.ui.Upload.FinishedListener;
import com.vaadin.ui.Upload.ProgressListener;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedListener;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class DossierUploader extends VerticalLayout {
	protected String directory = "c:\\temp";
	protected File file;
	// private Label fileName = new Label();
	private Label textualProgress = new Label();
	Panel p = new Panel("Status");
	private ProgressIndicator pi = new ProgressIndicator();

	private DataLoader counter = new DataLoader();

	private Upload upload = new Upload("", counter);

	public DossierUploader() {

		setSpacing(true);

		addComponent(new Label("Upload a file and we'll count the number of line break charaters found in it."));

		// make analyzing start immediatedly when file is selected
		upload.setImmediate(true);
		upload.setButtonCaption("Upload File");

		addComponent(upload);

		final Button cancelProcessing = new Button("Cancel processing");
		cancelProcessing.addListener((ClickListener) event -> upload.interruptUpload());
		cancelProcessing.setEnabled(false);
		cancelProcessing.setStyleName("small");
		addComponent(cancelProcessing);

		p.setSizeUndefined();
		FormLayout l = new FormLayout();
		l.setMargin(true);
		p.setContent(l);

		pi.setCaption("Progress");
		pi.setVisible(false);
		l.addComponent(pi);
		textualProgress.setVisible(false);
		l.addComponent(textualProgress);

		addComponent(p);

		upload.addStartedListener((StartedListener) event -> {
			// this method gets called immediatedly after upload is
			// started
			pi.setValue(0f);
			pi.setVisible(true);
			pi.setPollingInterval(500); // hit server frequantly to get
			textualProgress.setVisible(true);
			// updates to client

			cancelProcessing.setEnabled(true);
		});

		upload.addProgressListener((ProgressListener) (readBytes, contentLength) -> {
			// this method gets called several times during the update
			pi.setValue(new Float(readBytes / (float) contentLength));
			textualProgress.setValue("Processed " + readBytes + " bytes of " + contentLength);
		});

		upload.addSucceededListener((SucceededListener) event -> new Notification("Erfolgreich"));

		upload.addFailedListener((FailedListener) event -> new Notification("Fehlgeschlagen"));

		upload.addFinishedListener((FinishedListener) event -> {
			p.setVisible(false);
			pi.setVisible(false);
			textualProgress.setVisible(false);
			cancelProcessing.setEnabled(false);
			DataPublisher datapublisher = new DataPublisher();
			try {
				addComponent(datapublisher.getDataForUpload(file));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});

	}

	public class DataLoader implements Receiver {

		private String fileName;
		private String mtype;

		private int counter;
		private int total;
		private boolean sleep;

		/**
		 * return an OutputStream that simply counts lineends
		 */
		@Override
		public OutputStream receiveUpload(String filename, String MIMEType) {

			FileOutputStream fos = null;

			file = new File(directory, filename);

			try {
				fos = new FileOutputStream(file);
			} catch (final java.io.FileNotFoundException e) {
				throw new RuntimeException(e);
			}
			return fos;
		}

		public String getFileName() {
			return fileName;
		}

		public String getMimeType() {
			return mtype;
		}

		public int getLineBreakCount() {
			return counter;
		}

		public void setSlow(boolean value) {
			sleep = value;
		}

	}
}