package io.laidani.models;

import java.time.LocalDate;
import java.util.ArrayList;
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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "FIELDS")
public class Field {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UID")
	private int uid;
	@Size(min = 3, max = 12, message = "Field name length should be between 3 and 12 characters !")
    @Column(name="FIELD_NAME")
	private String fieldName;
	@Size(min = 10, max = 255, message = "Field description should be between 10 and 255 characters !")
    @Column(name="FIELD_DESC")
	private String fieldDesc;
    @Column(name = "CREATION_DATE")
	private LocalDate creationDate;
    
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Well> wells = new ArrayList<>();
    
    @PrePersist
	protected void prePersiste() {
		this.creationDate = LocalDate.now();
	}

	// Getters and Setters ...
    public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
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

	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}
	
}
