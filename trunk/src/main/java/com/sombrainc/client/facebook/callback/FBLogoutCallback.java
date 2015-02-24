package com.sombrainc.client.facebook.callback;

public interface FBLogoutCallback {
	void onSuccess(String message);

	void onFailure(String error);
}
