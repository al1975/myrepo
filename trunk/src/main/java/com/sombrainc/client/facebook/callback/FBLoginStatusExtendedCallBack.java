package com.sombrainc.client.facebook.callback;

import com.sombrainc.shared.model.User;

public interface FBLoginStatusExtendedCallBack {
	void onSuccess(long facebookId);

	void onReconnected(User user);

	void onFailure(String error);
}
