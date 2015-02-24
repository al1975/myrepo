package com.sombrainc.server.common;

import javax.servlet.ServletException;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public abstract class AutowiringRemoteServiceServlet extends
		RemoteServiceServlet {
	@Override
	public void init() throws ServletException {
		super.init();

		final WebApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());

		if (ctx == null) {
			throw new IllegalStateException(
					"No Spring web application context found");
		}

		ctx.getAutowireCapableBeanFactory().autowireBeanProperties(this,
				AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, true);
	}
}