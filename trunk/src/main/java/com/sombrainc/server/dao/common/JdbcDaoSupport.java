package com.sombrainc.server.dao.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class JdbcDaoSupport {
	@Autowired
	protected JdbcTemplate jdbcTemplate;
}
