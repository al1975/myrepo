package com.sombrainc.client.page.enums;

public enum Div {
	HEADER, MAIN;

	public String panel() {
		return name().toLowerCase();
	}
}
	