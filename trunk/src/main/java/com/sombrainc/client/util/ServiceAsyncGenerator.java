package com.sombrainc.client.util;

import static com.sombrainc.shared.util.URLConstants.PHONEGAP_MODULE_URL;
import static com.sombrainc.shared.util.URLConstants.REQUEST_SERVLET;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.googlecode.gwtphonegap.client.util.PhonegapUtil;
import com.sombrainc.client.service.Service;
import com.sombrainc.client.service.ServiceAsync;

public class ServiceAsyncGenerator {
	public static ServiceAsync createServiceAsync(boolean isPhoneGapDevice) {
		ServiceAsync service = GWT.create(Service.class);
		if (isPhoneGapDevice) {
			setServiceEntryPointForPhone((ServiceDefTarget) service);
		} else {
			setServiceEntryPointForWeb((ServiceDefTarget) service);
		}
		return service;
	}

	private static void setServiceEntryPointForPhone(ServiceDefTarget service) {
		PhonegapUtil.prepareService(service, PHONEGAP_MODULE_URL,
				REQUEST_SERVLET);
	}

	private static void setServiceEntryPointForWeb(ServiceDefTarget service) {
		service.setServiceEntryPoint(GWT.getHostPageBaseURL() + REQUEST_SERVLET);
	}
}
