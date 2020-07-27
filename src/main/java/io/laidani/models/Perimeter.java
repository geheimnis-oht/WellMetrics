package io.laidani.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "PERIMETERS", uniqueConstraints = @UniqueConstraint(columnNames = {"PERIMETER_NAME"}, name = "UK_PERIMETER_NAME"))
public class Perimeter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UID")
	private int uid;
	@Size(min = 3, max = 6, message = "Perimeter name length should be between 3 and 6 characters !")
	@Column(name ="PERIMETER_NAME")
	private String perimeterName;
	@Size(min = 10, max = 255, message = "Perimeter description should be between 10 and 255 characters !")
	@Column(name ="PERIMETER_DESC")
	private String perimeterDesc;
	@Column(name ="CREATION_DATE")
	private LocalDate creationDate;
	
	@OneToMany(mappedBy = "perimeter", cascade = CascadeType.ALL)
	@JsonBackReference
	private List<Well> wells;
	
	@PrePersist
	protected void prePersiste() {
		this.creationDate = LocalDate.now();
	}
	
    public Perimeter() {
	}

	// Getters and Setters
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
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public List<Well> getWells() {
		return wells;
	}

	public void setWells(List<Well> wells) {
		this.wells = wells;
	}

	public String getPerimeterDesc() {
		return perimeterDesc;
	}

	public void setPerimeterDesc(String perimeterDesc) {
		this.perimeterDesc = perimeterDesc;
	}
	
}
