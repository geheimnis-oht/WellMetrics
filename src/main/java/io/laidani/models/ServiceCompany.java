package io.laidani.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "name")
public class ServiceCompany {

	@Id
	@GeneratedValue()
	@Column(name="UID")
	private int uid;
	
	@Column(name = "NAME")
	private String name;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "TELEPHONE")
	private String telephone;
	@Column(name = "EMAIL")
	private String email;
	
	@OneToOne (mappedBy = "company")
	private Perforation perforation;
	
	@OneToOne (mappedBy = "company")
	private WellTest wellTest;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Perforation getPerforation() {
		return perforation;
	}

	public void setPerforation(Perforation perforation) {
		this.perforation = perforation;
	}

	public WellTest getWellTest() {
		return wellTest;
	}

	public void setWellTest(WellTest wellTest) {
		this.wellTest = wellTest;
	}
	
}
