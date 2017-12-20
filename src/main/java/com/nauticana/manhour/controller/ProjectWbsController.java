package com.nauticana.manhour.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.manhour.model.Category;
import com.nauticana.manhour.model.Project;
import com.nauticana.manhour.model.ProjectWbs;
import com.nauticana.manhour.model.ProjectWbsId;
import com.nauticana.manhour.model.ProjectWbsManhour;
import com.nauticana.manhour.model.ProjectWbsQuantity;
import com.nauticana.manhour.service.CategoryService;
import com.nauticana.manhour.service.CbsService;
import com.nauticana.manhour.service.ManhourJdbcService;
import com.nauticana.manhour.service.ProjectService;
import com.nauticana.nams.abstrct.AbstractController;
import com.nauticana.nams.utils.Labels;
import com.nauticana.nams.utils.PortalLanguage;
import com.nauticana.nams.utils.Utils;

@Controller
@RequestMapping("/" + ProjectWbs.rootMapping)
public class ProjectWbsController extends AbstractController<ProjectWbs, ProjectWbsId>{

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProjectService ps;

	@Autowired
	private CbsService cbsService;

	@Autowired
	private ManhourJdbcService manhourJdbcService;

	public static final String[] lookuplists = new String[] {"cbsList"};
	public static final String[] detailTables = new String[] {ProjectWbsManhour.tableName, ProjectWbsQuantity.tableName};
	public static final String[][] detailFields = new String[][] {ProjectWbsManhour.fieldNames, ProjectWbsQuantity.fieldNames};
	public static final String listView   = ProjectWbs.rootMapping + "List";
	public static final String editView   = ProjectWbs.rootMapping + "Edit";
	public static final String showView   = ProjectWbs.rootMapping + "Show";
	public static final String selectView = ProjectWbs.rootMapping + "Select";

	private static String[] domainNames = null;
	private static String[] domainlists = null;
	
	@Override
	protected String rootMapping() {return ProjectWbs.rootMapping;}

	@Override
	protected String tableName() {return ProjectWbs.tableName;}

	@Override
	protected String listView() {return listView;}

	@Override
	protected String editView() {return editView;}

	@Override
	protected String showView() {return showView;}

	@Override
	protected String selectView() {return selectView;}

	@Override
	protected String prevPage(String id) {if (Utils.emptyStr(id)) return rootMapping()+"/list"; return Project.rootMapping+"/show?id="+id.split(",")[0];}

	@Override
	protected String[] detailTables() {return detailTables;}

	@Override
	protected String[][] detailFields() {return detailFields;}
	
	@Override
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(ProjectWbs.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

	@Override
	protected String[] domainlists() {return domainlists;}

	@Override
	protected String[] lookuplists() {return lookuplists;}

	@Override
	protected String[][] lookupService(int i) {
		switch (i) {
		case 0: return cbsService.findAllStr();
		default: return null;
		}
	}
	
	@Override
	protected String[] actions() {return ProjectWbs.actions;}

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

		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		List<String> ids = namsJdbcService.authorizedIds(username, Labels.PROJECT_TEAM, Labels.APPROVE_MANHOUR);
		List<Project> projects = ps.findByIds(ids);
		if (projects.size() == 0)
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.PROJECT_TEAM + "-" + Labels.APPROVE_MANHOUR);
		
		
		
		int projectId=0;
		
		String strId = request.getParameter("projectId");
		
		if (!Utils.emptyStr(strId))
				projectId = Integer.parseInt(strId);
		else {
			try {
				projectId= Integer.parseInt(request.getParameter("id"));
			} catch (Exception e) {
				return errorPage(language, Labels.ERR_PARAMETER_MISSING, " projectId");
			}
		}
		
		ModelAndView model = new ModelAndView(selectView);
		strId = request.getParameter("projectFilter");
		int projectFilter = projectId;
		if (!Utils.emptyStr(strId)) {
			model.addObject("projectFilter", strId);
			projectFilter =Integer.parseInt(strId);
		}

		String[] links = new String[] {"category/new?projectId=" + projectId + "&parentKey="};
		String[] caps  = new String[] {"<i class=\"fa fa-angle-double-right\"></i>" +  language.getIconText(Labels.NEW), language.getIconText(Labels.EDIT), language.getIconText(Labels.DELETE)};
		
		String wbshtml = manhourJdbcService.findAllCategoryHtml(projectId, projectFilter, "tv3", links, caps, language.code);
		String prevpage = "project/show?id="+ projectId;
		System.out.println(wbshtml);
		model.addObject("wbshtml", wbshtml);
		model.addObject("projectId", projectId);
		
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(ProjectWbs.tableName));
		model.addObject(Labels.SELECT, language.getIconText(Labels.SELECT));
		model.addObject(Labels.OK, language.getIconText(Labels.OK));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.SELECT_ALL, language.getIconText(Labels.SELECT_ALL));
		model.addObject(Labels.DE_SELECT, language.getIconText(Labels.DE_SELECT));
		model.addObject(Labels.TOGGLE_SELECT, language.getIconText(Labels.TOGGLE_SELECT));
		model.addObject(Labels.NEW, language.getIconText(Labels.NEW));
		model.addObject(Labels.EDIT, language.getIconText(Labels.EDIT));
		model.addObject(Labels.DELETE, language.getIconText(Labels.DELETE));
		model.addObject(Category.tableName, language.getText(Category.tableName));
		model.addObject(Labels.PREVPAGE,prevpage);
		model.addObject(Labels.CAPTION_FILTER, language.getText(Labels.CAPTION_FILTER));
		model.addObject(Labels.PROJECT_FILTER, language.getText(Labels.PROJECT_FILTER));
		
		
		model.addObject("projects", projects);
		model.addObject("projectId", projectId);
	
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

		int projectId=0;
		try {
			projectId= Integer.parseInt(request.getParameter("projectId"));
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_PARAMETER_MISSING, " projectId");
		}
		System.out.println("Project Id : " + projectId);
		String s = request.getParameter("WBS_IDS");
		System.out.println("Project WBSes : " + s);
		if (!Utils.emptyStr(s)) {
			String[] wid = s.split(",");
			for (int i = 1; i < wid.length; i++) {
				System.out.println("Processing : " + wid[i]);
				int w = Integer.parseInt(wid[i]);
				ProjectWbsId id = new ProjectWbsId(projectId, w);
				ProjectWbs pw = modelService.findById(id);
				if (pw == null) {
					System.out.println("Inserting Project WBS : " + projectId + " : " + w);
					pw = modelService.newEntity(projectId+"");
					Category category = categoryService.findById(w);
					pw.setCategory(category);
					pw.setId(id);
					pw.setMetric(0);
					pw.setQuantity(0);
					String u = category.getUnit();
					if (Utils.emptyStr(u))
						pw.setUnit("m");
					else
						pw.setUnit(u.substring(0,Math.min(3,u.length())));
					try {
						modelService.create(pw);
					} catch(Exception e) {
						return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
					}

				}
			}
		}
		
		System.out.println("select Post Method");
		return new ModelAndView("redirect:/project/show?id=" + projectId);
	}

}
