package com.zent.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zent.entity.Category;
import com.zent.entity.Post;
import com.zent.entity.User;
import com.zent.mapper.CategoryMapper;
import com.zent.mapper.PostMapper;
import com.zent.util.Constant;

@Repository("blogDAO")
public class BlogDAO implements IBlogDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public User getAdmin() {
		return null;
	}

	public List<Post> getNewPosts() {
		String sql = "SELECT p.*, c.name, u.fullname"
				+ " FROM post p, category c, user u\r\n" + " WHERE \r\n"
				+ "	p.categoryId = c.id AND p.authorId = u.id"
				+ " ORDER BY p.id DESC"
				+ " LIMIT 6";
		List<Post> list = new ArrayList<Post>();
		list = jdbcTemplate.query(sql, new PostMapper());
		return list;
	}

	public List<Category> getCategories() {
		String sql = " SELECT * FROM category";
		List<Category> list = new ArrayList<Category>();
		list = jdbcTemplate.query(sql, new CategoryMapper());
		return list;
	}

	public Post getPostById(Integer id) {
		String sql = "SELECT p.*, c.name, u.fullname "
				+ "FROM post p, category c, user u\r\n " + "WHERE \r\n"
				+ "	p.categoryId = c.id AND p.authorId = u.id AND p.id = ?";
		Object args[] = new Object[1];
		args[0] = id;
		List<Post> list = new ArrayList<Post>();
		list = jdbcTemplate.query(sql, args, new PostMapper());
		return list.get(0);
	}

	public List<Post> getPostsByCategory(Integer id, Integer page) {
		List<Post> list = new ArrayList<Post>();
		String sql = "SELECT p.*, c.name, u.fullname"
				+ " FROM post p, category c, user u\r\n " + "WHERE \r\n"
				+ "	p.categoryId = c.id AND p.authorId = u.id"
				+ " AND c.id = ?"
				+ " ORDER BY p.id DESC";

		sql = sql + " LIMIT " + (page - 1) * Constant.PAGE_SIZE_BLOG + " , " + Constant.PAGE_SIZE_BLOG;

		Object args[] = new Object[1];
		args[0] = id;
		list = jdbcTemplate.query(sql, args, new PostMapper());
		return list;
	}

	public Integer countAllBlogByCategory(Integer id) {
		Integer count = 0;
		String sql = "SELECT COUNT(*) FROM post p, category c, user u\r\n " + "WHERE \r\n"
				+ "	p.categoryId = c.id AND p.authorId = u.id  AND c.id = ?";
		Object args[] = new Object[1];
		args[0] = id;
		count = jdbcTemplate.queryForObject(sql, args, Integer.class);
		return count;
	}

}
