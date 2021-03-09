package com.vc.dao;

import java.sql.SQLException;
import java.util.List;

import com.vc.entity.City;
import com.vc.model.CityModel;

public interface ICityDAO  extends IBaseDAO<City>{

	List<CityModel> findBigCityOfCountry() throws SQLException;
	
	List<CityModel>  findBigCityOfContinent() throws SQLException;
	
	CityModel findBigCapitalCity() throws SQLException;
	
	List<CityModel>  findBigCApitalCityOfContinient()throws SQLException;
	
	
}
