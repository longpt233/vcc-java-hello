package com.vc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vc.conn.MySql;
import com.vc.dao.ICityDAO;
import com.vc.entity.City;
import com.vc.model.CityModel;

public class CityDAO implements ICityDAO {

	@Override
	public List<City> findAll() throws SQLException {
		String sql = "SELECT * FROM city";

		List<City> results = new ArrayList<>();
		try {
			Connection conn = MySql.getConn();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id_city");
				String name = resultSet.getString("name_city");
				int population = resultSet.getInt("population");
				int isCapital= resultSet.getByte("capital");
				boolean isCapitalBolean;
				if(isCapital==1 ) 
					isCapitalBolean=true ;
				else 
					isCapitalBolean=false;
				String name_country = resultSet.getString("code_country");
				results.add(new City(id, name, population,isCapitalBolean,name_country));
			}
		} catch (SQLException e) {
			return null;
		}

		return results.isEmpty() ? null : results;
	}

	@Override
	public City insert(City t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(City t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CityModel>  findBigCityOfCountry() throws SQLException {
		String sql = "SELECT  id_city,name_city,capital,country.name_country, max(city.population)as population\r\n"
				+ "FROM	city, country\r\n"
				+ "WHERE	city.code_country= country.code_country\r\n"
				+ "GROUP BY name_country;";

		List<CityModel> results = new ArrayList<>();
		try {
			Connection conn = MySql.getConn();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id_city");
				String name = resultSet.getString("name_city");
				int population = resultSet.getInt("population");
				int isCapital= resultSet.getByte("capital");
				boolean isCapitalBolean;
				if(isCapital==1 ) 
					isCapitalBolean=true ;
				else 
					isCapitalBolean=false;
				String name_country = resultSet.getString("name_country");
				results.add(new CityModel(id, name, population,isCapitalBolean,name_country));
			}
		} catch (SQLException e) {
			return null;
		}

		return results.isEmpty() ? null : results;
	}

	@Override
	public List<CityModel>  findBigCityOfContinent()  throws SQLException{
		String sql = "SELECT  id_city,name_city,capital,country.name_country, max(city.population)as population\r\n"
				+ "FROM	city, country\r\n"
				+ "WHERE	city.code_country= country.code_country\r\n"
				+ "GROUP BY country.continent;";

		List<CityModel> results = new ArrayList<>();
		try {
			Connection conn = MySql.getConn();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id_city");
				String name = resultSet.getString("name_city");
				int population = resultSet.getInt("population");
				int isCapital= resultSet.getByte("capital");
				boolean isCapitalBolean;
				if(isCapital==1 ) 
					isCapitalBolean=true ;
				else 
					isCapitalBolean=false;
				String name_country = resultSet.getString("name_country");
				results.add(new CityModel(id, name, population,isCapitalBolean,name_country));
			}
		} catch (SQLException e) {
			return null;
		}

		return results.isEmpty() ? null : results;
	}

	@Override
	public CityModel findBigCapitalCity() throws SQLException {
		String sql = "SELECT  id_city,name_city,capital,country.name_country, city.population\r\n"
				+ "FROM	city, country\r\n"
				+ "WHERE	city.code_country= country.code_country\r\n"
				+ "        AND city.population in (SELECT MAX(population)\r\n"
				+ "								FROM city\r\n"
				+ "                                WHERE capital=1);";

		List<CityModel> results = new ArrayList<>();
		try {
			Connection conn = MySql.getConn();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id_city");
				String name = resultSet.getString("name_city");
				int population = resultSet.getInt("population");
				int isCapital= resultSet.getByte("capital");
				boolean isCapitalBolean;
				if(isCapital==1 ) 
					isCapitalBolean=true ;
				else 
					isCapitalBolean=false;
				String name_country = resultSet.getString("name_country");
				results.add(new CityModel(id, name, population,isCapitalBolean,name_country));
			}
		} catch (SQLException e) {
			return null;
		}

		return results.isEmpty() ? null : results.get(0);
	}

	@Override
	public List<CityModel>  findBigCApitalCityOfContinient() throws SQLException {
		String sql = "SELECT  id_city,name_city,capital,country.name_country, max(city.population)as population\r\n"
				+ "				FROM	city, country\r\n"
				+ "				WHERE	city.code_country= country.code_country\r\n"
				+ "						AND city.capital=1\r\n"
				+ "                        \r\n"
				+ "				GROUP BY country.continent;\r\n"
				+ "		";

		List<CityModel> results = new ArrayList<>();
		try {
			Connection conn = MySql.getConn();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id_city");
				String name = resultSet.getString("name_city");
				int population = resultSet.getInt("population");
				int isCapital= resultSet.getByte("capital");
				boolean isCapitalBolean;
				if(isCapital==1 ) 
					isCapitalBolean=true ;
				else 
					isCapitalBolean=false;
				String name_country = resultSet.getString("name_country");
				results.add(new CityModel(id, name, population,isCapitalBolean,name_country));
			}
		} catch (SQLException e) {
			return null;
		}

		return results.isEmpty() ? null : results;
	}


}
