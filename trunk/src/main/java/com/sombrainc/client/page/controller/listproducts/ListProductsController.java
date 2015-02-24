package com.sombrainc.client.page.controller.listproducts;

import static com.sombrainc.client.util.PageManager.toPage;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.sombrainc.client.page.controller.common.AbstractController;
import com.sombrainc.client.page.listproducts.ListProductsListener;
import com.sombrainc.client.page.welcome.Welcome;
import com.sombrainc.client.service.ServiceAsync;
import com.sombrainc.shared.form.ProductForm;

public class ListProductsController extends AbstractController {
	private final ListProductsListener listener;

	public ListProductsController(PhoneGap phoneGap, ServiceAsync service,
			ListProductsListener listener) {
		super(phoneGap, service);
		this.listener = listener;
	}

	public void getListProductExecute() {
		service.getAllProducts(new AsyncCallback<List<ProductForm>>() {

			@Override
			public void onSuccess(List<ProductForm> result) {
				listener.setListProducts(result);
			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error:" + caught);
			}
		});

	}

	public void createWelcomePage() {
		toPage(new Welcome(phoneGap, service));
	}
}
