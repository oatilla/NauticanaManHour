package com.nauticana.manhour.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.manhour.model.Category;
import com.nauticana.manhour.model.ProjectTeam;
import com.nauticana.manhour.model.ProjectTeamId;
import com.nauticana.manhour.model.ProjectTeamTemplate;
import com.nauticana.manhour.model.ProjectTeamTemplateId;
import com.nauticana.manhour.model.ProjectWbs;
import com.nauticana.manhour.model.ProjectWbsId;
import com.nauticana.manhour.model.Subcontractor;
import com.nauticana.manhour.service.ManhourJdbcService;
import com.nauticana.manhour.service.ProjectTeamService;
import com.nauticana.manhour.service.ProjectWbsService;
import com.nauticana.nams.abstrct.AbstractController;
import com.nauticana.nams.utils.Labels;
import com.nauticana.nams.utils.PortalLanguage;
import com.nauticana.nams.utils.Utils;

@Controller
@RequestMapping("/" + ProjectTeamTemplate.rootMapping)
public class ProjectTeamTemplateController extends AbstractController<ProjectTeamTemplate, ProjectTeamTemplateId>{

	@Autowired
	private ProjectWbsService projectWbsService;
	
	@Autowired
	private ProjectTeamService projectTeamService;

	@Autowired
	private ManhourJdbcService manhourJdbcService;

	public static final String[] lookuplists = null;
	public static final String[] detailTables = null;
	public static final String[][] detailFields = null;
	public static final String listView   = ProjectTeamTemplate.rootMapping + "List";
	public static final String editView   = ProjectTeamTemplate.rootMapping + "Edit";
	public static final String showView   = ProjectTeamTemplate.rootMapping + "Show";
	public static final String selectView = ProjectTeamTemplate.rootMapping + "Select";

	private static String[] domainNames = null;
	private static String[] domainlists = null;
	
	@Override
	protected String rootMapping() {return ProjectTeamTemplate.rootMapping;}

	@Override
	protected String tableName() {return ProjectTeamTemplate.tableName;}

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
		return ProjectTeam.rootMapping+"/show?id="+s[0]+","+s[2];
	}

	@Override
	protected String[] detailTables() {return detailTables;}

	@Override
	protected String[][] detailFields() {return detailFields;}
	
	@Override
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(ProjectTeamTemplate.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

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

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public ModelAndView selectGet(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName());
		
		String[] id = null;
		int projectId=0, teamId = 0;
		try {
			id = request.getParameter("id").split(",");
			projectId= Integer.parseInt(id[0]);
			teamId= Integer.parseInt(id[1]);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_PARAMETER_MISSING, tableName() + " : projectId,teamId");
		}
		String prevpage= "projectTeam/show?id=" + projectId + "," + teamId;
		ModelAndView model = new ModelAndView(selectView);
		String wbshtml = manhourJdbcService.findAllCategoryHtml(projectId, teamId, language.code);
		model.addObject("wbshtml", wbshtml);
		model.addObject("projectId", projectId);
		model.addObject("teamId", teamId);
		
		// Assign text objects from session language
		model.addObject(Labels.PREVPAGE, prevpage);
		model.addObject(Labels.PAGETITLE, language.getText(Category.tableName));
		model.addObject(Labels.SELECT, language.getIconText(Labels.SELECT));
		model.addObject(Labels.OK, language.getIconText(Labels.OK));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.PERSON_ID, language.getIconText(Labels.PERSON_ID));
		model.addObject(Subcontractor.tableName, language.getText(Subcontractor.tableName));
		for (int i = 0; i < Category.fieldNames.length; i++) {
			model.addObject(Category.fieldNames[i], language.getText(Category.fieldNames[i]));
		}
		return model;
	}
	
	@RequestMapping(value = "/select", method = RequestMethod.POST)
	public ModelAndView selectPost(HttpServletRequest request) throws IOException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		int projectId=0, teamId = 0;
		try {
			projectId= Integer.parseInt(request.getParameter("projectId"));
			teamId= Integer.parseInt(request.getParameter("teamId"));
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_PARAMETER_MISSING, tableName() + " : projectId,teamId");
		}
		
		System.out.println("Project Id : " + projectId);
		System.out.println("Team Id : " + teamId);
		String s = request.getParameter("WBS_IDS");
		System.out.println("Project WBSes : " + s);
		if (!Utils.emptyStr(s)) {
			String[] wid = s.split(",");
			for (int i = 1; i < wid.length; i++) {
				System.out.println("Processing : " + wid[i]);
				try {
					int w = Integer.parseInt(wid[i]);
					ProjectTeamTemplateId id = new ProjectTeamTemplateId(projectId, w, teamId);
					ProjectTeamTemplate ptt = modelService.findById(id);
					if (ptt == null) {
						System.out.println("Inserting Project Team Template : " + projectId + " " + teamId + " : " + w);
						ProjectWbs pw = projectWbsService.findById(new ProjectWbsId(projectId, w));
						ProjectTeam pt = projectTeamService.findById(new ProjectTeamId(projectId, teamId));
						ptt = new ProjectTeamTemplate(id, pt, pw);
						modelService.create(ptt);
					}
				} catch (Exception e) {
				}
			}
		}
		
		System.out.println("select Post Method");
		
		return new ModelAndView("redirect:/projectTeam/show?id=" + projectId + "," + teamId);
	}
}
