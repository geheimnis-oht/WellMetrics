package io.laidani.models;

public enum WellTestStatus {
	
	OK ("OK"),
	UNV ("INVALID"),
	UND ("UNDEFINED"),
	OXX ("OTHER");
	
	private final String wts;

	private WellTestStatus(String wts) {
		this.wts = wts;
	}

	public String getWts() {
		return wts;
	}
	
}
