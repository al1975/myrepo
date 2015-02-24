package com.sombrainc.client.page.listproducts;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.sombrainc.client.page.common.AbstractPage;
import com.sombrainc.client.page.controller.listproducts.ListProductsController;
import com.sombrainc.client.service.ServiceAsync;
import com.sombrainc.shared.form.ProductForm;

public class ListProducts extends AbstractPage implements ListProductsListener {

	private ListProductsController listProductsController;

	private static ViewProductsUiBinder uiBinder = GWT
			.create(ViewProductsUiBinder.class);

	interface ViewProductsUiBinder extends UiBinder<Widget, ListProducts> {
	}

	@UiField
	VerticalPanel mainPanel;
	@UiField
	Button cancel;

	public ListProducts(PhoneGap phoneGap, ServiceAsync service) {
		initWidget(uiBinder.createAndBindUi(this));
		listProductsController = new ListProductsController(phoneGap, service,
				this);
		listProductsController.getListProductExecute();
	}

	@Override
	public void setListProducts(List<ProductForm> result) {
		if (result.isEmpty()) {
			Window.alert("You do not have products");
		} else {
			for (ProductForm productForm : result) {
				mainPanel.add(new BitProduct(productForm));
			}
		}
	}

	@UiHandler("cancel")
	void cancelOnClick(ClickEvent e) {
		listProductsController.createWelcomePage();
	}
}
