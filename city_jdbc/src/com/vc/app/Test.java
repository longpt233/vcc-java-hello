package com.vc.app;

import java.sql.SQLException; 
import com.vc.dao.impl.CityDAO;
import com.vc.dao.impl.CountryDAO; 

public class Test {

	
	public static void main(String[] args) {
//		try {
//			new CityDAO().findBigCapitalCity().stream().forEach(s-> System.out.println(s));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
		CityDAO cityDAO=new CityDAO();
		CountryDAO countryDAO=new CountryDAO();
		try {
			System.out.println("1.");
			cityDAO.findBigCityOfCountry().stream().forEach(s-> System.out.println(s));
			
			System.out.println("2.");
			cityDAO.findBigCityOfContinent().stream().forEach(s-> System.out.println(s));
			
			System.out.println("3.");
			System.out.println(cityDAO.findBigCapitalCity().toString());
			
			System.out.println("4.");
			cityDAO.findBigCApitalCityOfContinient().stream().forEach(s-> System.out.println(s));
			
			System.out.println("5.");
			countryDAO.getDecresingByNumOfCity().stream().forEach(s-> System.out.println(s));
			
			System.out.println("6.");
			countryDAO.getDecresingByDensity().stream().forEach(s-> System.out.println(s));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
