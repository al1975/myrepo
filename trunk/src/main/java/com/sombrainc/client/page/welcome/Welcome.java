package com.sombrainc.client.page.welcome;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.sombrainc.client.page.controller.welcome.WelcomeController;
import com.sombrainc.client.service.ServiceAsync;

public class Welcome extends Composite implements WelcomeListener {
	private WelcomeController welcomeController;
	private static WelcomeUiBinder uiBinder = GWT.create(WelcomeUiBinder.class);
	@UiField
	VerticalPanel mainPanel;
	@UiField
	Button gotItButton;

	interface WelcomeUiBinder extends UiBinder<Widget, Welcome> {
	}

	public Welcome(PhoneGap phoneGap, ServiceAsync service) {
		initWidget(uiBinder.createAndBindUi(this));
		welcomeController = new WelcomeController(phoneGap, service);
		setMainPanelSize();
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				setMainPanelSize();
			}
		});
	}

	private void setMainPanelSize() {
		mainPanel
				.setPixelSize(Window.getClientWidth(), Window.getClientWidth());
	}

	@UiHandler("gotItButton")
	void gotItOnClick(ClickEvent e) {
		welcomeController.createAddPhotoPage();
	}
}
