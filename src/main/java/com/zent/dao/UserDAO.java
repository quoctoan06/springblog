package com.zent.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zent.entity.User;
import com.zent.mapper.UserMapper;
import com.zent.util.Constant;

@Repository("userDAO")
public class UserDAO implements IUserDAO {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	// Override functions
	
	public User checkLogin(User user) {
		String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
		Object args[] = new Object[2];
		args[0] = user.getUsername();
		args[1] = user.getPassword();
		List<User> list = jdbcTemplate.query(sql, args, new UserMapper());
		if(list.isEmpty())
			return null;
		return list.get(0);
	}

	public void changeProfile(User user, Integer id) {
		String sql = "UPDATE user SET fullname = ?,birthday = ?,address = ?,phone = ?,email = ? WHERE id = ?";
		Object args[] = new Object[6];
		args[0] = user.getFullName();
		args[1] = user.getBirthday();
		args[2] = user.getAddress();
		args[3] = user.getPhone();
		args[4] = user.getEmail();
		args[6] = id;
		jdbcTemplate.update(sql, args);
	}

	public void add(User user) {
		String sql = "INSERT INTO user(username,password,fullname,birthday,address,phone,email,image) VALUES (?,?,?,?,?,?,?,?)";
		Object args[] = new Object[8];
		args[0] = user.getUsername();
		args[1] = user.getPassword();
		args[2] = user.getFullName();
		args[3] = user.getBirthday();
		args[4] = user.getAddress();
		args[5] = user.getPhone();
		args[6] = user.getEmail();
		args[7] = user.getImage();
		jdbcTemplate.update(sql, args);
		
	}

	public Integer countUserByUserName(String username) {
		Integer count = 0;
		String sql = "SELECT COUNT(*) FROM user ";
		if(username != null) {
			sql = sql + "AND username = ?";
		}
		Object args[] = new Object[1];
		args[0] = username;
		count = jdbcTemplate.queryForObject(sql, args, Integer.class);
		return count;
	}

	public List<User> getAll(User user, Integer page) {
		List<User> list = new ArrayList<User>();
		String sql = "SELECT * FROM user WHERE 1=1 ";
		Integer count = -1;
		if(user.getFullName() != null) {
			sql = sql + " AND fullname LIKE ? ";
			count++;
		}
		
		// LIMIT m, n --> offset of the first row to return, maximum number of rows to return
		sql = sql + " LIMIT " + (page - 1) * Constant.PAGE_SIZE_USER + " , " + Constant.PAGE_SIZE_USER;	
		
		if(count > -1) {
			Object args[] = new Object[count + 1];
			args[count] = "%" + user.getFullName() + "%";
			list = jdbcTemplate.query(sql, args, new UserMapper());
		} else {
			list = jdbcTemplate.query(sql, new UserMapper());
		}
		return list;
	}

	public Integer countAll(User user) {
		String sql = "SELECT COUNT(*) FROM user WHERE 1=1 ";
		Integer count = -1;
		Integer countAll = 0;
		if (user.getFullName() != null) {
			sql = sql + " AND fullname LIKE ? ";
			count++;
		}
		
		if (count > -1) {
			Object args[] = new Object[count + 1];
			args[count] = "%" + user.getFullName() + "%";
			countAll = jdbcTemplate.queryForObject(sql, args, Integer.class);
		} else {
			countAll = jdbcTemplate.queryForObject(sql, Integer.class);
		}
		
		return countAll;
	}

	public void delete(Integer id) {
		String sql = "DELETE FROM user WHERE id = ?";
		Object args[] = new Object[1];
		args[0] = id;
		jdbcTemplate.update(sql, args);
		
	}

}
