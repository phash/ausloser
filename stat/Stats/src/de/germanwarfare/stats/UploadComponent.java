package de.germanwarfare.stats;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.VerticalLayout;

public abstract class UploadComponent extends VerticalLayout implements Upload.SucceededListener, Upload.FailedListener,
		Upload.Receiver, Upload.ProgressListener, Upload.FinishedListener {

	protected Upload upload;
	protected String directory;
	protected File file;
	protected long maxSize; // In bytes. 100Kb = 100000
	protected ProgressIndicator progressIndicator; // May be null
	protected boolean cancelled = false;
	protected Long contentLength;
	protected Button cancelProcessing;
	protected HorizontalLayout processingLayout;

	public UploadComponent(String fieldCaption, String buttonCaption, String directoryParam, int maxSize) {
		upload = new Upload(fieldCaption, null);
		this.addComponent(upload);
		this.maxSize = maxSize;
		upload.setReceiver(this);
		this.directory = directoryParam;
		upload.setButtonCaption(buttonCaption);
		upload.addListener((Upload.SucceededListener) this);
		upload.addListener((Upload.FailedListener) this);
		upload.addListener((Upload.ProgressListener) this);
		upload.addListener((Upload.FinishedListener) this);

		processingLayout = new HorizontalLayout();
		processingLayout.setSpacing(true);
		processingLayout.setVisible(false);
		this.addComponent(processingLayout);

		progressIndicator = new ProgressIndicator();
		progressIndicator.setWidth("100%");
		processingLayout.addComponent(progressIndicator);

		cancelProcessing = new Button("cancel", (ClickListener) event -> {
			cancelled = true;
			upload.interruptUpload();
		});
		cancelProcessing.setStyleName("small");
		processingLayout.addComponent(cancelProcessing);
	}

	@Override
	public OutputStream receiveUpload(String filename, String MIMEType) {
		FileOutputStream fos = null;

		file = new File(directory, filename);

		try {
			fos = new FileOutputStream(file);
		} catch (final java.io.FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		return fos; // Return the output stream to write to
	}

	@Override
	public void updateProgress(long readBytes, long contentLength) {
		this.contentLength = contentLength;
		if (maxSize < contentLength) {
			upload.interruptUpload();
			return;
		}

		processingLayout.setVisible(true);
		progressIndicator.setValue(new Float(readBytes / (float) contentLength));
	}

	@Override
	public void uploadFinished(FinishedEvent event) {
		processingLayout.setVisible(false);
	}

	@Override
	public void uploadFailed(FailedEvent event) {
	}

	/** Override me to do something more than displaying the notification */
	public void afterUploadFailed(FailedEvent event) {
	}

	public String getDirectory() {
		return directory;
	}

	public File getFile() {
		return file;
	}

}