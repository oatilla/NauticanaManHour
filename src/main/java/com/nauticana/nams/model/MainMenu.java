package com.nauticana.nams.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "MAIN_MENU")
public class MainMenu implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public static final String tableName = "MAIN_MENU";
	public static final String[] fieldNames = new String[] { "MENU", "CAPTION", "DISPLAY_ORDER" };
	public static final String rootMapping = "mainMenu";

	private String menu;
	private String caption;
	private String icon;
	private short  displayOrder;

	private Set<ScreenPage> screenPages = new HashSet<ScreenPage>(0);

	public MainMenu() {
	}

	public MainMenu(String menu, String caption, String icon, short displayOrder) {
		this.menu = menu;
		this.caption = caption;
		this.icon = icon;
		this.displayOrder = displayOrder;
	}

	public MainMenu(String menu, String caption, String icon, short displayOrder, Set<ScreenPage> screenPages) {
		this.menu = menu;
		this.caption = caption;
		this.icon = icon;
		this.displayOrder = displayOrder;
		this.screenPages = screenPages;
	}

	@Id
	@Column(name = "MENU", unique = true, nullable = false, length = 30)
	public String getId() {
		return this.menu;
	}

	public void setId(String menu) {
		this.menu = menu;
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
	@OrderBy
	public short getDisplayOrder() {
		return this.displayOrder;
	}

	public void setDisplayOrder(short displayOrder) {
		this.displayOrder = displayOrder;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
	public Set<ScreenPage> getScreenPages() {
		return this.screenPages;
	}

	public void setScreenPages(Set<ScreenPage> screenPages) {
		this.screenPages = screenPages;
	}

}
