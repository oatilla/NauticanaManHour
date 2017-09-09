package com.nauticana.manhour.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "USER_ACCOUNT";
	public static final String[] fieldNames = new String[] { "USERNAME", "PASSWORD", "CAPTION", "STATUS" };
	private String id;
	private String password;
	private String caption;
	private char status;

	private Set<UserAuthorization> userAuthorizations = new HashSet<UserAuthorization>(0);

	public UserAccount() {
	}

	public UserAccount(String username, String password, String caption, char status) {
		this.id = username;
		this.password = password;
		this.caption = caption;
		this.status = status;
	}

	@Id
	@Column(name = "USERNAME", unique = true, nullable = false, length = 30)
	public String getId() {
		return this.id;
	}

	public void setId(String username) {
		this.id = username;
	}

	@Column(name = "PASSWORD", nullable = false, length = 30)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "CAPTION", nullable = false, length = 30)
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userAccount")
	public Set<UserAuthorization> getUserAuthorizations() {
		return this.userAuthorizations;
	}

	public void setUserAuthorizations(Set<UserAuthorization> userAuthorizations) {
		this.userAuthorizations = userAuthorizations;
	}

}
