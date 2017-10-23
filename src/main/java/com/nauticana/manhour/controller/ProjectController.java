package com.nauticana.manhour.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.manhour.model.Project;
import com.nauticana.manhour.model.ProjectTeam;
import com.nauticana.manhour.model.ProjectWbs;
import com.nauticana.manhour.service.ExternalOrganizationService;
import com.nauticana.nams.abstrct.AbstractController;
import com.nauticana.nams.utils.Labels;
import com.nauticana.nams.utils.PortalLanguage;
import com.nauticana.nams.utils.Utils;

@Controller
@RequestMapping("/" + Project.rootMapping)
public class ProjectController extends AbstractController<Project, Integer> {

	public static final String[] lookuplists = new String[] {"organizationList"};
	public static final String[] detailTables = new String[] {ProjectWbs.tableName, ProjectTeam.tableName};
	public static final String[][] detailFields = new String[][] {ProjectWbs.fieldNames, ProjectTeam.fieldNames};
	public static final String[][] detailActions = new String[][] {ProjectWbs.actions, ProjectTeam.actions};
	
	public static final String listView   = Project.rootMapping + "List";
	public static final String editView   = Project.rootMapping + "Edit";
	public static final String showView   = Project.rootMapping + "Show";
	public static final String selectView = Project.rootMapping + "Select";

	private static String[] domainNames = null;
	private static String[] domainlists = null;
	
	@Autowired
	private ExternalOrganizationService organizationService;

	
	@Override
	protected String rootMapping() {return Project.rootMapping;}

	@Override
	protected String tableName() {return Project.tableName;}

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
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(Project.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

	@Override
	protected String[] domainlists() {return domainlists;}

	@Override
	protected String[] lookuplists() {return lookuplists;}

	
	
	@Override
	protected String[][] lookupService(int i) {
		return organizationService.findAllStr();
	}

	@Override
	protected String[] actions() {return Project.actions;}

	@Override
	protected String[][] detailActions() {return detailActions;}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView showGet(HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession(true);
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		ModelAndView model = super.showGet(request);
		model.addObject(Labels.TENDER, language.getText(Labels.TENDER));
		model.addObject(Labels.PUP, language.getText(Labels.PUP));
		model.addObject(Labels.PLANNED, language.getText(Labels.PLANNED));
		return model;
	}
	
	
	@RequestMapping(value = "/selectOrganization", method = RequestMethod.GET)
	public ModelAndView selectOrganization(HttpServletRequest request) throws IOException {
		ModelAndView model=new ModelAndView("organizationSelect"); 
		return model;
	}
	
	
	@RequestMapping(value = "/approveFinal", method = RequestMethod.GET)
	public ModelAndView approveFinal(HttpServletRequest request) throws IOException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		
		String id = request.getParameter("id");
		if (Utils.emptyStr(id))
			return errorPage(language, Labels.ERR_PARAMETER_MISSING, Labels.PROJECT + " : id");
		
		if (!namsJdbcService.authorityChk(username, Labels.PROJECT, Labels.APPROVE_FINAL, id))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.PROJECT + "-" + Labels.APPROVE_FINAL + " ON " + id);
		
		Project project = modelService.findById(modelService.StrToId(id));
		if (project.getStatus().equals(Labels.APPROVE_FINAL))
			return errorPage(language, Labels.ERR_ALREADY_APPROVED, Labels.PROJECT + " : " + id);
		project.setStatus(Labels.APPROVE_FINAL);
		modelService.save(project);
		namsJdbcService.addProjectApproval(project.getId(), username, Labels.APPROVE_FINAL);
		return new ModelAndView("redirect:/project/show?id="+id);
	}

	@RequestMapping(value = "/approveWbs", method = RequestMethod.GET)
	public ModelAndView approveWbs(HttpServletRequest request) throws IOException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		
		String id = request.getParameter("id");
		if (Utils.emptyStr(id))
			return errorPage(language, Labels.ERR_PARAMETER_MISSING, Labels.PROJECT + " : id");

		if (!namsJdbcService.authorityChk(username, Labels.PROJECT, Labels.APPROVE_WBS, id))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.PROJECT + "-" + Labels.APPROVE_WBS + " ON " + id);
		
		Project project = modelService.findById(modelService.StrToId(id));
		if (project.getStatus().equals(Labels.APPROVE_WBS) || project.getStatus().equals(Labels.APPROVE_FINAL))
			return errorPage(language, Labels.ERR_ALREADY_APPROVED, Labels.PROJECT + " : " + id);
		project.setStatus(Labels.APPROVE_WBS);
		modelService.save(project);
		namsJdbcService.addProjectApproval(project.getId(), username, Labels.APPROVE_WBS);
		return new ModelAndView("redirect:/project/show?id="+id);
	}

	@RequestMapping(value = "/withdrawApprove", method = RequestMethod.GET)
	public ModelAndView withdrawApprove(HttpServletRequest request) throws IOException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		
		String id = request.getParameter("id");
		if (Utils.emptyStr(id))
			return errorPage(language, Labels.ERR_PARAMETER_MISSING, Labels.PROJECT + " : id");
		
		if (!namsJdbcService.authorityChk(username, Labels.PROJECT, Labels.APPROVE_WITHDRAW, id))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.PROJECT + "-" + Labels.APPROVE_WITHDRAW + " ON " + id);
		
		Project project = modelService.findById(modelService.StrToId(id));
		project.setStatus(Labels.INITIAL);
		modelService.save(project);
		namsJdbcService.addProjectApproval(project.getId(), username, Labels.INITIAL);
		return new ModelAndView("redirect:/project/show?id="+id);
	}

}
