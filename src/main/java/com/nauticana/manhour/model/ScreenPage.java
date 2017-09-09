package com.nauticana.manhour.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SCREEN_PAGE")
public class ScreenPage implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "SCREEN_PAGE";
	public static final String[] fieldNames = new String[] { "PAGENAME", "CAPTION", "ICON", "URL", "MENU", "DISPLAY_ORDER" };
	private String pagename;
	private String caption;
	private String icon;
	private String url;
	private MainMenu menu;
	private short displayOrder;

	private Set<PageAuthorization> pageAuthorizations = new HashSet<PageAuthorization>(0);

	public ScreenPage() {
	}

	public ScreenPage(String pagename, String caption, String icon, String url, MainMenu menu, short displayOrder) {
		this.pagename = pagename;
		this.caption = caption;
		this.icon = icon;
		this.url = url;
		this.menu = menu;
		this.displayOrder = displayOrder;
	}

	@Id
	@Column(name = "PAGENAME", unique = true, nullable = false, length = 30)
	public String getId() {
		return this.pagename;
	}

	public void setId(String pagename) {
		this.pagename = pagename;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MENU", nullable = false)
	public MainMenu getmenu() {
		return this.menu;
	}

	public void setMenu(MainMenu menu) {
		this.menu = menu;
	}

	@Column(name = "URL", nullable = false, length = 100)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "CAPTION", nullable = false, length = 30)
	public String getCaption() {
		return this.caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	@Column(name = "ICON", nullable = false, length = 100)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Column(name = "DISPLAY_ORDER")
	public short getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(short displayOrder) {
		this.displayOrder = displayOrder;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "screenPage")
	public Set<PageAuthorization> getPageAuthorizations() {
		return this.pageAuthorizations;
	}

	public void setPageAuthorizations(Set<PageAuthorization> pageAuthorizations) {
		this.pageAuthorizations = pageAuthorizations;
	}

}
