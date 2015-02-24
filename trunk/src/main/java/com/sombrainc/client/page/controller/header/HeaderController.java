package com.sombrainc.client.page.controller.header;

import static com.sombrainc.client.util.PageManager.createHeader;

import com.google.gwt.user.client.Window;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.sombrainc.client.facebook.FBConnectManagerExtended;
import com.sombrainc.client.facebook.callback.FBReconnectExtendedCallBack;
import com.sombrainc.client.page.controller.common.AbstractController;
import com.sombrainc.client.page.header.HeaderListener;
import com.sombrainc.client.service.ServiceAsync;
import com.sombrainc.shared.model.User;

public class HeaderController extends AbstractController {
	// private final HeaderListener listener;

	public HeaderController(PhoneGap phoneGap, ServiceAsync service,
			HeaderListener listener) {
		super(phoneGap, service);
		// this.listener = listener;
	}

	public void reconnect() {
		FBConnectManagerExtended.connectOrReconnect(
				new FBReconnectExtendedCallBack() {

					@Override
					public void onSuccess(User user) {
						createHeader(user, phoneGap, service);
					}

					@Override
					public void onFailure(String error) {
						Window.alert(error);
					}
				}, service);
	}
}
