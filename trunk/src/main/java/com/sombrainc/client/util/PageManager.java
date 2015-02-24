package com.sombrainc.client.util;

import static com.sombrainc.client.page.enums.Div.HEADER;
import static com.sombrainc.client.page.enums.Div.MAIN;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.sombrainc.client.page.header.Header;
import com.sombrainc.client.service.ServiceAsync;
import com.sombrainc.shared.model.User;

public class PageManager {
	public static void createHeader(User user, PhoneGap phoneGap,
			ServiceAsync service) {
		RootPanel rootPanel = RootPanel.get(HEADER.panel());
		rootPanel.clear();
		rootPanel.add(new Header(user, phoneGap, service));
	}

	public static void toPage(Composite composite) {
		RootPanel rootPanel = RootPanel.get(MAIN.panel());
		rootPanel.clear();
		rootPanel.add(composite);
	}

	// public static void toPage(Page page) {
	// RootPanel rootPanel = RootPanel.get(MAIN.panel());
	// rootPanel.clear();
	// Composite composite = null;
	// switch (page) {
	// case WELCOME:
	// composite = new Welcome();
	// break;
	// case ADD_PRODUCT:
	// composite = new AddProduct();
	// break;
	// case LIST_PRODUCT:
	// composite = new ListProducts();
	// break;
	// case NULL:
	// return;
	// }
	// rootPanel.add(composite);
	// }
}
