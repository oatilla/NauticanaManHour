package com.nauticana.nams.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.nams.abstrct.AbstractController;
import com.nauticana.nams.model.UserAccount;
import com.nauticana.nams.model.UserAuthorization;
import com.nauticana.nams.model.UserAuthorizationId;
import com.nauticana.nams.service.AuthorityGroupService;
import com.nauticana.nams.service.UserAccountService;
import com.nauticana.nams.utils.Utils;

@Controller
@RequestMapping("/" + UserAuthorization.rootMapping)
public class UserAuthorizationController extends AbstractController<UserAuthorization, UserAuthorizationId>{

	@Autowired
	private AuthorityGroupService ags;

	@Autowired
	private UserAccountService uas;

	public static final String[] lookuplists = new String[] {"authorityGroupList","userAccountList"};
	public static final String[] detailTables = null;
	public static final String[][] detailFields = null;
	public static final String listView   = UserAuthorization.rootMapping + "List";
	public static final String editView   = UserAuthorization.rootMapping + "Edit";
	public static final String showView   = UserAuthorization.rootMapping + "Show";
	public static final String selectView = UserAuthorization.rootMapping + "Select";

	private static String[] domainNames = null;
	private static String[] domainlists = null;
	
	@Override
	protected String rootMapping() {return UserAuthorization.rootMapping;}

	@Override
	protected String tableName() {return UserAuthorization.tableName;}

	@Override
	protected String listView() {return listView;}

	@Override
	protected String editView() {return editView;}

	@Override
	protected String showView() {return showView;}

	@Override
	protected String selectView() {return selectView;}

	@Override
	protected String prevPage(String id) {if (Utils.emptyStr(id)) return rootMapping()+"/list"; return UserAccount.rootMapping+"/show?id="+id.split(",")[0];}

	@Override
	protected String[] detailTables() {return detailTables;}

	@Override
	protected String[][] detailFields() {return detailFields;}
	
	@Override
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(UserAuthorization.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

	@Override
	protected String[] domainlists() {return domainlists;}

	@Override
	protected String[] lookuplists() {return lookuplists;}

	@Override
	protected String[][] lookupService(int i) {
		switch (i) {
		case 0: return ags.findAllStr();
		case 1: return uas.findAllStr();
		default: return null;
		}
	}

	@Override
	protected String[] actions() {return null;}

	@Override
	protected String[][] detailActions() {return null;}
}
