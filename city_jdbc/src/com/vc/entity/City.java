package com.vc.entity;

public class City {

	private int id;
	private String name;
	private int poplation;
	private boolean capital;
	private String codeCountry;
	
	

	public City(int id, String name, int poplation, boolean capital, String codeCountry) {
		super();
		this.id = id;
		this.name = name;
		this.poplation = poplation;
		this.capital = capital;
		this.codeCountry = codeCountry;
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

	public String getCodeCountry() {
		return codeCountry;
	}

	public void setCodeCountry(String codeCountry) {
		this.codeCountry = codeCountry;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", poplation=" + poplation + ", capital=" + capital
				+ ", codeCountry=" + codeCountry + "]";
	}

	
	
	
}
