package io.laidani.dtos;

import io.laidani.models.Perimeter;

public class WellDTO {
	
	private int uid;
	private String wellName;
	private int gps_latitude;
		
	public WellDTO() {
	}
	
	public WellDTO(int uid, String wellName) {
		super();
		this.uid = uid;
		this.wellName = wellName;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getWellName() {
		return wellName;
	}
	public void setWellName(String wellName) {
		this.wellName = wellName;
	}

	public int getGps_latitude() {
		return gps_latitude;
	}

	public void setGps_latitude(int gps_latitude) {
		this.gps_latitude = gps_latitude;
	}

}
