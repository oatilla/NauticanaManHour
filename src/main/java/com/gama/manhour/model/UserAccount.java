package com.gama.manhour.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "USER_ACCOUNT", schema = "ADSAAT")
public class UserAccount implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String[] fieldNames = new String[] {"USERNAME","PASSWORD","CAPTION","STATUS"};
	private String username;
	private String password;
	private String caption;
	private char status;

	public UserAccount() {
	}

	public UserAccount(String username, String password, String caption, char status) {
       this.username = username;
       this.password = password;
       this.caption = caption;
       this.status = status;
    }

	@Id

	@Column(name = "USERNAME", unique = true, nullable = false, length = 30)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", nullable = false, length = 30)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="CAPTION", nullable=false, length=30)
    public String getCaption() {
        return this.caption;
    }

	public void setCaption(String caption) {
        this.caption = caption;
    }

	@Column(name = "STATUS", nullable = false, length = 1)
	public char getStatus() {
		return this.status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	public boolean checkPassword(String passStr) {
		return password.equals(passStr);
	}

}
