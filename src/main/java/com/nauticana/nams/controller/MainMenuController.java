package com.nauticana.nams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.nams.abstrct.AbstractController;
import com.nauticana.nams.model.MainMenu;
import com.nauticana.nams.model.ScreenPage;

@Controller
@RequestMapping("/" + MainMenu.rootMapping)
public class MainMenuController extends AbstractController<MainMenu, String> {

	public static final String[] lookuplists = null;
	public static final String[] detailTables = new String[] {ScreenPage.tableName};
	public static final String[][] detailFields = new String[][] {ScreenPage.fieldNames};
	public static final String listView   = MainMenu.rootMapping + "List";
	public static final String editView   = MainMenu.rootMapping + "Edit";
	public static final String showView   = MainMenu.rootMapping + "Show";
	public static final String selectView = MainMenu.rootMapping + "Select";

	private static String[] domainNames = null;
	private static String[] domainlists = null;
	
	@Override
	protected String rootMapping() {return MainMenu.rootMapping;}

	@Override
	protected String tableName() {return MainMenu.tableName;}

	@Override
	protected String listView() {return listView;}

	@Override
	protected String editView() {return editView;}

	@Override
	protected String showView() {return showView;}

	@Override
	protected String selectView() {return selectView;}

	@Override
	protected String prevPage(String id) {return rootMapping()+"/list";}

	@Override
	protected String[] detailTables() {return detailTables;}

	@Override
	protected String[][] detailFields() {return detailFields;}
	
	@Override
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(MainMenu.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

	@Override
	protected String[] domainlists() {return domainlists;}

	@Override
	protected String[] lookuplists() {return lookuplists;}

	@Override
	protected String[][] lookupService(int i) {
		return null;
	}

	@Override
	protected String[] actions() {return null;}

	@Override
	protected String[][] detailActions() {return null;}
}
