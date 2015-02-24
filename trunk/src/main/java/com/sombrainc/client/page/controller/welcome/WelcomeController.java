package com.sombrainc.client.page.controller.welcome;

import static com.sombrainc.client.util.PageManager.toPage;

import com.googlecode.gwtphonegap.client.PhoneGap;
import com.sombrainc.client.page.addPhoto.AddPhoto;
import com.sombrainc.client.phonegap.PhonegapService;
import com.sombrainc.client.service.ServiceAsync;

public class WelcomeController extends PhonegapService {
	public WelcomeController(PhoneGap phoneGap, ServiceAsync service) {
		super(phoneGap, service);
	}

	public void createAddPhotoPage() {
		toPage(new AddPhoto(phoneGap, service));
	}
}
