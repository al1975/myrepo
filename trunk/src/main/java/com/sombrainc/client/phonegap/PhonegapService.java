package com.sombrainc.client.phonegap;

import com.google.gwt.core.client.GWT;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.sombrainc.client.localization.Messages;
import com.sombrainc.client.service.ServiceAsync;

public abstract class PhonegapService {
	protected final Messages messages = GWT.create(Messages.class);
	protected final PhoneGap phoneGap;
	protected final ServiceAsync service;
	protected PhonegapService(PhoneGap phoneGap, ServiceAsync service) {
		this.phoneGap = phoneGap;
		this.service = service;
	}
}
