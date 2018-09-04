package com.zent.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zent.entity.Category;
import com.zent.mapper.CategoryMapper;
import com.zent.util.Constant;

@Repository("categoryDAO")
public class CategoryDAO implements ICategoryDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	// getters and setters
	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	// Override
	
	public List<Category> getAll(Category category, Integer page) {
		List<Category> list = new ArrayList<Category>();
		String sql = "SELECT * FROM category WHERE 1=1 ";
		Integer count = -1;
		
		if (category.getName() != null) {
			sql = sql + " AND name LIKE ? ";
			count++;
		}
		
		sql = sql + " LIMIT " + (page - 1) * Constant.PAGE_SIZE_CATEGORY + " , " + Constant.PAGE_SIZE_CATEGORY;
		
		if(count>-1) {
			Object args[] = new Object[count+1];
			args[count] = "%" + category.getName() + "%";
			list = jdbcTemplate.query(sql, args, new CategoryMapper());
		} else {
			list = jdbcTemplate.query(sql, new CategoryMapper());
		}
		return list;
	}

	public Integer countAll(Category category) {
		String sql = "SELECT COUNT(*) FROM category WHERE 1=1 ";
		Integer count = -1;
		Integer countAll = 0;
		
		if (category.getName() != null) {
			sql = sql + " AND name LIKE ? ";
			count++;
		}
		
		if(count>-1) {
			Object args[] = new Object[count+1];
			args[count] = "%" + category.getName() + "%";
			countAll = jdbcTemplate.queryForObject(sql, args, Integer.class);
		} else {
			countAll = jdbcTemplate.queryForObject(sql, Integer.class);
		}
		return countAll;
	}

	public void deleteCategory(Integer id) {
		String sql = "DELETE FROM category WHERE id = ?";
		Object args [] = new Object[1];
		args[0] = id;
		jdbcTemplate.update(sql,args);
		
	}

	public void addCategory(Category category) {
		String sql = "INSERT INTO category(name,description,code) VALUES (?,?,?)";
		Object args[] = new Object [3];
		args[0] = category.getName();
		args[1] = category.getDescription();
		args[2] = category.getCode();
		jdbcTemplate.update(sql,args);
		
	}

	public Category getCategoryById(Integer id) {
		List<Category> list = new ArrayList<Category>();
		String sql ="SELECT * FROM category WHERE id = ?";
		Object args[] = new Object[1];
		args[0] = id;
		list = jdbcTemplate.query(sql, args, new CategoryMapper());
		return list.get(0);
	}

	public void updateCategory(Category category, Integer id) {
		String sql = "UPDATE category SET name = ?, description = ?, code = ? WHERE id = ?";
		Object args[] = new Object[4];
		args[0] = category.getName();
		args[1] = category.getDescription();
		args[2] = category.getCode();
		args[3] = id;
		jdbcTemplate.update(sql,args);
		
	}

}
