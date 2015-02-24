package com.sombrainc.server.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import com.sombrainc.server.dao.UserDao;
import com.sombrainc.server.dao.common.JdbcDaoSupport;
import com.sombrainc.shared.form.ProductForm;
import com.sombrainc.shared.form.UserForm;

@Repository
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
	@Override
	public void insertOrUpdateUser(UserForm user) {
		String query = "insert into User (facebook_id, name) values (?, ?) ON DUPLICATE KEY UPDATE name = values(name)";
		try {
			jdbcTemplate.update(query, new Object[] { user.getFacebook_id(),
					user.getName() });
		} catch (DataAccessException e) {

		}
	}

	@Override
	public boolean createProduct(ProductForm productForm) {
		String query = "insert into product (owner, product_name, brand, type, size, color, year, price, description, image_number) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			jdbcTemplate.update(
					query,
					new Object[] { productForm.getOwner(),
							productForm.getProductName(),
							productForm.getBrand(), productForm.getType(),
							productForm.getSize(), productForm.getColor(),
							productForm.getYear(), productForm.getPrice(),
							productForm.getDescription(),
							productForm.getImageNumber() });
		} catch (DataAccessException e) {
			return false;
		}
		return true;
	}

	@Override
	public long getProductCount() {
		return jdbcTemplate.queryForObject("select count(*) from product",
				Long.class);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ProductForm> getAllProducts() {
		return jdbcTemplate.query("select * from product",
				new BeanPropertyRowMapper(ProductForm.class));
	}
}
