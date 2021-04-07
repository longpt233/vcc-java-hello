package com.example.entity;

import java.util.Date;

 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
 

@Entity 
@Table(name = "idcard")
public class IdCard {

	
	
	public IdCard() {
		
	}

	public IdCard(int id, @NotNull String name, @NotNull String idNumber, @NotNull String address,
			@NotNull Date birth) {
		this.id = id;
		this.name = name;
		this.idNumber = idNumber;
		this.address = address;
		this.birth = birth;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotNull 
	//@Column(nullable=false) // khong thuc hien validate du lieu ma loi ban ra luc chay cau lenh vao db
	private String name;
	
	@NotNull 
	private String idNumber;

	@NotNull
	private String address;
	
	@NotNull
	private Date birth;

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

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	
	 

	 
	 
	
}
