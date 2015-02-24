package com.sombrainc.shared.form;

import java.io.Serializable;

public class UserForm implements Serializable {

	private static final long serialVersionUID = 3192259847669341042L;

	private long facebook_id;
	private String name;

	public UserForm() {
	}

	public UserForm(long facebook_id, String name) {
		this.facebook_id = facebook_id;
		this.name = name;
	}

	public long getFacebook_id() {
		return facebook_id;
	}

	public void setFacebook_id(long facebook_id) {
		this.facebook_id = facebook_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserForm [facebook_id=" + facebook_id + ", name=" + name + "]";
	}

}
