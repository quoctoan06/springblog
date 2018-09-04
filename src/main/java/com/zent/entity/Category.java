package com.zent.entity;

public class Category {
	private Integer id;
	private String name;
	private String description;
	private String code;
	private String error;
	
	// constructors
	public Category(Integer id, String name, String description, String code) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.code = code;
	}

	public Category() {
		super();
	}

	// getters and setters
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
