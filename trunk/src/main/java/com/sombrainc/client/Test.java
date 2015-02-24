package com.sombrainc.client;

import static com.sombrainc.shared.util.URLConstants.PHONEGAP_MODULE_URL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableEvent;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableHandler;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutEvent;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutHandler;
import com.googlecode.gwtphonegap.client.camera.PictureCallback;
import com.googlecode.gwtphonegap.client.camera.PictureOptions;
import com.googlecode.gwtphonegap.client.file.DirectoryEntry;
import com.googlecode.gwtphonegap.client.file.FileTransferError;
import com.googlecode.gwtphonegap.client.file.FileTransferProgressEvent;
import com.googlecode.gwtphonegap.client.file.FileUploadCallback;
import com.googlecode.gwtphonegap.client.file.FileUploadOptions;
import com.googlecode.gwtphonegap.client.file.FileUploadResult;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartHandler;
import com.googlecode.mgwt.ui.client.widget.button.Button;
import com.googlecode.mgwt.ui.client.widget.layout.LayoutPanel;
import com.sombrainc.client.phonegap.PhonegapService;
import com.sombrainc.client.service.ServiceAsync;
import com.sombrainc.shared.form.ProductForm;
import com.sombrainc.shared.form.UserForm;

public class Test extends PhonegapService {

	DirectoryEntry dirEntry;
	String currentFileUri;

	public Test(PhoneGap phoneGap, ServiceAsync service) {
		super(phoneGap, service);
	}

	public void inUse() {

		final long t = System.currentTimeMillis();
		phoneGap.addHandler(new PhoneGapAvailableHandler() {

			@Override
			public void onPhoneGapAvailable(PhoneGapAvailableEvent event) {
				Window.alert("PhoneGap Initialized for: "
						+ (System.currentTimeMillis() - t) + " ms");
				createUI();
			}
		});
		createUI();

		phoneGap.addHandler(new PhoneGapTimeoutHandler() {

			@Override
			public void onPhoneGapTimeout(PhoneGapTimeoutEvent event) {
				Window.alert("can not load phonegap");
			}
		});

		phoneGap.initializePhoneGap();
	}

	// private void testDevice() {
	// Device device = phoneGap.getDevice();
	// Window.alert("PhoneGapVersion:" + device.getPhoneGapVersion()
	// + "\nPlatform:" + device.getPlatform() + "\nUuid:"
	// + device.getUuid() + "\n" + device.getPlatform() + " version: "
	// + device.getVersion());
	// RootPanel.get(DivNames.workButton).clear();
	// }

	public void takePhoto() {
		PictureOptions options = new PictureOptions(100);
		options.setSourceType(PictureOptions.PICTURE_SOURCE_TYPE_CAMERA);
		options.setDestinationType(PictureOptions.DESTINATION_TYPE_FILE_URI);
		options.setCorrectOrientation(true);
		options.setSaveToPhotoAlbum(true);
		options.setAllowEdit(false);
		phoneGap.getCamera().getPicture(options, new PictureCallback() {

			@Override
			public void onSuccess(String data) {
				currentFileUri = data;
				Window.alert("Saved in " + data);
			}

			@Override
			public void onFailure(String message) {
				Window.alert(message);

			}

		});
	}

	public void getPhotoDirection() {

	}

	public void getPhoto() {
		PictureOptions options = new PictureOptions();
		options.setSourceType(PictureOptions.PICTURE_SOURCE_TYPE_SAVED_PHOTO_ALBUM);
		options.setMediaType(PictureOptions.CAMERA_MEDIA_TYPE_PICTURE);
		options.setDestinationType(PictureOptions.DESTINATION_TYPE_FILE_URI);

		// options.setCorrectOrientation(true);
		options.setAllowEdit(false);
		phoneGap.getCamera().getPicture(options, new PictureCallback() {

			@Override
			public void onSuccess(String data) {
				currentFileUri = data;
				// Window.alert("Direction: ");
				Window.alert("Selected: " + data);
				// createPopup("data:image/jpeg;base64," + data);
				createPopup(data);
			}

			@Override
			public void onFailure(String message) {
				Window.alert(message);

			}

		});
		Window.alert("SelectedDirection: " + options.getDirection());
	}

	private void testRpcPhoneGap() {

		service.insertOrUpdateUser(new UserForm(123456789, "Danil"),
				new AsyncCallback<Void>() {
					public void onFailure(Throwable caught) {
						Window.alert("Request failure: " + caught);
					}

					public void onSuccess(Void result) {

						Window.alert("Request is: " + result);

					}
				});
	}

