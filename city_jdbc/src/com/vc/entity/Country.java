package com.vc.entity;

public class Country {

	String codeCountry;
	String nameCountry;
	String contient;
	double suface_area;
	int population;
	double gnp;
	public Country(String codeCountry, String nameCountry, String contient, double suface_area, int population,
			double gnp) {
		super();
		this.codeCountry = codeCountry;
		this.nameCountry = nameCountry;
		this.contient = contient;
		this.suface_area = suface_area;
		this.population = population;
		this.gnp = gnp;
	}
	public String getCodeCountry() {
		return codeCountry;
	}
	public void setCodeCountry(String codeCountry) {
		this.codeCountry = codeCountry;
	}
	public String getNameCountry() {
		return nameCountry;
	}
	public void setNameCountry(String nameCountry) {
		this.nameCountry = nameCountry;
	}
	public String getContient() {
		return contient;
	}
	public void setContient(String contient) {
		this.contient = contient;
	}
	public double getSuface_area() {
		return suface_area;
	}
	public void setSuface_area(double suface_area) {
		this.suface_area = suface_area;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public double getGnp() {
		return gnp;
	}
	public void setGnp(double gnp) {
		this.gnp = gnp;
	}
	
	
	
	
}
