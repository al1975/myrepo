package com.sombrainc.client.facebook.callback;

public interface FBLoginStatusCallBack {
	void onSuccess(String status, String facebookId);

	void onFailure(String error);
}
