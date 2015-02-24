package com.sombrainc.client.entry;

import static com.sombrainc.client.util.PageManager.createHeader;
import static com.sombrainc.client.util.PageManager.toPage;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableEvent;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableHandler;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutEvent;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutHandler;
import com.sombrainc.client.facebook.FBConnectManagerExtended;
import com.sombrainc.client.facebook.callback.FBReconnectExtendedCallBack;
import com.sombrainc.client.page.welcome.Welcome;
import com.sombrainc.client.service.ServiceAsync;
import com.sombrainc.client.util.ServiceAsyncGenerator;
import com.sombrainc.shared.model.User;

public class Main implements EntryPoint {
	public void onModuleLoad() {
		final PhoneGap phoneGap = GWT.create(PhoneGap.class);
		phoneGap.addHandler(new PhoneGapAvailableHandler() {
			@Override
			public void onPhoneGapAvailable(PhoneGapAvailableEvent event) {
				login(phoneGap, ServiceAsyncGenerator
						.createServiceAsync(phoneGap.isPhoneGapDevice()));
			}
		});
		phoneGap.addHandler(new PhoneGapTimeoutHandler() {
			@Override
			public void onPhoneGapTimeout(PhoneGapTimeoutEvent event) {
				Window.alert("Can not load phonegap");
			}
		});
		phoneGap.initializePhoneGap();

	}

	private void login(final PhoneGap phoneGap, final ServiceAsync service) {
		
//		FBConnectManagerExtended.connectOrReconnect(
//				new FBReconnectExtendedCallBack() {
//
//					@Override
//					public void onSuccess(User user) {
						//createHeader(new User(111l,"ggg"), phoneGap, service);
						toPage(new Welcome(phoneGap, service));
//					}
//
//					@Override
//					public void onFailure(String error) {
//						Window.alert(error);
//					}
//				}, service);
	}
}
