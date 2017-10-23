package com.nauticana.manhour.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.manhour.model.ProjectTeam;
import com.nauticana.manhour.model.ProjectTeamPerson;
import com.nauticana.manhour.model.ProjectTeamPersonId;
import com.nauticana.manhour.model.Worker;
import com.nauticana.nams.abstrct.AbstractController;
import com.nauticana.nams.utils.Utils;

@Controller
@RequestMapping("/" + ProjectTeamPerson.rootMapping)
public class ProjectTeamPersonController extends AbstractController<ProjectTeamPerson, ProjectTeamPersonId>{

	public static final String[] lookuplists = null;
	public static final String[] detailTables = new String[] {Worker.tableName};
	public static final String[][] detailFields = new String[][] {Worker.fieldNames};
	public static final String listView   = ProjectTeamPerson.rootMapping + "List";
	public static final String editView   = ProjectTeamPerson.rootMapping + "Edit";
	public static final String showView   = ProjectTeamPerson.rootMapping + "Show";
	public static final String selectView = ProjectTeamPerson.rootMapping + "Select";

	private static String[] domainNames = null;
	private static String[] domainlists = null;
	
	@Override
	protected String rootMapping() {return ProjectTeamPerson.rootMapping;}

	@Override
	protected String tableName() {return ProjectTeamPerson.tableName;}

	@Override
	protected String listView() {return listView;}

	@Override
	protected String editView() {return editView;}

	@Override
	protected String showView() {return showView;}

	@Override
	protected String selectView() {return selectView;}

	@Override
	protected String prevPage(String id) {
		if (Utils.emptyStr(id)) return rootMapping()+"/list";
		String[] s = id.split(",");
		return ProjectTeam.rootMapping+"/show?id="+s[0]+","+s[1];
	}

	@Override
	protected String[] detailTables() {return detailTables;}

	@Override
	protected String[][] detailFields() {return detailFields;}
	
	@Override
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(ProjectTeamPerson.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

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
