package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name="USER")
public class User {
	@Id
	@GeneratedValue
	private String username;
	@Column(name="email")
	private String useremail;
	@Column(name="date")
	private String dob;
    @Column(name="address")
    
	private String address;
	@Column(name="phone")
	private String phone;
	@Column(name="posh") 
	private String posh;
	@Column(name="xfc")
	private String xfc;
	@Column(name="isms")
	private String isms;
    @Column(name="emp")
	private String emp;
	
	public User() {
		
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUseremail() {
		return useremail;
	}
	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPosh() {
		return posh;
	}
	public void setPosh(String posh) {
		this.posh = posh;
	}
	public String getXfc() {
		return xfc;
	}
	public void setXfc(String xfc) {
		this.xfc = xfc;
	}
	public String getIsms() {
		return isms;
	}
	public void setIsms(String isms) {
		this.isms = isms;
	}
	public String getEmp() {
		return emp;
	}
	public void setEmp(String emp) {
		this.emp = emp;
	}
	public User(String username, String useremail, String dob, String address, String phone, String posh,
			String xfc, String isms, String emp) {
		super();
		this.username = username;
		this.useremail = useremail;
		this.dob = dob;
		this.address = address;
		this.phone = phone;
		this.posh = posh;
		this.xfc = xfc;
		this.isms = isms;
		this.emp = emp;
	}
	
	

}
