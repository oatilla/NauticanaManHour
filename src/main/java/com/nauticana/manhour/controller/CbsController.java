package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.Cbs;
import com.nauticana.nams.abstrct.AbstractController;

@Controller
@RequestMapping("/" + Cbs.rootMapping)
public class CbsController extends AbstractController<Cbs, String> {

	public static final String[] lookuplists = null;
	public static final String[] detailTables = null;
	public static final String[][] detailFields = null;
	public static final String listView   = Cbs.rootMapping + "List";
	public static final String editView   = Cbs.rootMapping + "Edit";
	public static final String showView   = Cbs.rootMapping + "Show";
	public static final String selectView = Cbs.rootMapping + "Select";

	private static String[] domainNames = null;
	private static String[] domainlists = null;
	
	@Override
	protected String rootMapping() {return Cbs.rootMapping;}

	@Override
	protected String tableName() {return Cbs.tableName;}

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
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(Cbs.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

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
