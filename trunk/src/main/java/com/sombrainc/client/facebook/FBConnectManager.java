package com.sombrainc.client.facebook;

import com.sombrainc.client.facebook.callback.FBApiCallBack;
import com.sombrainc.client.facebook.callback.FBLoginCallback;
import com.sombrainc.client.facebook.callback.FBLoginStatusCallBack;
import com.sombrainc.client.facebook.callback.FBLogoutCallback;

public class FBConnectManager {

	public native static void login(FBLoginCallback fBLoginListener)/*-{
		if (!$wnd.cordova) {
			var appId = prompt("399214336898239", "");

			$wnd.facebookConnectPlugin.browserInit(appId);
		}
		$wnd.facebookConnectPlugin
				.login(
						[ "public_profile" ],
						function(response) {
							//alert('1:'+JSON.stringify(response));
							$wnd.facebookConnectPlugin
									.api(
											"me/?fields=id,name",
											[ "public_profile" ],
											function(response) {
												//alert('2:'+JSON.stringify(response));
												fBLoginListener.@com.sombrainc.client.facebook.callback.FBLoginCallback::onSuccess(Ljava/lang/String;Ljava/lang/String;)(response.id,response.name);
														function(response) {
															alert("api2 "
																	+ JSON
																			.stringify(response));
														}
											})
						}, function(response) {
							fBLoginListener.@com.sombrainc.client.facebook.callback.FBLoginCallback::onFailure(Ljava/lang/String;)("Connect cancel" + JSON.stringify(response));
						});
	}-*/;

	public native static void logout(FBLogoutCallback fBLogoutListener)/*-{
		$wnd.facebookConnectPlugin.logout(function(response) {
			fBLogoutListener.@com.sombrainc.client.facebook.callback.FBLogoutCallback::onSuccess(Ljava/lang/String;)("SuccessOut + " + JSON.stringify(response));
		}, function(response) {
			fBLogoutListener.@com.sombrainc.client.facebook.callback.FBLogoutCallback::onFailure(Ljava/lang/String;)(JSON.stringify(response));
		});
	}-*/;

	public native static void getLoginStatus(
			FBLoginStatusCallBack fBLoginStatusListener)/*-{
	$wnd.facebookConnectPlugin.getLoginStatus(function(response) {
		fBLoginStatusListener.@com.sombrainc.client.facebook.callback.FBLoginStatusCallBack::onSuccess(Ljava/lang/String;Ljava/lang/String;)(response.status,response.authResponse.userID);
	}, function(response) {
		fBLoginStatusListener.@com.sombrainc.client.facebook.callback.FBLoginStatusCallBack::onFailure(Ljava/lang/String;)(JSON.stringify(response));
	});
}-*/;

	public native static void api(FBApiCallBack fBApiCallBack)/*-{
	if (!$wnd.cordova) {
		var appId = prompt("399214336898239", "");
		$wnd.facebookConnectPlugin.browserInit(appId);
	}
	$wnd.facebookConnectPlugin.api("me/?fields=id,name",
			[ "public_profile" ], function(response) {
				fBApiCallBack.@com.sombrainc.client.facebook.callback.FBApiCallBack::onSuccess(Ljava/lang/String;Ljava/lang/String;)(response.id,response.name);
				function(
						response) {
							fBApiCallBack.@com.sombrainc.client.facebook.callback.FBApiCallBack::onFailure(Ljava/lang/String;)(JSON.stringify(response));					
				}
			});
}-*/;

	// ////////////////////////not used
	public native void showDialog()/*-{
		if (!$wnd.cordova) {
			var appId = prompt("399214336898239", "");
			$wnd.facebookConnectPlugin.browserInit(appId);
		}
		$wnd.facebookConnectPlugin.showDialog({
			method : "feed"
		}, function(response) {
			alert("showDialog1 " + JSON.stringify(response))
		}, function(response) {
			alert("showDialog2 " + JSON.stringify(response))
		});
	}-*/;

	public native void logPurchase()/*-{
		$wnd.facebookConnectPlugin.logPurchase(1.99, "USD", function(response) {
			alert("logPurchase1 " + JSON.stringify(response))
		}, function(response) {
			alert("logPurchase2 " + JSON.stringify(response))
		});
	}-*/;

	public native void logEvent()/*-{
		$wnd.facebookConnectPlugin.logEvent("Purchased", {
			NumItems : 1,
			Currency : "USD",
			ContentType : "shoes",
			ContentID : "HDFU-8452"
		}, null, function(response) {
			alert("logEvent1 " + JSON.stringify(response))
		}, function(response) {
			alert("logEvent2 " + JSON.stringify(response))
		});
	}-*/;

	public native void getAccessToken()/*-{
		facebookConnectPlugin.getAccessToken(function(token) {
			alert("Token: " + token);
		}, function(err) {
			alert("Could not get access token: " + err);
		});
	}-*/;

}
