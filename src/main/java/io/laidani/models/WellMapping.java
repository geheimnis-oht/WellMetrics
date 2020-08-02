package io.laidani.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "WELLS_MAPPING", uniqueConstraints = @UniqueConstraint(columnNames = {"WELL_UID",
																					"PERIMETER_UID",
																					"FIELD_UID"}, 
                                                                      name = "UK_WELL_FIELD_PERIMETER"))
public class WellMapping {
	@Id
	@GeneratedValue()
	private int uid;
	@Column(name = "WELL_UID")
	private int wid;
	@Column(name = "PERIMETER_UID", nullable = true)
	private int pid;
	@Column(name = "FIELD_UID", nullable = true)
	private int fid;
	@Column(name = "CREATION_DATE")
	private LocalDateTime creationDate;
	@Column(name = "UPDATE_DATE")
	private LocalDateTime updateDate;
		
	@PrePersist
	protected void prePersiste() {
		this.creationDate = LocalDateTime.now();
	}
	
	@PreUpdate
	protected void preUpdate() {
		this.updateDate = LocalDateTime.now();
	}
	
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}	
}
