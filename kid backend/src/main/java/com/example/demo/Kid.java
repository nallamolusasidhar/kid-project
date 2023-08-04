package com.example.demo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Kid {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String className;
	private String section;
	private String fatherName;
	private String address;
	private String phone;
	@Column(length=1000000)
	private byte[] image;
	
	
	public Kid() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Kid(int id, String name, String className, String section, String fatherName, String address, String phone,
			byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.className = className;
		this.section = section;
		this.fatherName = fatherName;
		this.address = address;
		this.phone = phone;
		this.image = image;
	}
	public Kid( String name, String className, String section, String fatherName, String address, String phone,
			byte[] image) {
		super();
		this.name = name;
		this.className = className;
		this.section = section;
		this.fatherName = fatherName;
		this.address = address;
		this.phone = phone;
		this.image = image;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public String getSection() {
		return section;
	}


	public void setSection(String section) {
		this.section = section;
	}


	public String getFatherName() {
		return fatherName;
	}


	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
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


	public byte[] getImage() {
		return image;
	}


	public void setImage(byte[] image) {
		this.image = image;
	}	
	
	
	
}
