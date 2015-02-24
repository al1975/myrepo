package com.sombrainc.client.page.addPhoto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.sombrainc.client.page.common.AbstractPage;
import com.sombrainc.client.page.controller.addPhoto.AddPhotoController;
import com.sombrainc.client.service.ServiceAsync;

public class AddPhoto extends AbstractPage implements AddPhotoListener {
	interface AddPhotoUiBinder extends UiBinder<Widget, AddPhoto> {
	}

	private static AddPhotoUiBinder uiBinder = GWT
			.create(AddPhotoUiBinder.class);

	private AddPhotoController controller;
	@UiField
	VerticalPanel mainPanel;
	@UiField
	SimplePanel imagePanel;
	@UiField
	Button yourLibraryButton;
	@UiField
	Button photoDeviceButton;
	@UiField
	Button arrowButton;
	private Grid grid;

	public AddPhoto(PhoneGap phoneGap, ServiceAsync service) {
		initWidget(uiBinder.createAndBindUi(this));
		controller = new AddPhotoController(phoneGap, service, this);
		createGrid();
		setMainPanelSize();
		Window.addResizeHandler(new ResizeHandler() {
			@Override
			public void onResize(ResizeEvent event) {
				setMainPanelSize();
			}
		});
	}

	private void setMainPanelSize() {
		mainPanel
				.setPixelSize(Window.getClientWidth(), Window.getClientWidth());
		imagePanel.setWidth(Window.getClientWidth() + "px");
	}

	@Override
	public void addImage(String imageUri) {
		Image img = new Image(imageUri);
		// img.setPixelSize(Window.getClientWidth()/5,
		// Window.getClientHeight()/5);
		imagePanel.add(img);

	}

	private void createGrid() {
		grid = new Grid(2,3);
	    for (int row = 0; row < 2; ++row) {
	        for (int col = 0; col < 3; ++col)
	        	grid.setText(row, col, "QQ");
	      }
	    imagePanel.add(grid);
	}

	@UiHandler("photoDeviceButton")
	void cameraOnClick(ClickEvent e) {
		controller.takePhoto();
	}

	@UiHandler("yourLibraryButton")
	void libraryOnClick(ClickEvent e) {
		controller.getPhoto();
	}

	@Override
	public void goToPage() {

	}
}
