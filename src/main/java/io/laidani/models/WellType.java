package io.laidani.models;

public enum WellType {
	AGL ("AGL"),
	ERP ("ERUPTIF"),
    OXX ("OTHER");
	
	private final String wt;

	WellType(String wt) {
		this.wt = wt;
	}

	public String getWt() {
		return wt;
	}

}
