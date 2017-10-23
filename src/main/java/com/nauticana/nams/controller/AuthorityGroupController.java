package com.nauticana.nams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.nams.abstrct.AbstractController;
import com.nauticana.nams.model.AuthorityGroup;
import com.nauticana.nams.model.ObjectAuthorization;
import com.nauticana.nams.model.UserAuthorization;

@Controller
@RequestMapping("/" + AuthorityGroup.rootMapping)
public class AuthorityGroupController extends AbstractController<AuthorityGroup, String> {

	public static final String[] lookuplists = null;
	public static final String[] detailTables = new String[] {ObjectAuthorization.tableName, UserAuthorization.tableName};
	public static final String[][] detailFields = new String[][] {ObjectAuthorization.fieldNames, UserAuthorization.fieldNames};
	public static final String listView = AuthorityGroup.rootMapping + "List";
	public static final String editView = AuthorityGroup.rootMapping + "Edit";
	public static final String showView = AuthorityGroup.rootMapping + "Show";
	public static final String selectView = AuthorityGroup.rootMapping + "Select";
	
	private static String[] domainNames = null;
	private static String[] domainlists = null;
	
	@Override
	protected String rootMapping() {return AuthorityGroup.rootMapping;}

	@Override
	protected String tableName() {return AuthorityGroup.tableName;}

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
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(AuthorityGroup.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

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
