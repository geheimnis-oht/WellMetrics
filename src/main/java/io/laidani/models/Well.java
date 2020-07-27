package io.laidani.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name = "WELLS", uniqueConstraints = @UniqueConstraint(columnNames = {"WELL_NAME"}, name = "UK_WELL_NAME"))
public class Well {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UID")
	private int uid;
	@Size(min = 3, max = 6, message = "Well name length should be between 3 and 6 characters !")
	@Column(name ="WELL_NAME")
	private String wellName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "START_DATE")
	private LocalDate startDate;
	@Column(name = "CREATION_DATE")
	private LocalDateTime creationDate;
	
	@Column(name = "WELL_TYPE")
	@Enumerated(EnumType.STRING)
	private WellType wellType;
	
	@Column(name = "RESERVOIR_TYPE")
	@Enumerated(EnumType.STRING)
	private ReservoirType reservoirType;
	
	@Size(min = 3, max = 6, message = "Mecano Code length should be between 3 and 6 characters !")
	@Column(name = "MECANO_CODE")
	private String mecanoCode;
	
	@Min(value = (long) -85.05112878, message = "min value is -85.05112878")
	@Max(value =  (long) 85.05112878, message = "max value is 85.05112878")
	@Column(name="LATITUDE")
	private BigDecimal latitude;
	
	@Min(value = -180, message = "min value is -180")
	@Max(value = 180, message = "max value is 180")
	@Column(name = "LONGITUDE")
	private BigDecimal longitude;
	
	/*
	 * TECH : Prevent recursive association in ManyToOne Hibernate (@JsonManagedReference, @JsonBackReference)
	 *        -> use @JsonIgnore on one side of the ManyToOne relationship to break the cycle
	 */
	
	@ManyToOne 
	@JoinColumn(name = "PERIMETER", foreignKey = @ForeignKey(name = "FK_WELL_PERIMETER"))
	@JsonManagedReference
	private Perimeter perimeter;
	
	@ManyToOne
	@JoinColumn(name = "FIELD", foreignKey = @ForeignKey(name = "FK_WELL_FIELD"))
	@JsonManagedReference
	private Field field;
	
	@OneToMany(mappedBy = "well", cascade = CascadeType.ALL)
	private List<Perforation> perfos = new ArrayList<>();
	
	@OneToMany(mappedBy = "well", cascade = CascadeType.ALL)
	private List<WellTest> tests = new ArrayList<>();
	
	@PrePersist
	protected void prePersiste() {
		this.creationDate = LocalDateTime.now();
	}
	
	public Well() {

	}

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

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
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

	public ReservoirType getReservoirType() {
		return reservoirType;
	}

	public void setReservoirType(ReservoirType reservoirType) {
		this.reservoirType = reservoirType;
	}
	
}
