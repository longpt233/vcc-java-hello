package com.vc.dao;

import java.sql.SQLException;
import java.util.List;

public interface IBaseDAO<T> {
	
	List<T> findAll() throws SQLException;

	T insert(T t) throws SQLException;

	boolean update(T t) throws SQLException;

	boolean delete(int id) throws SQLException;
}
