package com.nauticana.manhour.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.manhour.model.ProjectTeam;
import com.nauticana.manhour.model.ProjectTeamId;
import com.nauticana.manhour.model.ProjectTeamPerson;
import com.nauticana.manhour.model.ProjectTeamTemplate;
import com.nauticana.manhour.utils.DataCache;
import com.nauticana.manhour.utils.Icons;
import com.nauticana.manhour.utils.Labels;
import com.nauticana.manhour.utils.PortalLanguage;
import com.nauticana.manhour.utils.Utils;

@Controller
@RequestMapping("/projectTeam")
public class ProjectTeamController extends AbstractController<ProjectTeam, ProjectTeamId>{

//	@Autowired
//	protected ProjectTeamService modelService;

	public ProjectTeamController() {
		super(ProjectTeam.tableName, "projectTeamList", "projectTeamEdit");
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
		ProjectTeam projectTeam = modelService.findById(modelService.StrToId(request.getParameter("id")));
		
		ModelAndView model = new ModelAndView("projectTeamShow");
		model.addObject("record", projectTeam);
		
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(tableName));
		model.addObject(Labels.NEW, language.getText(Labels.NEW));
		model.addObject(Labels.EDIT, language.getText(Labels.EDIT));
		model.addObject(Labels.CHOOSE, language.getText(Labels.CHOOSE));
		model.addObject(Labels.DELETE, language.getText(Labels.DELETE));
		model.addObject(Labels.PERSONNEL, language.getText(Labels.PERSONNEL));
		model.addObject(Labels.SUBCONTRACTOR, language.getText(Labels.SUBCONTRACTOR));
		model.addObject(Icons.EDIT, Icons.getIcon(Icons.EDIT));
		model.addObject(Icons.NEW, Icons.getIcon(Icons.NEW));
		model.addObject(Icons.DELETE, Icons.getIcon(Icons.DELETE));
		model.addObject("DATATABLE1", Labels.dataTableSetting1);
		model.addObject("DATATABLE2", Labels.dataTableSetting1);
		model.addObject(tableName, language.getText(tableName));
		for (int i = 0; i < modelService.getFieldNames().length; i++) {
			model.addObject(modelService.getFieldNames()[i], language.getText(modelService.getFieldNames()[i]));
		}
		model.addObject(ProjectTeamPerson.tableName, language.getText(ProjectTeamPerson.tableName));
		for (int i = 0; i < ProjectTeamPerson.fieldNames.length; i++) {
			model.addObject(ProjectTeamPerson.fieldNames[i], language.getText(ProjectTeamPerson.fieldNames[i]));
		}
		model.addObject(ProjectTeamTemplate.tableName, language.getText(ProjectTeamTemplate.tableName));
		for (int i = 0; i < ProjectTeamTemplate.fieldNames.length; i++) {
			model.addObject(ProjectTeamTemplate.fieldNames[i], language.getText(ProjectTeamTemplate.fieldNames[i]));
		}
		return model;
	}
}
