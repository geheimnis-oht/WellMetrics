package io.laidani.models;

public enum WellStatus {
	O ("Open"),
	C ("Closed"),
	UT ("Under Test"),
	OXX ("Other");
	
	private final String ws;

	private WellStatus(String ws) {
		this.ws = ws;
	}

	public String getWs() {
		return ws;
	}
	
}
