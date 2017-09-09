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
import com.nauticana.manhour.model.ProjectWbs;
import com.nauticana.manhour.model.ProjectWbsId;
import com.nauticana.manhour.model.ProjectWbsManhour;
import com.nauticana.manhour.model.ProjectWbsQuantity;
import com.nauticana.manhour.query.ProjectCategory;
import com.nauticana.manhour.service.CategoryService;
import com.nauticana.manhour.service.UtilService;
import com.nauticana.manhour.utils.DataCache;
import com.nauticana.manhour.utils.Labels;
import com.nauticana.manhour.utils.PortalLanguage;
import com.nauticana.manhour.utils.Utils;

@Controller
@RequestMapping("/projectWbs")
public class ProjectWbsController extends AbstractController<ProjectWbs, ProjectWbsId>{

//	@Autowired
//	protected ProjectWbsService modelService;

	@Autowired
	private UtilService utilService;

	@Autowired
	private CategoryService categoryService;

	public ProjectWbsController() {
		super(ProjectWbs.tableName, "projectWbsList", "projectWbsEdit");
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public ModelAndView selectGet(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");
		if (!utilService.allowSelect(username, Category.tableName))
			return new ModelAndView("redirect:/unauthorized");
		
		// Read language of session
		PortalLanguage language = DataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		int projectId= Integer.parseInt(request.getParameter("id"));
		ModelAndView model = new ModelAndView("projectWBSSelect");
		List<ProjectCategory> records = utilService.findAllCategories(projectId);
		model.addObject("records", records);
		model.addObject("projectId", projectId);
		
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(Category.tableName));
		model.addObject(Labels.SELECT, language.getText(Labels.SELECT));
		model.addObject(Labels.OK, language.getText(Labels.OK));
		model.addObject(Labels.CANCEL, language.getText(Labels.CANCEL));
		model.addObject(Category.tableName, language.getText(Category.tableName));
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
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");
		if (!utilService.allowInsert(username, tableName))
			return new ModelAndView("redirect:/unauthorized");

		int projectId= Integer.parseInt(request.getParameter("PROJECT_ID"));
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
					pw.setWorkforce(0);
					String u = category.getUnit();
					if (Utils.emptyStr(u))
						pw.setUnit("m");
					else
						pw.setUnit(u.substring(0,Math.min(3,u.length())));
					modelService.create(pw);
				}
			}
		}
		
		System.out.println("select Post Method");
		return new ModelAndView("redirect:/project/show?id=" + projectId);
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView showGet(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");
		if (!utilService.allowSelect(username, tableName))
			return new ModelAndView("redirect:/unauthorized");
		
		// Read language of session
		PortalLanguage language = DataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		// Read data and assign to model and view object
		ProjectWbs projectWbs = modelService.findById(modelService.StrToId(request.getParameter("id")));
		
		ModelAndView model = new ModelAndView("projectWbsShow");
		model.addObject("record", projectWbs);
		
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(tableName));
		model.addObject(Labels.NEW, language.getText(Labels.NEW));
		model.addObject(Labels.EDIT, language.getText(Labels.EDIT));
		model.addObject(Labels.CHOOSE, language.getText(Labels.CHOOSE));
		model.addObject(Labels.DELETE, language.getText(Labels.DELETE));
		model.addObject(tableName, language.getText(tableName));
		for (int i = 0; i < modelService.getFieldNames().length; i++) {
			model.addObject(modelService.getFieldNames()[i], language.getText(modelService.getFieldNames()[i]));
		}
		model.addObject(ProjectWbsManhour.tableName, language.getText(ProjectWbsManhour.tableName));
		for (int i = 0; i < ProjectWbsManhour.fieldNames.length; i++) {
			model.addObject(ProjectWbsManhour.fieldNames[i], language.getText(ProjectWbsManhour.fieldNames[i]));
		}
		model.addObject(ProjectWbsQuantity.tableName, language.getText(ProjectWbsQuantity.tableName));
		for (int i = 0; i < ProjectWbsQuantity.fieldNames.length; i++) {
			model.addObject(ProjectWbsQuantity.fieldNames[i], language.getText(ProjectWbsQuantity.fieldNames[i]));
		}
		return model;
	}

}
