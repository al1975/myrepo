package com.sombrainc.client.page.header;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface LoginResources extends ClientBundle {
	/**
	 * Sample CssResource.
	 */
	public interface MyCss extends CssResource {
		String loginButton();
		
		String inComeCenter();
	}

	@Source("View.css")
	MyCss style();
}
