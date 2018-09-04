package com.zent.dao;

import java.util.List;

import com.zent.entity.Category;
import com.zent.entity.Post;
import com.zent.entity.User;

public interface IPostDAO {
	public List<Post> getAll(Post post, Integer page);
	public Integer countAll(Post post);
	public void deletePost(Integer id);
	public void updatePost(Post post,Integer id);
	public void add(Post post);
	public Post getPostById(Integer id);
	public List<User> getAuthor();
	public List<Category> getCategory();
	public String getCategoryNameById(Integer id);
}
