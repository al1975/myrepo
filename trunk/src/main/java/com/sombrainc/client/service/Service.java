package com.sombrainc.client.service;



import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.sombrainc.shared.form.ProductForm;
import com.sombrainc.shared.form.UserForm;

/**
 * The client side stub for the RPC service.
 */
//@RemoteServiceRelativePath("greet")
public interface Service extends RemoteService {
	void insertOrUpdateUser(UserForm user) throws IllegalArgumentException;
	List<ProductForm>getAllProducts(); 
}
