package com.vc.dao;


import java.sql.SQLException;
import java.util.List;

import com.vc.entity.Country;
import com.vc.model.CountryModel;

public interface ICountryDAO  extends IBaseDAO<Country> {

	
	List<CountryModel> getDecresingByNumOfCity() throws SQLException;
	
	List<CountryModel> getDecresingByDensity() throws SQLException;
	
}

