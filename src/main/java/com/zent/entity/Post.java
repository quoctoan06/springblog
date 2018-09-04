package com.zent.entity;

public class Post {
	private Integer id;
	private Integer categoryId;
	private String categoryName;
	private String code;
	private String title;
	private String content;
	private Integer authorId;
	private String authorName;
	private String date;
	private String image;
	private String error;

	// constructors
	
	public Post() {
		super();
	}
	
	public Post(Integer id, Integer categoryId, String categoryName, String code, String title, String content,
			Integer authorId, String authorName, String date, String image) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.code = code;
		this.title = title;
		this.content = content;
		this.authorId = authorId;
		this.authorName = authorName;
		this.date = date;
		this.image = image;
	}

	public Post(Integer categoryId, String categoryName, String code, String title, String content,
			int authorId, String authorName, String date, String image) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.code = code;
		this.title = title;
		this.content = content;
		this.authorId = authorId;
		this.authorName = authorName;
		this.date = date;
		this.image = image;
	}

	// getters and setters
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
