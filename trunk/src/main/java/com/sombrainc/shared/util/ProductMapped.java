package com.sombrainc.shared.util;

import java.util.HashMap;
import java.util.Map;

import com.sombrainc.shared.form.ProductForm;

public class ProductMapped {

	public static Map<String, String> productFormToParams(
			ProductForm productForm) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("owner", String.valueOf(productForm.getOwner()));
		params.put("productName", productForm.getProductName());
		params.put("brand", productForm.getBrand());
		params.put("type", productForm.getType());
		params.put("size", productForm.getSize());
		params.put("color", productForm.getColor());
		params.put("year", String.valueOf(productForm.getYear()));
		params.put("price", String.valueOf(productForm.getPrice()));
		params.put("description", productForm.getDescription());
		params.put("imageNumber", String.valueOf(productForm.getImageNumber()));
		return params;

	}

	public static ProductForm paramsToProductForm(Map<String, String[]> params) {
		ProductForm productForm = new ProductForm();
		productForm.setOwner(Long.parseLong(params.get("owner")[0]));
		productForm.setProductName(params.get("productName")[0]);
		productForm.setBrand(params.get("brand")[0]);
		productForm.setType(params.get("type")[0]);
		productForm.setSize(params.get("size")[0]);
		productForm.setColor(params.get("color")[0]);
		productForm.setYear(Integer.parseInt(params.get("year")[0]));
		productForm.setPrice(Double.parseDouble(params.get("price")[0]));
		productForm.setDescription(params.get("description")[0]);
		productForm.setImageNumber(Integer.parseInt(params.get("imageNumber")[0]));
		return productForm;
	}
}
