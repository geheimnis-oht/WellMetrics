package io.laidani.models;

public enum WellFieldType {
	F1 ("F1"), 
	F2 ("F2"), 
	F3 ("F3"), 
	F4 ("F4"), 
	F5 ("F5"), 
	F6 ("F6"), 
	ORDO ("ORDOVICIEN"),
	OXX ("OTHERS");
	
	private final String wft;

	WellFieldType(String wft) {
		this.wft = wft;
	}

	public String getWft() {
		return wft;
	}

}
