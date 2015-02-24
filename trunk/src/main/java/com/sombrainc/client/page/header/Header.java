package com.sombrainc.client.page.header;

import static com.sombrainc.shared.util.URLConstants.FB_IMAGE_PATH_PREFIX;
import static com.sombrainc.shared.util.URLConstants.FB_IMAGE_PATH_SUFFIX;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.sombrainc.client.page.controller.header.HeaderController;
import com.sombrainc.client.service.ServiceAsync;
import com.sombrainc.shared.model.User;

public class Header extends Composite implements HeaderListener {
	private HeaderController headerController;

	interface HeaderUiBinder extends UiBinder<Widget, Header> {
	}

	private static HeaderUiBinder uiBinder = GWT.create(HeaderUiBinder.class);

	@UiField
	Label userName;
	@UiField
	Image userImage;
	@UiField
	Button reconnect;

	public Header(User user, PhoneGap phoneGap, ServiceAsync service) {
		initWidget(uiBinder.createAndBindUi(this));
		userImage.setUrl(FB_IMAGE_PATH_PREFIX + user.getFacebookId()
				+ FB_IMAGE_PATH_SUFFIX);
		userName.setText(user.getName());
		headerController = new HeaderController(phoneGap, service, this);
	}

	@UiHandler("reconnect")
	void reconnectOnClick(ClickEvent e) {
		headerController.reconnect();
	}

}
