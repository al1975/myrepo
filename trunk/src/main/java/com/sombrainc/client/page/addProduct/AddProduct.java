package com.sombrainc.client.page.addProduct;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.sombrainc.client.page.common.AbstractPage;
import com.sombrainc.client.page.controller.addProduct.AddProductController;
import com.sombrainc.client.service.ServiceAsync;

public class AddProduct extends AbstractPage implements AddProductListener {
	interface AddProductUiBinder extends UiBinder<Widget, AddProduct> {
	}

	private static AddProductUiBinder uiBinder = GWT
			.create(AddProductUiBinder.class);

	private AddProductController controller;

	@UiField
	Label productNameLabel;

	@UiField
	TextBox productName;

	@UiField
	Label productDescriptionLabel;

	@UiField
	TextArea productDescription;

	@UiField
	VerticalPanel imagePanel;

	@UiField
	Button camera;
	@UiField
	Button submit;
	@UiField
	Button cancel;
	@UiField
	Button library;
	@UiField
	Button reset;

	public AddProduct(PhoneGap phoneGap, ServiceAsync service) {
		initWidget(uiBinder.createAndBindUi(this));
		controller = new AddProductController(phoneGap, service, this);
	}

	@Override
	public void addImage(String imageUri) {
		Image img = new Image(imageUri);
		// img.setPixelSize(Window.getClientWidth()/5,
		// Window.getClientHeight()/5);
		imagePanel.add(img);

	}

	@UiHandler("camera")
	void cameraOnClick(ClickEvent e) {
		controller.takePhoto();
	}

	@UiHandler("library")
	void libraryOnClick(ClickEvent e) {
		controller.getPhoto();
	}

	@UiHandler("submit")
	void submitOnClick(ClickEvent e) {
		controller.upload();
	}

	@UiHandler("cancel")
	void cancelOnClick(ClickEvent e) {
		controller.toWelcomePage();
	}

	@UiHandler("reset")
	void resetOnClick(ClickEvent e) {
		reset();
	}

	@Override
	public String getProductName() {
		return productName.getText();
	}

	@Override
	public String getProductDescription() {
		return productDescription.getText();
	}

	@Override
	public void reset() {
		productName.setText("");
		productDescription.setText("");
		imagePanel.clear();
		controller.clearUrlsImages();
	}

}
