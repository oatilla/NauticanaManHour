package com.nauticana.manhour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.Subcontractor;
import com.nauticana.manhour.model.Worker;
import com.nauticana.manhour.service.ExternalSubcontractorService;
import com.nauticana.nams.abstrct.AbstractController;


@Controller
@RequestMapping("/" + Subcontractor.rootMapping)
public class SubcontractorController extends AbstractController<Subcontractor, Integer> {

	public static final String[] lookuplists = new String[] {"extSubcontractorList"};
	public static final String[] detailTables = new String[] {Worker.tableName};
	public static final String[][] detailFields = new String[][] {Worker.fieldNames};
	public static final String listView   = Subcontractor.rootMapping + "List";
	public static final String editView   = Subcontractor.rootMapping + "Edit";
	public static final String showView   = Subcontractor.rootMapping + "Show";
	public static final String selectView = Subcontractor.rootMapping + "Select";

	private static String[] domainNames = null;
	private static String[] domainlists = null;
	@Autowired
	private ExternalSubcontractorService extSubcontractorService;
	
	
	@Override
	protected String rootMapping() {return Subcontractor.rootMapping;}

	@Override
	protected String tableName() {return Subcontractor.tableName;}

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
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(Subcontractor.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

	@Override
	protected String[] domainlists() {return domainlists;}

	@Override
	protected String[] lookuplists() {return lookuplists;}

	@Override
	protected String[][] lookupService(int i) {
		switch (i) {
			case 0: return extSubcontractorService.findAllStr();
		}
		return null;
	}

	@Override
	protected String[] actions() {return null;}

	@Override
	protected String[][] detailActions() {return null;}
}
