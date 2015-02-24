package com.sombrainc.client.page.listproducts;

import static com.sombrainc.shared.util.FileConstant.JPG_IMAGE_EXTENSION;
import static com.sombrainc.shared.util.URLConstants.IMAGE_DIR;
import static com.sombrainc.shared.util.URLConstants.SLASH;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sombrainc.client.page.common.AbstractPage;
import com.sombrainc.shared.form.ProductForm;

public class BitProduct extends AbstractPage {
	private static BitProductUiBinder uiBinder = GWT
			.create(BitProductUiBinder.class);

	interface BitProductUiBinder extends UiBinder<Widget, BitProduct> {
	}

	@UiField
	Image image;
	@UiField
	Label productNameLabel;

	public BitProduct(ProductForm productForm) {
		initWidget(uiBinder.createAndBindUi(this));
		image.setUrl(getFirstImageUrlForProductId(productForm.getId()));
		productNameLabel.setText(productForm.getProductName());
	}

	private String getFirstImageUrlForProductId(Long productId) {
		return IMAGE_DIR + productId + SLASH + "1" + JPG_IMAGE_EXTENSION;
	}
}
