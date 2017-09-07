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
import com.nauticana.manhour.query.ProjectCategory;
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
	
}
