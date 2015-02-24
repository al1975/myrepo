package com.sombrainc.shared.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -1872927475185742769L;

	private long id;
	private long facebookId;
	private String name;

	public User() {
	}

	public User(long facebookId, String name) {
		this.facebookId = facebookId;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(long facebookId) {
		this.facebookId = facebookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", facebookId=" + facebookId + ", name="
				+ name + "]";
	}

}
