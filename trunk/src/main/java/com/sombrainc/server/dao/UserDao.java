package com.sombrainc.server.dao;

import java.util.List;

import com.sombrainc.shared.form.ProductForm;
import com.sombrainc.shared.form.UserForm;

public interface UserDao {
	void insertOrUpdateUser(UserForm user);
	boolean createProduct(ProductForm productForm);
	long getProductCount();
	List<ProductForm>getAllProducts();
}
