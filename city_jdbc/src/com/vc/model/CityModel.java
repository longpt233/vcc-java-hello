package com.vc.model;

public class CityModel {

	private int id;
	private String name;
	private int poplation;
	private boolean capital;
	private String name_country;
	
	

	public CityModel(int id, String name, int poplation, boolean capital, String name_country) {
		super();
		this.id = id;
		this.name = name;
		this.poplation = poplation;
		this.capital = capital;
		this.name_country = name_country;
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

	public int getPoplation() {
		return poplation;
	}

	public void setPoplation(int poplation) {
		this.poplation = poplation;
	}
	
	

	public boolean isCapital() {
		return capital;
	}

	public void setCapital(boolean capital) {
		this.capital = capital;
	}

	public String getname_country() {
		return name_country;
	}

	public void setname_country(String name_country) {
		this.name_country = name_country;
	}

	@Override
	public String toString() {
		return "CityModel [id=" + id + ", name=" + name + ", poplation=" + poplation + ", capital=" + capital
				+ ", name_country=" + name_country + "]";
	}

	

	
	
	
}
