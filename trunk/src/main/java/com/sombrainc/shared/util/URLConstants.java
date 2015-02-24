package com.sombrainc.shared.util;

public class URLConstants {
	public static final String PROD_HOST = "192.168.1.40";
	public static final String LOCAL_HOST = "127.0.0.1";
	public static final String PROTOCOL = "http://";
	public static final String PROD_PORT = "8080";
	public static final String CONTEXT = "share-your-stuff";
	public static final String COLON = ":";
	public static final String SLASH = "/";
	public static final String REQUEST_SERVLET = "request";
	public static final String MODULE_NAME = "Main";
	public static final String PRODUCT_IMAGES_DIR = "product_images";
	public static final String PHONEGAP_MODULE_URL = PROTOCOL + PROD_HOST
			+ COLON + PROD_PORT + SLASH + CONTEXT + SLASH;
	public static final String IMAGE_DIR = PHONEGAP_MODULE_URL
			+ PRODUCT_IMAGES_DIR + SLASH;
	public static final String FB_IMAGE_PATH_PREFIX = "https://graph.facebook.com/";
	public static final String FB_IMAGE_PATH_SUFFIX = "/picture";
	public static final String UPLOAD_SERVLET = "UploadServlet";
	public static final String UPLOAD_URL = PHONEGAP_MODULE_URL
			+ UPLOAD_SERVLET;
}
