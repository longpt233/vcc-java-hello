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
		String sql = "SELECT c2.id_city,c2.name_city,c2.capital,co2.name_country, c2.population\r\n"
				+ "from (	SELECT co.code_country, max(c.population) as max_population\r\n"
				+ "		FROM city c\r\n"
				+ "			join country co on c.code_country = co.code_country\r\n"
				+ "		GROUP BY co.code_country) t\r\n"
				+ "join city c2 on c2.code_country = t.code_country and c2.population = t.max_population\r\n"
				+ "join country co2 on co2.code_country = t.code_country;";

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
		String sql = "SELECT id_city,name_city,capital,t.name_country, population\r\n"
				+ "FROM   (SELECT co.continent, co.name_country, max(c.population) max_population \r\n"
				+ "		FROM city c \r\n"
				+ "			join country co on c.code_country = co.code_country\r\n"
				+ "		GROUP BY co.continent) t \r\n"
				+ "join ( SELECT c2.id_city,c2.name_city,c2.capital,co2.name_country, co2.continent, c2.population\r\n"
				+ "		FROM city c2,country co2\r\n"
				+ "       WHERE c2.code_country = co2.code_country\r\n"
				+ "       ) city_country\r\n"
				+ "on t.max_population= city_country.population\r\n"
				+ " AND t.continent=city_country.continent\r\n"
				+ "";

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
		String sql = "SELECT id_city,name_city,capital,t.name_country, population\r\n"
				+ "FROM   (SELECT co.continent, co.name_country, max(c.population) max_population \r\n"
				+ "		FROM city c \r\n"
				+ "			join country co on c.code_country = co.code_country\r\n"
				+ "		WHERE c.capital=1\r\n"
				+ "		GROUP BY co.continent) t \r\n"
				+ "join ( SELECT c2.id_city,c2.name_city,c2.capital,co2.name_country, co2.continent, c2.population\r\n"
				+ "		FROM city c2,country co2\r\n"
				+ "       WHERE c2.code_country = co2.code_country\r\n"
				+ "			/*WHERE c.capital=1  de day la sai */\r\n"
				+ "       ) city_country\r\n"
				+ "on t.max_population= city_country.population\r\n"
				+ " AND t.continent=city_country.continent";

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
