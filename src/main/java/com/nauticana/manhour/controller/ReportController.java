package com.nauticana.manhour.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.manhour.model.Project;
import com.nauticana.manhour.model.ProjectTeam;
import com.nauticana.manhour.model.ProjectTeamId;
import com.nauticana.manhour.model.ReportProjectWbsStatus;
import com.nauticana.manhour.model.Subcontractor;
import com.nauticana.manhour.model.ViewWbsQuantity;
import com.nauticana.manhour.service.ProjectService;
import com.nauticana.manhour.service.ProjectTeamService;
import com.nauticana.manhour.service.SubcontractorService;
import com.nauticana.nams.service.NamsJdbcService;
import com.nauticana.nams.utils.DataCache;
import com.nauticana.nams.utils.Labels;
import com.nauticana.nams.utils.PortalLanguage;
import com.nauticana.nams.utils.Utils;

@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	protected DataCache dataCache;

	@Autowired
	protected NamsJdbcService namsJdbcService;

	@Autowired
	protected ProjectService projectService;

	@Autowired
	protected ProjectTeamService teamService;

	@Autowired
	protected SubcontractorService subcontractorService;

	public static final String listView = "reportList";
	
	public static final String[] reportNames = new String[] {
			"REPORT_CUMULATIVE_DIRECT_MANHOUR",
			"REPORT_TEAM_MANHOUR",
			"REPORT_SUBCONTRACTOR_MANHOUR",
			"REPORT_BRIEF_MANHOUR"};
//			"REPORT_CITIZEN_TYPE"};
	
	public static final String[] reportMaps = new String[] {
			"/report/cumulativeMh",
			"/report/subcontractorMh",
			"/report/teamMh",
			"/report/briefMh"};
