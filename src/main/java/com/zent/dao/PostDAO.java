package com.zent.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.zent.entity.Category;
import com.zent.entity.Post;
import com.zent.entity.User;
import com.zent.mapper.CategoryMapper;
import com.zent.mapper.PostMapper;
import com.zent.mapper.UserMapper;
import com.zent.util.Constant;

@Repository("postDAO")
public class PostDAO implements IPostDAO {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	
	public List<Post> getAll(Post post, Integer page) {
		List<Post> list = new ArrayList<Post>();
		String sql = "SELECT p.*, c.name, u.fullname "
				+ "FROM post p, category c, user u\r\n " + "WHERE \r\n"
				+ "	p.categoryId = c.id AND p.authorId = u.id ";
		Integer count = -1;
		HashMap<Integer, Object> map = new HashMap<Integer, Object>();
		
		if (post.getTitle() != null) {
			sql = sql + " AND p.title LIKE ? ";
			count++;
			map.put(count, "%" + post.getTitle() + "%");
		}
		
		if (post.getDate() != null) {
			sql = sql + " AND p.date LIKE ? ";
			count++;
			map.put(count, "%" + post.getDate() + "%");
		}
		
		if (post.getAuthorName() != null) {
			sql = sql + " AND u.fullname LIKE ? ";
			count++;
			map.put(count, "%" + post.getAuthorName() + "%");
		}
		
		sql = sql + " LIMIT " + (page - 1) * Constant.PAGE_SIZE_POST + " , " + Constant.PAGE_SIZE_POST;
		
		if (count > -1) {
			Object args[] = new Object[count + 1];
			for (Integer i : map.keySet()) {
				args[i] = map.get(i);
			}

			list = jdbcTemplate.query(sql, args, new PostMapper());
		} else {
			list = jdbcTemplate.query(sql, new PostMapper());
		}
		
		return list;
	}

	public Integer countAll(Post post) {
		Integer countAll = 0;
		String sql = "SELECT p.*, c.name, u.fullname "
				+ "FROM post p, category c, user u\r\n " + "WHERE \r\n"
				+ "	p.categoryId = c.id AND p.authorId = u.id ";
		Integer count = -1;
		HashMap<Integer, Object> map = new HashMap<Integer, Object>();
		
		if (post.getTitle() != null) {
			sql = sql + " AND p.title LIKE ? ";
			count++;
			map.put(count, "%" + post.getTitle() + "%");
		}
		
		if (post.getDate() != null) {
			sql = sql + " AND p.date LIKE ? ";
			count++;
			map.put(count, "%" + post.getDate() + "%");
		}
		
		if (post.getAuthorName() != null) {
			sql = sql + " AND u.fullname LIKE ? ";
			count++;
			map.put(count, "%" + post.getAuthorName() + "%");
		}
		
		if (count > -1) {
			Object args[] = new Object[count + 1];
			for (Integer i : map.keySet()) {
				args[i] = map.get(i);
			}
			countAll = jdbcTemplate.queryForObject(sql, args, Integer.class);
		} else {
			countAll = jdbcTemplate.queryForObject(sql, Integer.class);
		}

		return countAll;
	}

	public void deletePost(Integer id) {
		String sql = "DELETE FROM post WHERE id = ?";
		Object args[] = new Object[1];
		args[0] = id;
		jdbcTemplate.update(sql, args);
		
	}

	public void updatePost(Post post, Integer id) {
		String sql = "UPDATE post SET categoryId = ? , categoryName = ?, code = ? , title = ? , content = ? , authorId = ? , authorName = ?, image = ? WHERE id = ?";
		Object args[] = new Object[9];
		args[0] = post.getCategoryId();
		args[1] = post.getCategoryName();
		args[2] = post.getCode();
		args[3] = post.getTitle();
		args[4] = post.getContent();
		args[5] = post.getAuthorId();
		args[6] = post.getAuthorName();
		args[7] = post.getImage();
		args[8] = id;
		jdbcTemplate.update(sql, args);
		
	}

	public void add(Post post) {
		String sql = "INSERT INTO post(categoryId,categoryName,code,title,content,authorId,authorName,date,image) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		Object args[] = new Object[9];
		args[0] = post.getCategoryId();
		args[1] = post.getCategoryName();
		args[2] = post.getCode();
		args[3] = post.getTitle();
		args[4] = post.getContent();
		args[5] = post.getAuthorId();
		args[6] = post.getAuthorName();
		args[7] = post.getDate();
		args[8] = post.getImage();
		jdbcTemplate.update(sql, args);
		
	}

	public Post getPostById(Integer id) {
		List<Post> list = new ArrayList<Post>();
		String sql = "SELECT p.*, c.name, u.fullname "
				+ "FROM post p, category c, user u\r\n " + "WHERE \r\n"
				+ "	p.categoryId = c.id AND p.authorId = u.id AND p.id = ?";
		
		Object args[] = new Object[1];
		args[0] = id;
		list = jdbcTemplate.query(sql, args, new PostMapper());
		return list.get(0);
	}

	public List<User> getAuthor() {
		String sql = "SELECT * FROM user";
		List<User> list = new ArrayList<User>();
		list = jdbcTemplate.query(sql, new UserMapper());
		return list;
	}

	public List<Category> getCategory() {
		String sql = "SELECT * FROM category";
		List<Category> list = new ArrayList<Category>();
		list = jdbcTemplate.query(sql, new CategoryMapper());
		return list;
	}
	
	public String getCategoryNameById(Integer id) {
		String sql = "SELECT name FROM category WHERE id = ?";
		Object args[] = new Object[1];
		args[0] = id;
		String categoryName = jdbcTemplate.queryForObject(sql, args, String.class);
		return categoryName;
	}

}
