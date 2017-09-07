package com.nauticana.manhour.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PAGE_AUTHORIZATION")
public class PageAuthorization implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "PAGE_AUTHORIZATION";
	public static final String[] fieldNames = new String[] { "AUTHORITY_GROUP", "PAGENAME" };

	private PageAuthorizationId id;
	private AuthorityGroup authorityGroup;
	private ScreenPage screenPage;

	public PageAuthorization() {
	}

	public PageAuthorization(PageAuthorizationId id, AuthorityGroup authorityGroup, ScreenPage screenPage) {
		this.id = id;
		this.authorityGroup = authorityGroup;
		this.screenPage = screenPage;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "authorityGroup", column = @Column(name = "AUTHORITY_GROUP", nullable = false, length = 30)),
			@AttributeOverride(name = "pagename", column = @Column(name = "PAGENAME", nullable = false, length = 30)) })
	public PageAuthorizationId getId() {
		return this.id;
	}

	public void setId(PageAuthorizationId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "AUTHORITY_GROUP", nullable = false, insertable = false, updatable = false)
	public AuthorityGroup getAuthorityGroup() {
		return this.authorityGroup;
	}

	public void setAuthorityGroup(AuthorityGroup authorityGroup) {
		this.authorityGroup = authorityGroup;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PAGENAME", nullable = false, insertable = false, updatable = false)
	public ScreenPage getScreenPage() {
		return this.screenPage;
	}

	public void setScreenPage(ScreenPage screenPage) {
		this.screenPage = screenPage;
	}

}
