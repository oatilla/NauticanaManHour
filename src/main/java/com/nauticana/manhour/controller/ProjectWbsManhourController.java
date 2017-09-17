package com.nauticana.manhour.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
import com.nauticana.manhour.model.ProjectTeam;
import com.nauticana.manhour.model.ProjectTeamId;
import com.nauticana.manhour.model.ProjectTeamPerson;
import com.nauticana.manhour.model.ProjectWbs;
import com.nauticana.manhour.model.ProjectWbsManhour;
import com.nauticana.manhour.model.ProjectWbsManhourId;
import com.nauticana.manhour.service.ProjectService;
import com.nauticana.manhour.service.ProjectTeamService;
import com.nauticana.manhour.service.ProjectWbsManhourService;
import com.nauticana.manhour.utils.DataCache;
import com.nauticana.manhour.utils.Icons;
import com.nauticana.manhour.utils.Labels;
import com.nauticana.manhour.utils.PortalLanguage;
import com.nauticana.manhour.utils.Utils;

@Controller
@RequestMapping("/projectWbsManhour")
public class ProjectWbsManhourController extends AbstractController<ProjectWbsManhour, ProjectWbsManhourId>{

//	@Autowired
//	protected ProjectWbsManhourService modelService;

	public ProjectWbsManhourController() {
		super(ProjectWbsManhour.tableName, "projectWbsManhourList", "projectWbsManhourEdit");
	}

	@Autowired
	protected ProjectTeamService pts;
	
	@Autowired
	protected ProjectService ps;
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView showGet(HttpServletRequest request) throws IOException, ParseException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");
		if (!utilService.allowInsert(username, tableName))
			return new ModelAndView("redirect:/unauthorized");
		
		// Read language of session
		PortalLanguage language = DataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		int projectId = 0;
		int teamId = 0;

		String dateStr = request.getParameter("date");
		if (Utils.emptyStr(dateStr)) {
			dateStr = Labels.dmyDF.format(System.currentTimeMillis());
		}
		Date date= Labels.dmyDF.parse(dateStr);
		
