package io.laidani.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "WELL_TESTS")
public class WellTest {

	@Id
	@GeneratedValue()
	private int uid;
	
	@ManyToOne
	@JoinColumn(name = "WUID", foreignKey = @ForeignKey(name = "FK_WELLTEST_WELL"))
	private Well well;
	
	@Column(name = "TEST_DATE")
	private LocalDate testDate;
	
	// TODO : change the type of the duse
	@Column(name = "DUSE")
	private String duse;
	
	@Column(name = "RATE")
	private long rate;
	
	// Oil Quantity (M3/day)
	@Column(name = "OIL_QTY")
	private long oilQuantity;
	
	@Column(name = "GAS_FORMED")
	private long gazFormed;
	
	@Column(name = "GAS_INJECTED")
	private long gazInjected;
	
	@Column(name = "GOR")
	private long gasOilRatio;
	
	// Water Quantity (M3/day)
	@Column(name = "WATER_QTY")
	private long waterQuantity;
	
	// Oil Percentage (%)
	@Column(name = "WATER_PERCENTAGE")
	private long waterPercentage;
	
	// Pressures
	
	@Column(name = "HIGH_PRESSURE")
	private long HighPressure;
	@Column(name = "LOW_PRESSURE")
	private long LowPressure;
	@Column(name = "MEDIUM_PRESSURE")
	private long MediumPressure;
	@Column(name = "TBG_PRESSURE")
	private long TubingPressure;
	@Column(name = "CSG_PRESSURE")
	private long CasingPressure;
	@Column(name = "SEP_PRESSURE")
	private long SeparatorPressure;
	
	@Column(name = "TEST_STATUS")
	@Enumerated(EnumType.STRING)
	private WellTestStatus status;	
	
	@OneToOne
	@JoinColumn(name = "COMPANY", foreignKey = @ForeignKey(name = "FK_WELLTEST_COMPANY"))
	private ServiceCompany company;
	
	@Column(name = "COMMENT")
	private String comment;
	
	@Column(name = "REPORTED_ON")
	private LocalDateTime reportedOn;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Well getWell() {
		return well;
	}

	public void setWell(Well well) {
		this.well = well;
	}

	public LocalDate getTestDate() {
		return testDate;
	}

	public void setTestDate(LocalDate testDate) {
		this.testDate = testDate;
	}

	public String getDuse() {
		return duse;
	}

	public void setDuse(String duse) {
		this.duse = duse;
	}

	public long getRate() {
		return rate;
	}

	public void setRate(long rate) {
		this.rate = rate;
	}

	public long getOilQuantity() {
		return oilQuantity;
	}

	public void setOilQuantity(long oilQuantity) {
		this.oilQuantity = oilQuantity;
	}

	public long getGazFormed() {
		return gazFormed;
	}

	public void setGazFormed(long gazFormed) {
		this.gazFormed = gazFormed;
	}

	public long getGazInjected() {
		return gazInjected;
	}

	public void setGazInjected(long gazInjected) {
		this.gazInjected = gazInjected;
	}

	public long getGasOilRatio() {
		return gasOilRatio;
	}

	public void setGasOilRatio(long gasOilRatio) {
		this.gasOilRatio = gasOilRatio;
	}

	public long getWaterQuantity() {
		return waterQuantity;
	}

	public void setWaterQuantity(long waterQuantity) {
		this.waterQuantity = waterQuantity;
	}

	public long getWaterPercentage() {
		return waterPercentage;
	}

	public void setWaterPercentage(long waterPercentage) {
		this.waterPercentage = waterPercentage;
	}

	public long getHighPressure() {
		return HighPressure;
	}

	public void setHighPressure(long highPressure) {
		HighPressure = highPressure;
	}

	public long getLowPressure() {
		return LowPressure;
	}

	public void setLowPressure(long lowPressure) {
		LowPressure = lowPressure;
	}

	public long getMediumPressure() {
		return MediumPressure;
	}

	public void setMediumPressure(long mediumPressure) {
		MediumPressure = mediumPressure;
	}

	public long getTubingPressure() {
		return TubingPressure;
	}

	public void setTubingPressure(long tubingPressure) {
		TubingPressure = tubingPressure;
	}

	public long getCasingPressure() {
		return CasingPressure;
	}

	public void setCasingPressure(long casingPressure) {
		CasingPressure = casingPressure;
	}

	public long getSeparatorPressure() {
		return SeparatorPressure;
	}

	public void setSeparatorPressure(long separatorPressure) {
		SeparatorPressure = separatorPressure;
	}

	public WellTestStatus getStatus() {
		return status;
	}

	public void setStatus(WellTestStatus status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getReportedOn() {
		return reportedOn;
	}

	public void setReportedOn(LocalDateTime reportedOn) {
		this.reportedOn = reportedOn;
	}

	public ServiceCompany getCompany() {
		return company;
	}

	public void setCompany(ServiceCompany company) {
		this.company = company;
	}
		
}
