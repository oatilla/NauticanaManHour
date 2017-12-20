package com.nauticana.nams.model;

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
	public static final String[] fieldNames = new String[] { "USERNAME", "PASSWORD", "CAPTION", "STATUS", "EMAIL_ADDRESS" };
	public static final String rootMapping = "userAccount";

	private String id;
	private String password;
	private String caption;
	private char status;
	private String emailAddress;

	private Set<UserAuthorization> userAuthorizations = new HashSet<UserAuthorization>(0);

	public UserAccount() {
	}

	public UserAccount(String username, String password, String caption, String emailAddress, char status) {
		this.id = username;
		this.password = password;
		this.caption = caption;
		this.emailAddress = emailAddress;
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

	@Column(name = "EMAIL_ADDRESS", nullable = false, length = 80)
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
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
