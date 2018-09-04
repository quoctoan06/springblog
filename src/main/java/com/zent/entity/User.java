package com.zent.entity;

public class User {
	private Integer id;
	private String username;
	private String password;
	private String fullName;
	private String birthday;
	private String address;
	private String phone;
	private String email;
	private String image;
	
	// constructors
	public User() {
		super();
	}

	public User(Integer id, String username, String password, String fullName, String birthday, String address,
			String phone, String email, String image) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.birthday = birthday;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.image = image;
	}
	
	public User(String username, String password, String fullName, String birthday, String address, String phone,
			String email, String image) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.birthday = birthday;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.image = image;
	}
	
	// getters and setters
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
