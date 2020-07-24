package io.laidani.models;

public enum ChuckType {
	
	C14 ("Clapet 1/4"),
	C18 ("Clapet 1/8"),
	C16 ("Clapet 1/16"),
	C32 ("Clapet 1/32"),
	OXX ("OTHERS");
	
	private final String ct;

	private ChuckType(String ct) {
		this.ct = ct;
	}

	public String getCt() {
		return ct;
	}
	
}
