package com.sombrainc.client.page.controller.addProduct;

import static com.sombrainc.client.util.PageManager.toPage;
import static com.sombrainc.shared.util.FileConstant.FILE_KEY;
import static com.sombrainc.shared.util.FileConstant.IMAGE_MIME_TYPE;
import static com.sombrainc.shared.util.URLConstants.UPLOAD_URL;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Window;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.camera.PictureCallback;
import com.googlecode.gwtphonegap.client.camera.PictureOptions;
import com.googlecode.gwtphonegap.client.file.FileTransferError;
import com.googlecode.gwtphonegap.client.file.FileTransferProgressEvent;
import com.googlecode.gwtphonegap.client.file.FileUploadCallback;
import com.googlecode.gwtphonegap.client.file.FileUploadOptions;
import com.googlecode.gwtphonegap.client.file.FileUploadResult;
import com.sombrainc.client.facebook.FBConnectManager;
import com.sombrainc.client.facebook.callback.FBApiCallBack;
import com.sombrainc.client.page.addProduct.AddProductListener;
import com.sombrainc.client.page.controller.common.AbstractController;
import com.sombrainc.client.page.welcome.Welcome;
import com.sombrainc.client.service.ServiceAsync;
import com.sombrainc.shared.form.ProductForm;
import com.sombrainc.shared.util.ProductMapped;

public class AddProductController extends AbstractController {
	private final AddProductListener listener;
	private List<String> urlsImages;
	private FileUploadOptions currentFileUploadOptions;
	private PictureOptions getPhotoPictureOptions, takePhotoPictureOptions;
	private ProductForm productForm;

	public AddProductController(PhoneGap phoneGap, ServiceAsync service,
			AddProductListener listener) {
		super(phoneGap, service);
		this.listener = listener;
	}

	public void getPhoto() {
		getPicture(createGetPhotoPictureOptions());
	}

	public void takePhoto() {
		getPicture(createTakePhotoPictureOptions());
	}

	private void getPicture(PictureOptions pictureOptions) {
		phoneGap.getCamera().getPicture(pictureOptions, new PictureCallback() {

			@Override
			public void onSuccess(String data) {
				addImage(data);
			}

			@Override
			public void onFailure(String message) {
				Window.alert(message);
			}

		});
	}

	public void upload() {
		if (urlsImages == null || urlsImages.isEmpty()) {
			Window.alert("Please add at least one image");
			return;
		}
		executeUploadIfNetConnected();
	}

	private void executeUploadIfNetConnected() {
		// there check connect to internet
		FBConnectManager.api(new FBApiCallBack() {
			@Override
			public void onSuccess(String facebookId, String name) {
				createProductForm(Long.parseLong(facebookId));
				bitUpload();
			}

			@Override
			public void onFailure(String error) {
				Window.alert("error:"+error);

			}
		});
	}

	private void bitUpload() {
		createFileUploadOptions();
		phoneGap.getFile()
				.createFileTransfer()
				.upload(urlsImages.remove(0), UPLOAD_URL,
						currentFileUploadOptions, new FileUploadCallback() {
							@Override
							public void onSuccess(FileUploadResult result) {
								if (!urlsImages.isEmpty()) {
									bitUpload();
								} else {
									listener.reset();
									Window.alert("Product was created successful.");
								}
							}

							@Override
							public void onProgress(
									FileTransferProgressEvent event) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onFailure(FileTransferError error) {
								Window.alert("Here error:" + error.toString());

							}
						});
	}

	private PictureOptions createGetPhotoPictureOptions() {
		if (getPhotoPictureOptions == null) {
			getPhotoPictureOptions = new PictureOptions();
			getPhotoPictureOptions
					.setSourceType(PictureOptions.PICTURE_SOURCE_TYPE_SAVED_PHOTO_ALBUM);
			getPhotoPictureOptions
					.setMediaType(PictureOptions.CAMERA_MEDIA_TYPE_PICTURE);
			getPhotoPictureOptions
					.setDestinationType(PictureOptions.DESTINATION_TYPE_FILE_URI);
			getPhotoPictureOptions.setAllowEdit(false);
		}
		return getPhotoPictureOptions;
	}

	private PictureOptions createTakePhotoPictureOptions() {
		if (takePhotoPictureOptions == null) {
			takePhotoPictureOptions = new PictureOptions(100);
			takePhotoPictureOptions
					.setSourceType(PictureOptions.PICTURE_SOURCE_TYPE_CAMERA);
			takePhotoPictureOptions
					.setDestinationType(PictureOptions.DESTINATION_TYPE_FILE_URI);
			takePhotoPictureOptions.setCorrectOrientation(true);
			takePhotoPictureOptions.setSaveToPhotoAlbum(true);
			takePhotoPictureOptions.setAllowEdit(false);
		}
		return takePhotoPictureOptions;
	}

	private void createFileUploadOptions() {
		if (currentFileUploadOptions == null) {
			currentFileUploadOptions = new FileUploadOptions();
			currentFileUploadOptions.setFileKey(FILE_KEY);
			currentFileUploadOptions.setMimeType(IMAGE_MIME_TYPE);
		}
		if (urlsImages.size() == 1) {
			currentFileUploadOptions.setParams(ProductMapped
					.productFormToParams(productForm));
		}
	}

	private void addImage(String urlImage) {
		Window.alert("Selected: " + urlImage);
		if (urlsImages == null) {
			urlsImages = new ArrayList<String>();
		}
		urlsImages.add(urlImage);
		listener.addImage(urlImage);
	}

	private void createProductForm(long facebookId) {
		productForm = new ProductForm();
		productForm.setProductName(listener.getProductName());
		productForm.setDescription(listener.getProductDescription());
		productForm.setImageNumber(urlsImages.size());
		productForm.setOwner(facebookId);
		// add another fields
		productForm.setBrand("adidas");
		productForm.setType("clothes");
		productForm.setSize("XXL");
		productForm.setColor("green");
		productForm.setYear(2014);
		productForm.setPrice(75.50);
	}

	public void clearUrlsImages() {
		urlsImages.clear();
	}

	public void toWelcomePage() {
		toPage(new Welcome(phoneGap, service));
	}
}
