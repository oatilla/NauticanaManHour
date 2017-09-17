package com.nauticana.manhour.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.manhour.model.Project;
import com.nauticana.manhour.model.ProjectTeam;
import com.nauticana.manhour.model.ProjectTeamPerson;
import com.nauticana.manhour.model.ProjectWbs;
import com.nauticana.manhour.utils.DataCache;
import com.nauticana.manhour.utils.Icons;
import com.nauticana.manhour.utils.Labels;
import com.nauticana.manhour.utils.PortalLanguage;
import com.nauticana.manhour.utils.Utils;

@Controller
@RequestMapping("/project")
public class ProjectController extends AbstractController<Project, Integer> {

//	@Autowired
//	protected ProjectService modelService;

	public ProjectController() {
		super(Project.tableName, "projectList", "projectEdit");
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
		Project project = modelService.findById(modelService.StrToId(request.getParameter("id")));
		
		ModelAndView model = new ModelAndView("projectShow");
		model.addObject("record", project);
		
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(tableName));
		model.addObject(Labels.NEW, language.getText(Labels.NEW));
		model.addObject(Labels.EDIT, language.getText(Labels.EDIT));
		model.addObject(Labels.CHOOSE, language.getText(Labels.CHOOSE));
		model.addObject(Labels.DELETE, language.getText(Labels.DELETE));
		model.addObject(Icons.EDIT, Icons.getIcon(Icons.EDIT));
		model.addObject(Icons.NEW, Icons.getIcon(Icons.NEW));
		model.addObject(Icons.CHOOSE, Icons.getIcon(Icons.CHOOSE));
		model.addObject(Icons.DELETE, Icons.getIcon(Icons.DELETE));
		model.addObject("DATATABLE1", Labels.dataTableSetting1);
		model.addObject("DATATABLE2", Labels.dataTableSetting2);
		model.addObject(tableName, language.getText(tableName));
		for (int i = 0; i < modelService.getFieldNames().length; i++) {
			model.addObject(modelService.getFieldNames()[i], language.getText(modelService.getFieldNames()[i]));
		}
		model.addObject(ProjectTeam.tableName, language.getText(ProjectTeam.tableName));
		for (int i = 0; i < ProjectTeam.fieldNames.length; i++) {
			model.addObject(ProjectTeam.fieldNames[i], language.getText(ProjectTeam.fieldNames[i]));
		}
		model.addObject(ProjectWbs.tableName, language.getText(ProjectWbs.tableName));
		for (int i = 0; i < ProjectWbs.fieldNames.length; i++) {
			model.addObject(ProjectWbs.fieldNames[i], language.getText(ProjectWbs.fieldNames[i]));
		}
		model.addObject(ProjectTeamPerson.tableName, language.getText(ProjectTeamPerson.tableName));

		return model;
	}

}
