package io.laidani.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PERFORATIONS")
public class Perforation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UID")
	private int uid;
	
	@Column(name="STARTING_FROM")
	private long from;
	@Column(name="UP_TO")
	private long to;
	@Column(name="DATE_OF_PERFO")
	private LocalDate dateOfPerfo;
	
	@ManyToOne
	@JoinColumn(name = "WUID", foreignKey = @ForeignKey(name = "FK_PERFO_WELL"))
	private Well well;
	
	@OneToOne
    @JoinColumn(name="CUID", foreignKey = @ForeignKey(name = "FK_PERFO_COMPANY"))
	private ServiceCompany company;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {   
		this.uid = uid;
	}

	public long getFrom() {
		return from;
	}

	public void setFrom(long from) {
		this.from = from;
	}

	public long getTo() {
		return to;
	}

	public void setTo(long to) {
		this.to = to;
	}

	public LocalDate getDateOfPerfo() {
		return dateOfPerfo;
	}

	public void setDateOfPerfo(LocalDate dateOfPerfo) {
		this.dateOfPerfo = dateOfPerfo;
	}

	public Well getWell() {
		return well;
	}

	public void setWell(Well well) {
		this.well = well;
	}

	public ServiceCompany getCompany() {
		return company;
	}

	public void setCompany(ServiceCompany company) {
		this.company = company;
	}
	
}
