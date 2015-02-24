package com.sombrainc.client.page.controller.common;

import com.googlecode.gwtphonegap.client.PhoneGap;
import com.sombrainc.client.phonegap.PhonegapService;
import com.sombrainc.client.service.ServiceAsync;

public abstract class AbstractController extends PhonegapService {
	protected AbstractController(PhoneGap phoneGap, ServiceAsync service) {
		super(phoneGap, service);
	}
}