		String id = request.getParameter("id");
		if (!Utils.emptyStr(id)) {
			String s[] = id.split(",");
			projectId = Integer.parseInt(s[0]);
			teamId = Integer.parseInt(s[1]);
			date = Labels.dmyDF.parse(s[2]);

			ProjectTeam team = pts.findById(new ProjectTeamId(projectId, teamId));
			ArrayList<ProjectTeamPerson> p = new ArrayList<ProjectTeamPerson>();
			ArrayList<Category> c = new ArrayList<Category>();
			for (ProjectTeamPerson x : team.getProjectTeamPersonnel()) p.add(x);
			for (Category x : team.getProjectTeamCategory()) c.add(x);

			String[][] manhour = pts.getWbsManhourByDate(team.getId().getProjectId(), team.getId().getTeamId(), date, (ProjectWbsManhourService) modelService);

			ModelAndView model = new ModelAndView("projectWbsManhourShow");
			model.addObject("records", manhour);
			model.addObject("date", dateStr);
			model.addObject("projectId", projectId);
			model.addObject("teamId", teamId);

			// Assign text objects from session language
			model.addObject(Labels.PAGETITLE, language.getText(ProjectWbsManhour.tableName));
			model.addObject(Labels.SEARCH, language.getText(Labels.SEARCH));
			model.addObject(Labels.SAVE, language.getText(Labels.SAVE));
			model.addObject(Icons.SAVE, Icons.getIcon(Icons.SAVE));
			model.addObject(Labels.CANCEL, language.getText(Labels.CANCEL));
			model.addObject(Icons.CANCEL, Icons.getIcon(Icons.CANCEL));
			model.addObject("DATATABLE1", Labels.dataTableSetting1);
			model.addObject(tableName, language.getText(tableName));
			for (int i = 0; i < modelService.getFieldNames().length; i++) {
				model.addObject(modelService.getFieldNames()[i], language.getText(modelService.getFieldNames()[i]));
			}
			return model;
		}
		return new ModelAndView("redirect:/projectWbsManhour/select");
	}

	@RequestMapping(value = "/show", method = RequestMethod.POST)
	public ModelAndView showPost(HttpServletRequest request) throws IOException, ParseException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");
		if (!utilService.allowInsert(username, tableName))
			return new ModelAndView("redirect:/unauthorized");
		
		// Read POSTED request variables
		String strProjectId = request.getParameter("projectId");
		String strTeamId = request.getParameter("teamId");
		String strDate = request.getParameter("date");
		String data = request.getParameter("data");

		System.out.println("Submitted projectId " + strProjectId + " teamId " + strTeamId + " date " + strDate + "\n data \n" + data);
		
		int projectId = Integer.parseInt(strProjectId);
		int teamId = Integer.parseInt(strTeamId);
		Date date = Labels.dmyDF.parse(strDate);
		ProjectTeam team = pts.findById(new ProjectTeamId(projectId, teamId));
		String[] lines = data.split("\n");
		String[] catStr = lines[0].split(",");
		int[] catIds = new int[catStr.length];
		for (int i = 1; i < catIds.length; i++) {
			catIds[i] = Integer.parseInt(catStr[i].trim());
		}

		for (int i = 3; i < lines.length; i++) {
			String[] cols = lines[i].split(",");
			int workerId = Integer.parseInt(cols[0].trim());
			ProjectTeamPerson p = null;
			for (ProjectTeamPerson x : team.getProjectTeamPersonnel()) {
				if (x.getId().getWorkerId() == workerId)
					p = x;
			}
			short mh, ot;
			boolean mhe, ote;
			for (int j = 1; j < (int) (cols.length / 2); j++) {
				try {
					mh = Short.parseShort(cols[j * 2].trim());
					mhe = true;
				} catch (Exception e) {
					mhe = false;
					mh = 0;
				}
				try {
					ot = Short.parseShort(cols[j * 2 + 1].trim());
					ote = true;
				} catch (Exception e) {
					ot = 0;
					ote = false;
				}
				if (ote || mhe) {
					ProjectWbsManhourId id = new ProjectWbsManhourId(projectId, catIds[j], teamId, workerId, date);
					ProjectWbsManhour entity = modelService.findById(id);
					if (entity == null) {
						entity = new ProjectWbsManhour();
						entity.setId(id);
						entity.setProjectTeamPerson(p);
						ProjectWbs w = null;
						for (ProjectWbs x : team.getProject().getProjectWbses()) {
							if (x.getCategory().getId() == catIds[j])
								w = x;
						}
						entity.setProjectWbs(w);
					}
					entity.setManhour(mh);
					entity.setOvertime(ot);
					modelService.save(entity);
				}
			}
		}
		return new ModelAndView("redirect:/projectWbsManhour/select");
	}
	
	
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public ModelAndView select(HttpServletRequest request) throws IOException, ParseException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");
		if (!utilService.allowSelect(username, tableName))
			return new ModelAndView("redirect:/unauthorized");
		
		// Read language of session
		PortalLanguage language = DataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		
		String dateStr = request.getParameter("date");
		if (Utils.emptyStr(dateStr)) {
			dateStr = Labels.dmyDF.format(System.currentTimeMillis());
		}
		
		List<Project> projects = ps.findAll();
		ModelAndView model = new ModelAndView("projectWbsManhourSelect");
		model.addObject("projects", projects);
		model.addObject("date", dateStr);
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(ProjectWbsManhour.tableName));
		model.addObject(Labels.SELECT, language.getText(Labels.SELECT));
		model.addObject(Icons.SELECT, Icons.getIcon(Icons.SELECT));
		model.addObject(Labels.OK, language.getText(Labels.OK));
		model.addObject(Icons.OK, Icons.getIcon(Icons.OK));
		model.addObject(Labels.CANCEL, language.getText(Labels.CANCEL));
		model.addObject(Icons.CANCEL, Icons.getIcon(Icons.SELECT));
		model.addObject(Icons.PROJECT_MANHOUR, Icons.getIcon(Icons.PROJECT_MANHOUR));
		model.addObject("DATATABLE1", Labels.dataTableSetting1);
		model.addObject(tableName, language.getText(tableName));
		for (int i = 0; i < modelService.getFieldNames().length; i++) {
			model.addObject(modelService.getFieldNames()[i], language.getText(modelService.getFieldNames()[i]));
		}
		return model;
	}

}
