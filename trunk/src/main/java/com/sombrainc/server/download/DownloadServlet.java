package com.sombrainc.server.download;

import static com.sombrainc.shared.util.URLConstants.PRODUCT_IMAGES_DIR;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.sombrainc.server.dao.UserDao;

@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {

	private static final long serialVersionUID = -6167196790420750536L;

	private String mainDirPath = "";

	@Autowired
	private UserDao userDao;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
		mainDirPath = config.getServletContext().getRealPath("") + File.separator
				+ PRODUCT_IMAGES_DIR;
		File fileSaveDir = new File(mainDirPath);
		if (!fileSaveDir.exists()) {
			//fileSaveDir.mkdir();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("START");
		OutputStream out = response.getOutputStream();
		FileInputStream in = new FileInputStream(mainDirPath + "/1/1.jpeg");
		byte[] buffer = new byte[4096];
		int length;
		while ((length = in.read(buffer)) > 0){
		    out.write(buffer, 0, length);
		}
		in.close();
		out.flush();
}
}