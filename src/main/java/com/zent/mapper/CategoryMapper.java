package com.zent.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zent.entity.Category;

public class CategoryMapper implements RowMapper<Category> {

	public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
		Category category = new Category(
				rs.getInt(1), 
				rs.getString(2), 
				rs.getString(3), 
				rs.getString(4));
		
		return category;
	}
}
