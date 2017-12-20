package com.nauticana.manhour.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
import com.nauticana.manhour.model.ProjectWbsManhour;
import com.nauticana.manhour.model.ReportProjectWbsStatus;
import com.nauticana.manhour.service.ExternalOrganizationService;
import com.nauticana.manhour.service.ManhourJdbcService;
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
	
//	@Autowired
//	private ProjectWbsManhourService pwms;
//	
//	@Autowired
//	private ProjectWbsQuantityService pwqs;

	@Autowired
	private ExternalOrganizationService organizationService;

	@Autowired
	private ManhourJdbcService manhourJdbcService;
	
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
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

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
		try {
			modelService.save(project);
		} catch(Exception e) {
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
		manhourJdbcService.addProjectApproval(project.getId(), username, Labels.APPROVE_FINAL);
		return new ModelAndView("redirect:/project/show?id="+id);
	}

	@RequestMapping(value = "/approveWbs", method = RequestMethod.GET)
	public ModelAndView approveWbs(HttpServletRequest request) throws IOException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

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
		try {
			modelService.save(project);
		} catch(Exception e) {
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
		manhourJdbcService.addProjectApproval(project.getId(), username, Labels.APPROVE_WBS);
		return new ModelAndView("redirect:/project/show?id="+id);
	}

	@RequestMapping(value = "/withdrawApprove", method = RequestMethod.GET)
	public ModelAndView withdrawApprove(HttpServletRequest request) throws IOException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		
		String id = request.getParameter("id");
		if (Utils.emptyStr(id))
			return errorPage(language, Labels.ERR_PARAMETER_MISSING, Labels.PROJECT + " : id");
		
		if (!namsJdbcService.authorityChk(username, Labels.PROJECT, Labels.APPROVE_WITHDRAW, id))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.PROJECT + "-" + Labels.APPROVE_WITHDRAW + " ON " + id);
		
		Project project = modelService.findById(modelService.StrToId(id));
		project.setStatus(Labels.INITIAL);
		try {
			modelService.save(project);
		} catch(Exception e) {
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
		manhourJdbcService.addProjectApproval(project.getId(), username, Labels.INITIAL);
		return new ModelAndView("redirect:/project/show?id="+id);
	}

	@RequestMapping(value = "/approveProgress", method = RequestMethod.GET)
	public ModelAndView approveProgressGet(HttpServletRequest request) throws IOException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		
		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		String operation = request.getParameter("operation");
		System.out.println("Operation : " + operation);
		String nextpage = request.getParameter(Labels.NEXTPAGE);
		System.out.println("Nextpage : " + nextpage);

		List<String> ids = namsJdbcService.authorizedIds(username, Labels.PROJECT, Labels.APPROVE_PROGRESS);
		List<Project> projects = modelService.findByIds(ids);
		System.out.println("projects : " + projects.size() + " projects");
		if (projects.size() == 0)
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.PROJECT + "-" + Labels.APPROVE_PROGRESS);
		int projectId,year,month;
		String strProjectId = request.getParameter("projectId");
		String strYear  = request.getParameter("year");
		String strMonth = request.getParameter("month");
		System.out.println("Get approveProgress projectId " + strProjectId + " year " + strYear + " month " + strMonth);
		Calendar c = Calendar.getInstance();
		try {projectId = Integer.parseInt(strProjectId);} catch (Exception e) {projectId = -1;}//projects.get(0).getId();}
		try {year = Integer.parseInt(strYear);} catch (Exception e) {year = c.get(Calendar.YEAR);}
		try {month = Integer.parseInt(strMonth);} catch (Exception e) {month = c.get(Calendar.MONTH)+1;}
	    c.set(year, month-1, 1, 0, 0, 0);
	    Date begda = c.getTime();
	    c.set(year, month-1, c.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
		Date endda = c.getTime();
		
		Date minInitialDate = manhourJdbcService.getMinInitialDate(projectId, begda, endda);
		Date minApprovedDate = manhourJdbcService.getMinApprovedDate(projectId, begda, endda);

		System.out.println(" Begda " + begda.toString() + " endda " + endda.toString());

		List<ReportProjectWbsStatus> records = manhourJdbcService.getReportProjectWbsStatusAll(projectId, begda, endda, language.code);
		ModelAndView model = new ModelAndView("projectProgressApprove");
		if (minInitialDate != null)
			model.addObject("minInitialDate", minInitialDate);
		if (minApprovedDate != null)
			model.addObject("minApprovedDate", minApprovedDate);
		model.addObject("projects", projects);
		model.addObject("projectId", projectId);
		model.addObject("records", records);
		model.addObject("year", year);
		model.addObject("month", month);
		model.addObject("MONTHS_LIST", getDomainValues("MONTHS", language));
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(ProjectWbsManhour.tableName));
		model.addObject(Labels.SELECT, language.getIconText(Labels.SELECT));
		model.addObject(Labels.OK, language.getIconText(Labels.OK));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.PROJECT, language.getText(Labels.PROJECT));
		model.addObject(Labels.REALISED, language.getIconText(Labels.REALISED));
		model.addObject(Labels.TOTAL, language.getIconText(Labels.TOTAL));
		model.addObject(Labels.TENDER, language.getText(Labels.TENDER));
		model.addObject(Labels.PUP, language.getText(Labels.PUP));
		model.addObject(Labels.PLANNED, language.getText(Labels.PLANNED));
		model.addObject(Labels.APPROVE, language.getIconText(Labels.APPROVE));
		model.addObject(Labels.APPROVE_WITHDRAW, language.getIconText(Labels.APPROVE_WITHDRAW));
		model.addObject(Labels.PERIOD, language.getText(Labels.PERIOD));
		model.addObject(Labels.CODE, language.getText(Labels.CODE));
		model.addObject(Labels.CAPTION, language.getText(Labels.CAPTION));
		model.addObject(Labels.dataTableNames[0], Labels.dataTableSettings[0]);
		for (int i = 0; i < ReportProjectWbsStatus.fieldNames.length; i++) {
			model.addObject(ReportProjectWbsStatus.fieldNames[i], language.getText(ReportProjectWbsStatus.fieldNames[i]));
		}
		return model;
	}

	@RequestMapping(value = "/approveProgress", method = RequestMethod.POST)
	public ModelAndView approveProgressPost(HttpServletRequest request) throws IOException, ParseException {
			
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
			
		// Read POSTED request variables
		int projectId,year,month;
		String strProjectId = request.getParameter("projectId");
		String strYear  = request.getParameter("year");
		String strMonth = request.getParameter("month");
		String withDraw = request.getParameter("withdraw");
		
		System.out.println("Submitted projectId " + strProjectId + " year " + strYear + " month " + strMonth);
		try {projectId  = Integer.parseInt(strProjectId);}  catch (Exception e) {return errorPage(language, Labels.ERR_PARAMETER_MISSING, "projectId");}
		try {year  = Integer.parseInt(strYear);}  catch (Exception e) {return errorPage(language, Labels.ERR_PARAMETER_MISSING, "year");}
		try {month = Integer.parseInt(strMonth);} catch (Exception e) {return errorPage(language, Labels.ERR_PARAMETER_MISSING, "month");}
		Calendar c = Calendar.getInstance();
	    c.set(year, month-1, 1, 0, 0, 0);
	    Date begda = c.getTime();
	    c.set(year, month-1, c.getActualMaximum(Calendar.DAY_OF_MONTH), 0, 0, 0);
		Date endda = c.getTime();

//		Project p = modelService.findById(projectId);
		if ("YES".equals(withDraw)) {
			manhourJdbcService.setProjectWbsQtyStatus(projectId, begda, endda, Labels.INITIAL);
			manhourJdbcService.setProjectWbsMhStatus(projectId, begda, endda, Labels.INITIAL);
//			for (ProjectWbs pw : p.getProjectWbses()) {
//				for (ProjectTeam pt : p.getProjectTeams()) {
//					pwms.withdrawApprove(projectId, pw.getId().getCategoryId(), pt.getId().getTeamId(), begda, endda);
//					pwqs.withdrawApprove(projectId, pw.getId().getCategoryId(), pt.getId().getTeamId(), begda, endda);
//				}
//			}
		} else {
			manhourJdbcService.setProjectWbsQtyStatus(projectId, begda, endda, Labels.APPROVED);
			manhourJdbcService.setProjectWbsMhStatus(projectId, begda, endda, Labels.APPROVED);
//			for (ProjectWbs pw : p.getProjectWbses()) {
//				for (ProjectTeam pt : p.getProjectTeams()) {
//					pwms.approve(projectId, pw.getId().getCategoryId(), pt.getId().getTeamId(), begda, endda);
//					pwqs.approve(projectId, pw.getId().getCategoryId(), pt.getId().getTeamId(), begda, endda);
//				}
//			}
		}
		return new ModelAndView("redirect:/" + rootMapping() + "/approveProgress?projectId="+strProjectId+"&month="+strMonth+"&year="+strYear);
	}
}
