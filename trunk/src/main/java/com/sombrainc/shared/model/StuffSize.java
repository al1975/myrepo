package com.sombrainc.shared.model;

import java.io.Serializable;

public class StuffSize implements Serializable {

	private static final long serialVersionUID = -5720034940840435116L;
	private long id;
	private String size;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "StuffSize [id=" + id + ", size=" + size + "]";
	}

}
