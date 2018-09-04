package com.zent.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.zent.entity.Post;

public class PostMapper implements RowMapper<Post> {

	public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
		Post post = new Post(
				rs.getInt(1), 
				rs.getInt(2), 
				rs.getString(3),
				rs.getString(4),
				rs.getString(5), 
				rs.getString(6), 
				rs.getInt(7), 
				rs.getString(8),
				rs.getString(9),
				rs.getString(10));
		
		return post;
	}

}
