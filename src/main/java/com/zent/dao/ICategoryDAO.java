package com.zent.dao;

import java.util.List;

import com.zent.entity.Category;

public interface ICategoryDAO {
	public List<Category> getAll(Category category, Integer page);
	public Integer countAll(Category category);
	public void deleteCategory(Integer id);
	public void addCategory(Category category);
	public Category getCategoryById(Integer id);
	public void updateCategory(Category category, Integer id);
}
