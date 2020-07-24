package io.laidani.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

@Entity
@Table(name = "PERIMETERS", uniqueConstraints = @UniqueConstraint(columnNames = {"PERIMETER_NAME"}, name = "UK_PERIMETER_NAME"))
public class Perimeter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UID")
	private int uid;
	@Size(min = 3, max = 6, message = "Perimeter name length should be 3 and 6 characters !")
	@Column(name ="PERIMETER_NAME")
	private String perimeterName;
	@Column(name ="CREATION_DATE")
	private LocalDate CreationDate;
	
	@OneToMany(mappedBy = "perimeter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Well> wells;
	
	@PrePersist
	protected void prePersiste() {
		this.CreationDate = LocalDate.now();
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getPerimeterName() {
		return perimeterName;
	}

	public void setPerimeterName(String perimeterName) {
		this.perimeterName = perimeterName;
	}

	public LocalDate getCreationDate() {
		return CreationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		CreationDate = creationDate;
	}

	public List<Well> getWells() {
		return wells;
	}

	public void setWells(List<Well> wells) {
		this.wells = wells;
	}
	
}
