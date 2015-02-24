package com.sombrainc.client.facebook;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sombrainc.client.facebook.callback.FBLoginCallback;
import com.sombrainc.client.facebook.callback.FBLoginExtendedCallback;
import com.sombrainc.client.facebook.callback.FBLoginStatusCallBack;
import com.sombrainc.client.facebook.callback.FBLoginStatusExtendedCallBack;
import com.sombrainc.client.facebook.callback.FBLogoutCallback;
import com.sombrainc.client.facebook.callback.FBReconnectExtendedCallBack;
import com.sombrainc.client.service.ServiceAsync;
import com.sombrainc.shared.form.UserForm;
import com.sombrainc.shared.model.User;

public class FBConnectManagerExtended extends FBConnectManager {
	public static void login(
			final FBLoginExtendedCallback fBLoginExtendedListener,
			final ServiceAsync service) {
		login(new FBLoginCallback() {
			@Override
			public void onSuccess(final String facebook_id, final String name) {
				fBLoginExtendedListener.onSuccess(new User(Long
						.parseLong(facebook_id), name));
				service.insertOrUpdateUser(
						new UserForm(Long.parseLong(facebook_id), name),
						new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								fBLoginExtendedListener.onFailure(caught
										.getMessage());
							}

							@Override
							public void onSuccess(Void v) {
								fBLoginExtendedListener.onSuccess(new User(Long
										.parseLong(facebook_id), name));
							}
						});
			}

			@Override
			public void onFailure(String error) {
				fBLoginExtendedListener.onFailure(error);
			}
		});
	}

	public static void getLoginStatusExtended(
			final FBLoginStatusExtendedCallBack fBLoginStatusExtendedListener,
			final ServiceAsync service) {
		getLoginStatus(new FBLoginStatusCallBack() {

			@Override
			public void onSuccess(String status, String facebookId) {
				if ("connected".equals(status)) {
					fBLoginStatusExtendedListener.onSuccess(Long
							.parseLong(facebookId));
				} else {
					login(new FBLoginCallback() {
						@Override
						public void onSuccess(final String facebook_id,
								final String name) {
							service.insertOrUpdateUser(
									new UserForm(Long.parseLong(facebook_id),
											name), new AsyncCallback<Void>() {
										@Override
										public void onFailure(Throwable caught) {
											fBLoginStatusExtendedListener
													.onFailure(caught
															.getMessage());
										}

										@Override
										public void onSuccess(Void v) {
											fBLoginStatusExtendedListener.onReconnected(new User(
													Long.parseLong(facebook_id),
													name));
										}
									});

						}

						@Override
						public void onFailure(String error) {
							fBLoginStatusExtendedListener.onFailure(error);
						}
					});
				}
			}

			@Override
			public void onFailure(String error) {
				fBLoginStatusExtendedListener.onFailure(error);
			}
		});
	}

	public static void connectOrReconnect(
			final FBReconnectExtendedCallBack fBReconnectCallBack,
			final ServiceAsync service) {
		logout(new FBLogoutCallback() {
			@Override
			public void onSuccess(String message) {
				login(fBReconnectCallBack, service);
			}

			@Override
			public void onFailure(String error) {// there was not session
				login(fBReconnectCallBack, service);
			}
		});
	}

	private static void login(
			final FBReconnectExtendedCallBack fBReconnectExtendedCallBack,
			final ServiceAsync service) {
		login(new FBLoginCallback() {
			@Override
			public void onSuccess(final String facebook_id, final String name) {
				fBReconnectExtendedCallBack.onSuccess(new User(Long
						.parseLong(facebook_id), name));
				service.insertOrUpdateUser(
						new UserForm(Long.parseLong(facebook_id), name),
						new AsyncCallback<Void>() {
							@Override
							public void onFailure(Throwable caught) {
								fBReconnectExtendedCallBack.onFailure(caught
										.getMessage());
							}

							@Override
							public void onSuccess(Void v) {
								fBReconnectExtendedCallBack.onSuccess(new User(
										Long.parseLong(facebook_id), name));
							}
						});
			}

			@Override
			public void onFailure(String error) {
				fBReconnectExtendedCallBack.onFailure(error);
			}
		});
	}

}
