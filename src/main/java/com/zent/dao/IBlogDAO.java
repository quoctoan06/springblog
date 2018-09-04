package com.zent.dao;

import java.util.List;

import com.zent.entity.Category;
import com.zent.entity.Post;
import com.zent.entity.User;

public interface IBlogDAO {
	public User getAdmin();
	public List<Post> getNewPosts();
	public List<Category> getCategories();
	public Post getPostById(Integer id);
	public List<Post> getPostsByCategory(Integer id, Integer page);
	public Integer countAllBlogByCategory(Integer id);
}
