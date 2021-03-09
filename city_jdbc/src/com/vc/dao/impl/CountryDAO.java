package com.vc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vc.conn.MySql;
import com.vc.dao.ICountryDAO;
import com.vc.entity.Country;
import com.vc.model.CityModel;
import com.vc.model.CountryModel;

public class CountryDAO implements ICountryDAO{

	@Override
	public List<Country> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Country insert(Country t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Country t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CountryModel> getDecresingByNumOfCity() {
		String sql = "SELECT country.code_country,name_country,continent,suface_area,country.population,gnp, count(city.id_city)\r\n"
				+ "FROM	city, country\r\n"
				+ "WHERE	city.code_country= country.code_country\r\n"
				+ "GROUP BY country.code_country\r\n"
				+ "ORDER BY count(city.id_city) DESC";

		List<CountryModel> results = new ArrayList<>();
		try {
			Connection conn = MySql.getConn();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String id = resultSet.getString("code_country");
				String name = resultSet.getString("name_country");
				String con = resultSet.getString("continent");
				double area = resultSet.getDouble("suface_area");
				int popu= resultSet.getInt("population");
				double gnp = resultSet.getDouble("gnp");
				results.add(new CountryModel(id, name, con,area,popu,gnp));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return results.isEmpty() ? null : results;
	}

	@Override
	public List<CountryModel> getDecresingByDensity() {
		String sql = "SELECT country.code_country,name_country,continent,suface_area,country.population,gnp, population/suface_area as density\r\n"
				+ "from country\r\n"
				+ "WHERE population != 0\r\n"
				+ "ORDER BY density DESC";

		List<CountryModel> results = new ArrayList<>();
		try {
			Connection conn = MySql.getConn();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String id = resultSet.getString("code_country");
				String name = resultSet.getString("name_country");
				String con = resultSet.getString("continent");
				double area = resultSet.getDouble("suface_area");
				int popu= resultSet.getInt("population");
				double gnp = resultSet.getDouble("gnp");
				results.add(new CountryModel(id, name, con,area,popu,gnp));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return results.isEmpty() ? null : results;
	}

}
