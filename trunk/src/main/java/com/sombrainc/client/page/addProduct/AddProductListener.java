package com.sombrainc.client.page.addProduct;

public interface AddProductListener {
	void addImage(String imageUri);

	String getProductName();

	String getProductDescription();
	
	void reset();

}
