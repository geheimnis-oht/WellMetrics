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
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "FIELDS")
public class Field {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UID")
	private int uid;
	@Size(min = 3, max = 6, message = "Field name length should be 3 and 6 characters !")
    @Column(name="FIELD_NAME")
	private String fieldName;
    @Column(name = "CREATION_DATE")
	private LocalDate creationDate;
    
    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Well> wells;

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
    
}
