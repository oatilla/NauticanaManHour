package com.nauticana.nams.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.nams.abstrct.AbstractController;
import com.nauticana.nams.model.DomainLookup;
import com.nauticana.nams.model.DomainName;
import com.nauticana.nams.model.DomainValue;

@Controller
@RequestMapping("/" + DomainName.rootMapping)
public class DomainNameController extends AbstractController<DomainName, String> {

	public static final String[] lookuplists = null;
	public static final String[] actionlists = new String[] {"RELOAD_VALUES"};
	public static final String[] detailTables = new String[] {DomainValue.tableName, DomainLookup.tableName};
	public static final String[][] detailFields = new String[][] {DomainValue.fieldNames, DomainLookup.fieldNames};
	public static final String listView   = DomainName.rootMapping + "List";
	public static final String editView   = DomainName.rootMapping + "Edit";
	public static final String showView   = DomainName.rootMapping + "Show";
	public static final String selectView = DomainName.rootMapping + "Select";
	
	private static String[] domainNames = null;
	private static String[] domainlists = null;

	@Override
	protected String rootMapping() {return DomainName.rootMapping;}

	@Override
	protected String tableName() {return DomainName.tableName;}

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
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(DomainName.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

	@Override
	protected String[] domainlists() {return domainlists;}

	@Override
	protected String[] lookuplists() {return lookuplists;}

	@Override
	protected String[][] lookupService(int i) {
		return null;
	}

	@Override
	protected String[] actions() {return actionlists;}

	@Override
	protected String[][] detailActions() {return null;}

	@RequestMapping(value="/reloadValues",method=RequestMethod.GET)
	public ModelAndView reloadValues(HttpServletRequest request) {
		String id = request.getParameter("id");
		DomainName domainName = modelService.findById(id);
		dataCache.getDomain(id).clear();
		dataCache.loadDomainValues(domainName);
		return new ModelAndView("redirect:show?id="+id);
	}
}
