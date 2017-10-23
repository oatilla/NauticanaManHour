package com.nauticana.nams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.nams.abstrct.AbstractController;
import com.nauticana.nams.model.DomainLookup;
import com.nauticana.nams.model.DomainLookupId;
import com.nauticana.nams.model.DomainName;
import com.nauticana.nams.utils.Utils;

@Controller
@RequestMapping("/" + DomainLookup.rootMapping)
public class DomainLookupController extends AbstractController<DomainLookup, DomainLookupId>{

	public static final String[] lookuplists = null;
	public static final String[] detailTables = null;
	public static final String[][] detailFields = null;
	public static final String listView   = DomainLookup.rootMapping + "List";
	public static final String editView   = DomainLookup.rootMapping + "Edit";
	public static final String showView   = DomainLookup.rootMapping + "Show";
	public static final String selectView = DomainLookup.rootMapping + "Select";
	
	private static String[] domainNames = null;
	private static String[] domainlists = null;

	@Override
	protected String rootMapping() {return DomainLookup.rootMapping;}

	@Override
	protected String tableName() {return DomainLookup.tableName;}

	@Override
	protected String listView() {return listView;}

	@Override
	protected String editView() {return editView;}

	@Override
	protected String showView() {return showView;}

	@Override
	protected String selectView() {return selectView;}

	@Override
	protected String prevPage(String id) {if (Utils.emptyStr(id)) return rootMapping()+"/list"; return DomainName.rootMapping+"/show?id="+id.split(",")[0];}

	@Override
	protected String[] detailTables() {return detailTables;}

	@Override
	protected String[][] detailFields() {return detailFields;}
	
	@Override
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(DomainLookup.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

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
