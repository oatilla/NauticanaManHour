package com.nauticana.manhour.model;

public class LoginBean {

	private String username;
	private String password;
	private String language;
	
	public LoginBean(String username, String password, String language) {
		super();
		this.username = username;
		this.password = password;
		this.language = language;
	}
	
	public LoginBean() {
		
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
}

