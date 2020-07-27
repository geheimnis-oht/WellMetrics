package io.laidani.models;

public enum ReservoirType {
	F1 ("F1"), 
	F2 ("F2"), 
	F3 ("F3"), 
	F4 ("F4"), 
	F5 ("F5"), 
	F6 ("F6"), 
	ORDO ("ORDOVICIEN"),
	OXX ("OTHERS");
	
	private final String rt;

	private ReservoirType(String rt) {
		this.rt = rt;
	}

	public String getRt() {
		return rt;
	}
}
