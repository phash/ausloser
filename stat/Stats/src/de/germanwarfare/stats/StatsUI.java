package de.germanwarfare.stats;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("stats")
public class StatsUI extends UI {

	final VerticalLayout layout = new VerticalLayout();

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = StatsUI.class)
	public static class Servlet extends VaadinServlet {
	}

	DossierUploader dossierUploader = new DossierUploader();

	@Override
	protected void init(VaadinRequest request) {

		layout.setMargin(true);
		setContent(layout);
		layout.addComponent(new DossierUploader());

	}

}