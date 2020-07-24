package io.laidani.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.ForeignKey;


@Entity
@Table(name = "WELLS", uniqueConstraints = @UniqueConstraint(columnNames = {"WELL_NAME"}, name = "UK_WELL_NAME"))
public class Well {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UID")
	private int uid;
	@Column(name ="WELL_NAME")
	private String wellName;
	@Column(name = "START_DATE")
	private LocalDate startDate;
	@Column(name = "CREATION_DATE")
	private LocalDateTime creationDate;
	
	@Column(name = "WELL_TYPE")
	@Enumerated(EnumType.STRING)
	private WellType wellType;
	
	@Column(name = "MECANO_CODE", unique = true, length = 7)
	private String mecanoCode;
	
	@Column(name="LATITUDE")
	private long latitude;
	@Column(name = "LONGITUDE")
	private long longitude;
		
//	@OneToMany(mappedBy = "well", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<Chuck> chucks;
	
	@ManyToOne 
	@JoinColumn(name = "PERIMETER", foreignKey = @ForeignKey(name = "FK_WELL_PERIMETER"))
	private Perimeter perimeter;
	
	@ManyToOne
	@JoinColumn(name = "FIELD", foreignKey = @ForeignKey(name = "FK_WELL_FIELD"))
	private Field field;
	
	@OneToMany(mappedBy = "well", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Perforation> perfos;
	
	@OneToMany(mappedBy = "well", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<WellTest> tests;

	// Getters and Setters
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public WellType getWellType() {
		return wellType;
	}

	public void setWellType(WellType wellType) {
		this.wellType = wellType;
	}

	public String getMecanoCode() {
		return mecanoCode;
	}

	public void setMecanoCode(String mecanoCode) {
		this.mecanoCode = mecanoCode;
	}

	public long getLatitude() {
		return latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	public long getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	public Perimeter getPerimeter() {
		return perimeter;
	}

	public void setPerimeter(Perimeter perimeter) {
		this.perimeter = perimeter;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public List<Perforation> getPerfos() {
		return perfos;
	}

	public void setPerfos(List<Perforation> perfos) {
		this.perfos = perfos;
	}

	public List<WellTest> getTests() {
		return tests;
	}

	public void setTests(List<WellTest> tests) {
		this.tests = tests;
	}
	
}
