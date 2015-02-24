package com.sombrainc.client.facebook.callback;

public interface FBLoginCallback {
	void onSuccess(String facebook_id, String name);

	void onFailure(String error);
}
