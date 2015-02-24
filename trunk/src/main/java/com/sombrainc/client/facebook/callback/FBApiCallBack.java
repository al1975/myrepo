package com.sombrainc.client.facebook.callback;

public interface FBApiCallBack {
	void onSuccess(String facebookId, String name);

	void onFailure(String error);
}
