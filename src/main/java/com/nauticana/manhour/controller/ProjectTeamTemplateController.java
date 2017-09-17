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
import com.nauticana.manhour.model.ProjectTeamTemplate;
import com.nauticana.manhour.model.ProjectTeamTemplateId;
import com.nauticana.manhour.model.ProjectWbs;
import com.nauticana.manhour.model.ProjectWbsId;
import com.nauticana.manhour.query.ProjectCategory;
import com.nauticana.manhour.service.ProjectWbsService;
import com.nauticana.manhour.utils.DataCache;
import com.nauticana.manhour.utils.Icons;
import com.nauticana.manhour.utils.Labels;
import com.nauticana.manhour.utils.PortalLanguage;
import com.nauticana.manhour.utils.Utils;

@Controller
@RequestMapping("/projectTeamTemplate")
public class ProjectTeamTemplateController extends AbstractController<ProjectTeamTemplate, ProjectTeamTemplateId>{

	@Autowired
	private ProjectWbsService projectWbsService;

	public ProjectTeamTemplateController() {
		super(ProjectTeamTemplate.tableName, "projectTeamTemplateList", "projectTeamTemplateEdit");
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
		String[] id = request.getParameter("id").split(",");
		int projectId= Integer.parseInt(id[0]);
		int teamId= Integer.parseInt(id[1]);
		ModelAndView model = new ModelAndView("projectTeamTemplateSelect");
		List<ProjectCategory> records = utilService.findAllCategories(projectId, teamId);
		model.addObject("records", records);
		model.addObject("projectId", projectId);
		model.addObject("projectId", teamId);
		
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(Category.tableName));
		model.addObject(Labels.SELECT, language.getText(Labels.SELECT));
		model.addObject(Labels.OK, language.getText(Labels.OK));
		model.addObject(Labels.CANCEL, language.getText(Labels.CANCEL));
		model.addObject(Icons.SELECT, Icons.getIcon(Icons.SELECT));
		model.addObject(Icons.OK, Icons.getIcon(Icons.OK));
		model.addObject(Icons.CANCEL, Icons.getIcon(Icons.CANCEL));
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
		int teamId= Integer.parseInt(request.getParameter("TEAM_ID"));
		System.out.println("Project Id : " + projectId);
		System.out.println("Project Id : " + teamId);
		String s = request.getParameter("WBS_IDS");
		System.out.println("Project WBSes : " + s);
		if (!Utils.emptyStr(s)) {
			String[] wid = s.split(",");
			for (int i = 1; i < wid.length; i++) {
				System.out.println("Processing : " + wid[i]);
				int w = Integer.parseInt(wid[i]);
				ProjectTeamTemplateId id = new ProjectTeamTemplateId(projectId, teamId, w);
				ProjectTeamTemplate ptt = modelService.findById(id);
				if (ptt == null) {
					System.out.println("Inserting Project Team Template : " + projectId + " " + teamId + " : " + w);
					ptt = modelService.newEntity(projectId+","+teamId);
					ProjectWbs pw = projectWbsService.findById(new ProjectWbsId(projectId, w));
					ptt.setProjectWbs(pw);;
					ptt.setId(id);
					modelService.create(ptt);
				}
			}
		}
		
		System.out.println("select Post Method");
		return new ModelAndView("redirect:/project/show?id=" + projectId);
	}
}