	private void testListAllProduct() {
		service.getAllProducts(new AsyncCallback<List<ProductForm>>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error: " + caught);

			}

			@Override
			public void onSuccess(List<ProductForm> result) {
				Window.alert("ProductForms: " + result);
			}
		});

		// String serverUrl = PHONEGAP_MODULE_URL + "DownloadServlet";
		// phoneGap.getFile()
		// .createFileTransfer()
		// .download(serverUrl,"/",
		// new FileDownloadCallback() {
		//
		// @Override
		// public void onSuccess(FileEntry result) {
		// Window.alert("result: " + result.getFullPath());
		//
		// }
		//
		// @Override
		// public void onProgress(FileTransferProgressEvent event) {
		// // TODO Auto-generated method stub
		//
		// }
		//
		// @Override
		// public void onFailure(FileTransferError error) {
		// Window.alert("Error: " + error.toString());
		//
		// }
		// });
	}

	private void testUpload() {
		if (currentFileUri == null || currentFileUri.isEmpty()) {
			Window.alert("Photo is not selected");
			return;
		}
		FileUploadOptions fuo = new FileUploadOptions();
		fuo.setFileKey("file");
		fuo.setMimeType("image/jpeg");
		Map<String, String> params = new HashMap<String, String>();
		params.put("owner", "12345");
		params.put("productName", "productName");
		params.put("brand", "brand");
		params.put("type", "type");
		params.put("size", "size");
		params.put("color", "color");
		params.put("year", "2014");
		params.put("price", "13.21");
		params.put("description", "description");
		params.put("imageNumber", "3");
		fuo.setParams(params);
		// fuo.setFileName(currentFileUri.substring(currentFileUri.lastIndexOf('/')+1));
		String serverUrl = PHONEGAP_MODULE_URL + "UploadServlet";
		phoneGap.getFile()
				.createFileTransfer()
				.upload(currentFileUri, serverUrl, fuo,
						new FileUploadCallback() {
							@Override
							public void onSuccess(FileUploadResult result) {
								Window.alert("Successful. Bytes Sent:"
										+ result.getBytesSent());

							}

							@Override
							public void onProgress(
									FileTransferProgressEvent event) {

								// TODO Auto-generated method stub

							}

							@Override
							public void onFailure(FileTransferError error) {
								Window.alert("error:" + error.toString());

							}
						});
	}

	private void createPopup(String imageText) {
		final PopupPanel pop = new PopupPanel();
		VerticalPanel vp = new VerticalPanel();
		Button b = new Button("Ok");
		b.addTouchStartHandler(new TouchStartHandler() {
			@Override
			public void onTouchStart(TouchStartEvent event) {
				pop.setVisible(false);
			}
		});
		Image image = new Image();
		image.setUrl(imageText);
		Dimension dimension = new Dimension();
		image.setPixelSize(dimension.getHeight() - b.getOffsetHeight(),
				dimension.getWidth());
		vp.add(image);
		vp.add(b);
		pop.add(vp);
		pop.show();

	}

	private void createUI() {
		RootPanel.get("workButton").clear();
		LayoutPanel lp = new LayoutPanel();
		Button but1 = new Button(messages.deviceInfo());
		but1.addTouchStartHandler(new TouchStartHandler() {

			@Override
			public void onTouchStart(TouchStartEvent event) {
				// testDevice();
			}
		});
		Button but2 = new Button(messages.takePhoto());
		but2.addTouchStartHandler(new TouchStartHandler() {

			@Override
			public void onTouchStart(TouchStartEvent event) {
				takePhoto();
			}
		});
		Button but3 = new Button(messages.getPhoto());
		but3.addTouchStartHandler(new TouchStartHandler() {

			@Override
			public void onTouchStart(TouchStartEvent event) {
				getPhoto();
			}
		});
		Button but4 = new Button("RPC");
		but4.addTouchStartHandler(new TouchStartHandler() {

			@Override
			public void onTouchStart(TouchStartEvent event) {
				testRpcPhoneGap();
			}
		});
		Button but5 = new Button("Upload");
		but5.addTouchStartHandler(new TouchStartHandler() {

			@Override
			public void onTouchStart(TouchStartEvent event) {
				testUpload();
			}
		});
		Button but6 = new Button("Download");
		but6.addTouchStartHandler(new TouchStartHandler() {

			@Override
			public void onTouchStart(TouchStartEvent event) {
				testListAllProduct();
			}
		});
		lp.add(but1);
		lp.add(but2);
		lp.add(but3);
		lp.add(but4);
		lp.add(but5);

		lp.add(but6);
		RootPanel.get("main").add(lp);
	}

	class Dimension {
		private int height;
		private int width;

		public Dimension() {
			height = Window.getClientWidth();
			width = Window.getClientHeight();
		}

		public int getHeight() {
			return height;
		}

		public int getWidth() {
			return width;
		}

		@Override
		public String toString() {
			return "Dimension [height=" + height + ", width=" + width + "]";
		}
	}

}