//			"/report/citizenType"};

	public ModelAndView errorPage(PortalLanguage language, String ERRCODE, String msgText) {
		ModelAndView model = new ModelAndView("errorPage");
		model.addObject("ERRCODE", language.getText(ERRCODE));
		model.addObject("MSGTEXT", msgText);
		return model;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listGet(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		List<String> ids = namsJdbcService.authorizedIds(username, Labels.PROJECT_TEAM, Labels.APPROVE_MANHOUR);
		List<Project> projects = projectService.findByIds(ids);
		if (projects.isEmpty())
			return errorPage(language, Labels.ERR_UNAUTHORIZED, "No projects authorized for reporting");
		
		String strProjectId = request.getParameter("projectId");
		String strTeamId = request.getParameter("teamId");
		String strSubcontractorId = request.getParameter("subcontractorId");
		String strBegda = request.getParameter("begda");
		String strEndda = request.getParameter("endda");

		int projectId = projects.get(0).getId();
		int teamId = -1;
		int subcontractorId = -1;
		Date begda, endda;
		Calendar c = Calendar.getInstance();
		try { begda = Labels.ymdDF.parse(strBegda);} catch (Exception e) {
		    c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 1);
		    begda = c.getTime();
		}
		try { endda = Labels.ymdDF.parse(strEndda);} catch (Exception e) {
		    c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.getActualMaximum(Calendar.DAY_OF_MONTH));
		    endda = c.getTime();
		}

		if (!Utils.emptyStr(strProjectId))
			projectId = Integer.parseInt(strProjectId);
		if (!Utils.emptyStr(strTeamId))
			teamId = Integer.parseInt(strTeamId);
		if (!Utils.emptyStr(strSubcontractorId))
			subcontractorId = Integer.parseInt(strSubcontractorId);
	    
		ModelAndView model = new ModelAndView(listView);
		model.addObject("projects", projects);
		model.addObject("projectId", projectId);
		model.addObject("teamId", teamId);
		model.addObject("subcontractorId", subcontractorId);
		model.addObject(Labels.PROJECT, language.getText(Labels.PROJECT));
		model.addObject(Labels.PAGETITLE, language.getText(Labels.REPORTS));

		Map<String, String> reports = new LinkedHashMap<String, String>();
		
		for (int i = 0; i < reportMaps.length; i++) {
			reports.put(language.getText(reportNames[i]), reportMaps[i]);
		}
		model.addObject("reports", reports);
		model.addObject(Labels.BEGDA, language.getText(Labels.BEGDA));
	    model.addObject("begda", Labels.ymdDF.format(begda));
		model.addObject(Labels.ENDDA, language.getText(Labels.ENDDA));
	    model.addObject("endda", Labels.ymdDF.format(endda));
	    model.addObject(Labels.PROJECT_TEAM, language.getText(Labels.PROJECT_TEAM));
	    model.addObject(Labels.SUBCONTRACTOR, language.getText(Labels.SUBCONTRACTOR));
	    
	    return model;
	}
	
	@RequestMapping(value = "/cumulativeMh", method = RequestMethod.POST)
	public ModelAndView report1(HttpServletRequest request) throws IOException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");
		
		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		String nextpage = request.getParameter(Labels.NEXTPAGE);
		System.out.println("Nextpage : " + nextpage);

		int projectId;
		String strProjectId = request.getParameter("projectId");
		if (!Utils.emptyStr(strProjectId))
			projectId = Integer.parseInt(strProjectId);
		else
			return errorPage(language, Labels.ERR_WRONGDATA, "No project selected");
		Project project = projectService.findById(projectId);

		String strBegda = request.getParameter("begda");
		Date begda;
		try {
			begda = Labels.ymdDF.parse(strBegda);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "BEGDA not selected");
		}

		String strEndda = request.getParameter("endda");
		Date endda;
		try {
			endda = Labels.ymdDF.parse(strEndda);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "ENDDA not selected");
		}

		String prevPage= 	"report/list?projectId=" + strProjectId + "&begda=" + strBegda + "&endda=" + strEndda;

		List<ReportProjectWbsStatus> records = namsJdbcService.getReportProjectWbsStatus(projectId, begda, endda);
		ModelAndView model = new ModelAndView("reportCumulativeMh");

		model.addObject("project", project);
		model.addObject("team", project);
		model.addObject("projectId", projectId);
		model.addObject("records", records);
		model.addObject("begda", Labels.dmyDF.format(begda));
		model.addObject("endda", Labels.dmyDF.format(endda));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.PROJECT, language.getText(Labels.PROJECT));
		model.addObject(Labels.REALISED, language.getIconText(Labels.REALISED));
		model.addObject(Labels.TOTAL, language.getIconText(Labels.TOTAL));
		model.addObject(Labels.PREVPAGE, prevPage);

		model.addObject(Labels.PAGETITLE, language.getText(reportNames[0]));
		for (int i = 0; i < ReportProjectWbsStatus.fieldNames.length; i++) {
			model.addObject(ReportProjectWbsStatus.fieldNames[i], language.getText(ReportProjectWbsStatus.fieldNames[i]));
		}
		return model;
	}
	
	@RequestMapping(value = "/teamMh", method = RequestMethod.POST)
	public ModelAndView report2(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");
		
		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		String nextpage = request.getParameter(Labels.NEXTPAGE);
		System.out.println("Nextpage : " + nextpage);

		int projectId;
		String strProjectId = request.getParameter("projectId");
		if (!Utils.emptyStr(strProjectId))
			projectId = Integer.parseInt(strProjectId);
		else
			return errorPage(language, Labels.ERR_WRONGDATA, "No project selected");
		Project project = projectService.findById(projectId);

		String strBegda = request.getParameter("begda");
		Date begda;
		try {
			begda = Labels.ymdDF.parse(strBegda);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "BEGDA not selected");
		}

		String strEndda = request.getParameter("endda");
		Date endda;
		try {
			endda = Labels.ymdDF.parse(strEndda);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "ENDDA not selected");
		}
		
		int teamId;
		String strTeamId = request.getParameter("teamId");
		if (!Utils.emptyStr(strTeamId))
			teamId = Integer.parseInt(strTeamId);
		else
			return errorPage(language, Labels.ERR_WRONGDATA, "No project selected");
		
		String prevPage= 	"report/list?projectId=" + strProjectId + "&teamId=" + strTeamId + "&begda=" + strBegda + "&endda=" + strEndda;
		
		ProjectTeam team = teamService.findById(new ProjectTeamId(projectId,teamId));

		List<ReportProjectWbsStatus>records = namsJdbcService.getReportProjectWbsStatusForTeam(projectId,team.getId().getTeamId(), begda, endda);
		
		ModelAndView model = new ModelAndView("reportCumulativeTeamMh");

		model.addObject("project", project);
		model.addObject("teamCaption", team.getCaption());
		model.addObject("projectId", projectId);
		model.addObject("records", records);
		model.addObject("begda", Labels.dmyDF.format(begda));
		model.addObject("endda", Labels.dmyDF.format(endda));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.PROJECT, language.getText(Labels.PROJECT));
		model.addObject(Labels.PROJECT_TEAM, language.getText(Labels.PROJECT_TEAM));
		model.addObject(Labels.REALISED, language.getIconText(Labels.REALISED));
		model.addObject(Labels.TOTAL, language.getIconText(Labels.TOTAL));
		model.addObject(Labels.PREVPAGE, prevPage);

		model.addObject(Labels.PAGETITLE, language.getText(reportNames[1]));
		for (int i = 0; i < ReportProjectWbsStatus.fieldNames.length; i++) {
			model.addObject(ReportProjectWbsStatus.fieldNames[i], language.getText(ReportProjectWbsStatus.fieldNames[i]));
		}
		return model;

	}
	
	@RequestMapping(value = "/subcontractorMh", method = RequestMethod.POST)
	public ModelAndView report6(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");
		
		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		String nextpage = request.getParameter(Labels.NEXTPAGE);
		System.out.println("Nextpage : " + nextpage);

		int projectId;
		String strProjectId = request.getParameter("projectId");
		if (!Utils.emptyStr(strProjectId))
			projectId = Integer.parseInt(strProjectId);
		else
			return errorPage(language, Labels.ERR_WRONGDATA, "No project selected");
		Project project = projectService.findById(projectId);

		String strBegda = request.getParameter("begda");
		Date begda;
		try {
			begda = Labels.ymdDF.parse(strBegda);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "BEGDA not selected");
		}

		String strEndda = request.getParameter("endda");
		Date endda;
		try {
			endda = Labels.ymdDF.parse(strEndda);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_WRONGDATA, "ENDDA not selected");
		}
		
		int subcontractorId;
		String strSubcontractorId = request.getParameter("subcontractorId");
		if (!Utils.emptyStr(strSubcontractorId))
			subcontractorId = Integer.parseInt(strSubcontractorId);
		else
			return errorPage(language, Labels.ERR_WRONGDATA, "No project selected");
		
		String prevPage= 	"report/list?projectId=" + strProjectId + "&subcontractorId=" + strSubcontractorId + "&begda=" + strBegda + "&endda=" + strEndda;

		Subcontractor subcontractor = subcontractorService.findById(subcontractorId);

		List<ReportProjectWbsStatus>records = namsJdbcService.getReportProjectWbsStatusForSubcontractor(projectId,subcontractorId, begda, endda);

		ModelAndView model = new ModelAndView("reportCumulativeTeamMh");

		model.addObject("project", project);
		if (subcontractor !=null)
			model.addObject("teamCaption", subcontractor.getCaption());
		model.addObject("projectId", projectId);
		model.addObject("records", records);
		model.addObject("begda", Labels.dmyDF.format(begda));
		model.addObject("endda", Labels.dmyDF.format(endda));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.PROJECT, language.getText(Labels.PROJECT));
		model.addObject(Labels.PROJECT_TEAM, language.getText(Labels.PROJECT_TEAM));
		model.addObject(Labels.REALISED, language.getIconText(Labels.REALISED));
		model.addObject(Labels.TOTAL, language.getIconText(Labels.TOTAL));
		model.addObject(Labels.PREVPAGE, prevPage);

		
		model.addObject(Labels.PAGETITLE, language.getText(reportNames[2]));
		for (int i = 0; i < ReportProjectWbsStatus.fieldNames.length; i++) {
			model.addObject(ReportProjectWbsStatus.fieldNames[i], language.getText(ReportProjectWbsStatus.fieldNames[i]));
		}
		return model;

	}
	
	@RequestMapping(value = "/briefMh", method = RequestMethod.POST)
	public ModelAndView report3(HttpServletRequest request) throws IOException {
	// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");
		
		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		String nextpage = request.getParameter(Labels.NEXTPAGE);
		System.out.println("Nextpage : " + nextpage);

		int projectId;
		String strProjectId = request.getParameter("projectId");
		if (!Utils.emptyStr(strProjectId))
			projectId = Integer.parseInt(strProjectId);
		else
			return errorPage(language, Labels.ERR_WRONGDATA, "No project selected");
		Project project = projectService.findById(projectId);

		String prevPage= 	"report/list?projectId=" + strProjectId;

		List<ViewWbsQuantity> records = namsJdbcService.aggrProjectWbsQuantity(projectId);
		ModelAndView model = new ModelAndView("reportBriefMh");
		
		
		model.addObject("project", project);
		model.addObject("projectId", projectId);
		model.addObject("records", records);
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.PROJECT, language.getText(Labels.PROJECT));
		model.addObject(Labels.PREVPAGE, prevPage);
		model.addObject(Labels.MANHOUR, language.getText(Labels.MANHOUR));

		
		model.addObject(Labels.PAGETITLE, language.getText(reportNames[3]));
		for (int i = 0; i < ReportProjectWbsStatus.fieldNames.length; i++) {
			model.addObject(ReportProjectWbsStatus.fieldNames[i], language.getText(ReportProjectWbsStatus.fieldNames[i]));
		}

	return model;
	}

	@RequestMapping(value = "/citizenType", method = RequestMethod.GET)
	public ModelAndView report5(HttpServletRequest request) throws IOException {
		ModelAndView model = new ModelAndView("citizenType");
		model.addObject(Labels.PAGETITLE, reportNames[4]);

		
		return model;
	}

}
