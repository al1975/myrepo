package com.sombrainc.server;

import static com.sombrainc.shared.util.URLConstants.COLON;
import static com.sombrainc.shared.util.URLConstants.LOCAL_HOST;
import static com.sombrainc.shared.util.URLConstants.MODULE_NAME;
import static com.sombrainc.shared.util.URLConstants.PROTOCOL;
import static com.sombrainc.shared.util.URLConstants.SLASH;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gwt.user.server.rpc.SerializationPolicy;
import com.sombrainc.client.service.Service;
import com.sombrainc.server.common.AutowiringRemoteServiceServlet;
import com.sombrainc.server.dao.UserDao;
import com.sombrainc.shared.form.ProductForm;
import com.sombrainc.shared.form.UserForm;

@SuppressWarnings("serial")
public class ServiceImpl extends AutowiringRemoteServiceServlet implements
		Service {
	@Autowired
	private UserDao userDao;

	public void insertOrUpdateUser(UserForm user) throws IllegalArgumentException {
		userDao.insertOrUpdateUser(user);
	}
	
	@Override
	public List<ProductForm> getAllProducts() {
		return userDao.getAllProducts();
	}

	@Override
	protected SerializationPolicy doGetSerializationPolicy(
			HttpServletRequest request, String moduleBaseURL, String strongName) {
		// for possibility sending objects in rpc specified the path to
		// serialization policy file 'strongName'.gwt.rpc in the working
		// directory of
		// server(tomcat) which is located in compiled gwt-module
		return super.doGetSerializationPolicy(request,
				getUrlToSerializationPolicyFile(request), strongName);
	}

	private String getUrlToSerializationPolicyFile(HttpServletRequest request) {
		return PROTOCOL + LOCAL_HOST + COLON + request.getServerPort()
				+ request.getContextPath() + SLASH + MODULE_NAME + SLASH;
	}
}
