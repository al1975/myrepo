package com.sombrainc.client.facebook.callback;

import com.sombrainc.shared.model.User;

public interface FBReconnectExtendedCallBack {
	void onSuccess(User user);

	void onFailure(String error);
}
