package com.sombrainc.server.upload;

import static com.sombrainc.shared.util.FileConstant.FILE_KEY;
import static com.sombrainc.shared.util.FileConstant.JPG_IMAGE_EXTENSION;
import static com.sombrainc.shared.util.URLConstants.PRODUCT_IMAGES_DIR;
import static com.sombrainc.shared.util.URLConstants.UPLOAD_SERVLET;
import static com.sombrainc.shared.util.URLConstants.SLASH;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.sombrainc.server.dao.UserDao;
import com.sombrainc.shared.util.ProductMapped;

@WebServlet(SLASH + UPLOAD_SERVLET)
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)
// 50MB
public class UploadServlet extends HttpServlet {

	private static final long serialVersionUID = -6167196790420750536L;

	private String mainDirPath = "";
	private String productDirPath = "";
	private boolean isCreatedProductDir = false;
	private int currentImageNumber = 0;

	@Autowired
	private UserDao userDao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
		mainDirPath = config.getServletContext().getRealPath("")
				+ File.separator + PRODUCT_IMAGES_DIR;
		File fileSaveDir = new File(mainDirPath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean isParameterMapEmpty = request.getParameterMap().isEmpty();
		// upload first image
		if (isParameterMapEmpty && !isCreatedProductDir) {
			createProductDir();
			writeImage(request);
		} else
		// upload in-between image
		if (isParameterMapEmpty && isCreatedProductDir) {
			writeImage(request);
		} else
		// upload last image
		if (!isParameterMapEmpty && isCreatedProductDir) {
			writeImage(request);
			writeProduct(request);
		} else
		// upload single image
		if (!isParameterMapEmpty && !isCreatedProductDir) {
			createProductDir();
			writeImage(request);
			writeProduct(request);
		}
	}

	private void createProductDir() {
		productDirPath = mainDirPath + File.separator
				+ (userDao.getProductCount() + 1);
		new File(productDirPath).mkdir();
		isCreatedProductDir = true;
	}

	private void writeImage(HttpServletRequest request)
			throws ServletException, IOException {
		request.getPart(FILE_KEY).write(
				productDirPath + File.separator + (++currentImageNumber)
						+ JPG_IMAGE_EXTENSION);
	}

	private void writeProduct(HttpServletRequest request)
			throws ServletException, IOException {
		userDao.createProduct(ProductMapped.paramsToProductForm(request
				.getParameterMap()));
		currentImageNumber = 0;
		isCreatedProductDir = false;
	}
}
