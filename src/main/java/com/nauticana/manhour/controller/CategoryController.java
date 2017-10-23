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
import com.nauticana.manhour.service.ProjectService;
import com.nauticana.nams.abstrct.AbstractController;
import com.nauticana.nams.utils.Labels;
import com.nauticana.nams.utils.PortalLanguage;
import com.nauticana.nams.utils.Utils;

@Controller
@RequestMapping("/" + Category.rootMapping)
public class CategoryController extends AbstractController<Category, Integer> {

	public static final String[] lookuplists = null;
	public static final String[] detailTables = null;
	public static final String[][] detailFields = null;
	public static final String listView = Category.rootMapping + "List";
	public static final String editView = Category.rootMapping + "Edit";
	public static final String showView = Category.rootMapping + "Show";
	public static final String selectView = Category.rootMapping + "Select";
	
	private static String[] domainNames = null;
	private static String[] domainlists = null;
	@Autowired
	protected ProjectService ps;
	@Override
	protected String rootMapping() {return Category.rootMapping;}

	@Override
	protected String tableName() {return Category.tableName;}

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
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(Category.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

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

	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listGet(HttpServletRequest request) throws IOException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		
		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName());
		
		List<String> ids = namsJdbcService.authorizedIds(username, Labels.PROJECT_TEAM, Labels.APPROVE_MANHOUR);
		List<Project> projects = ps.findByIds(ids);
		
		String strId = request.getParameter("projectId");
		int projectId = -1;
		if (!Utils.emptyStr(strId))
			projectId = Integer.parseInt(strId);

//		strId = request.getParameter("projectFilter");
//		int projectFilter = -1;
//		if (!Utils.emptyStr(strId))
//				projectFilter = Integer.parseInt(strId);

		
		// Assign text objects from session language
		// Read data and assign to model and view object
		ModelAndView model = new ModelAndView(listView());
		
		model.addObject("projects", projects);
		model.addObject("projectId", projectId);
//		model.addObject("projectFilter", projectFilter);
	
		
		model.addObject(Labels.PAGETITLE, language.getText(tableName()));
		model.addObject(Labels.NEW, language.getIconText(Labels.NEW));
		model.addObject(Labels.EDIT, language.getIconText(Labels.EDIT));
		model.addObject(Labels.DELETE, language.getIconText(Labels.DELETE));
		model.addObject(Labels.CAPTION_FILTER, language.getText(Labels.CAPTION_FILTER));
		model.addObject(Labels.PROJECT_FILTER, language.getText(Labels.PROJECT_FILTER));
		String[] links = new String[] {"category/new?parentKey=", "category/edit?id=", "category/delete?id="};
		String[] caps  = new String[] {"<i class=\"fa fa-angle-double-right\"></i>" +  language.getIconText(Labels.NEW), language.getIconText(Labels.EDIT), language.getIconText(Labels.DELETE)};
		String wbshtml = namsJdbcService.findAllCategoryHtml(projectId, projectId, "tv3", links, caps);
		System.out.println(wbshtml);
		model.addObject("wbshtml", wbshtml);
		return model;
	}
	
	@Override
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newGet(HttpServletRequest request) {
		ModelAndView model = super.newGet(request);
	
		String projectId = request.getParameter("projectId");
		if (!Utils.emptyStr(projectId)) {
			Category record = (Category) model.getModel().get("record");
			record.setProjectId(Integer.parseInt(projectId));
			String prevpage = "projectWbs/select?id=" + projectId;
			model.addObject(Labels.NEXTPAGE,prevpage);
			model.getModel().replace(Labels.PREVPAGE, prevpage) ;
		}

		return model;
	}

}
