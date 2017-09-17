package com.nauticana.manhour.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.manhour.model.Category;
import com.nauticana.manhour.model.ProjectTeam;
import com.nauticana.manhour.model.ProjectTeamId;
import com.nauticana.manhour.model.ProjectTeamPerson;
import com.nauticana.manhour.model.ProjectTeamPersonId;
import com.nauticana.manhour.model.Subcontractor;
import com.nauticana.manhour.model.Worker;
import com.nauticana.manhour.query.LocalPerson;
import com.nauticana.manhour.service.ProjectTeamPersonService;
import com.nauticana.manhour.service.ProjectTeamService;
import com.nauticana.manhour.service.SubcontractorService;
import com.nauticana.manhour.service.UtilService;
import com.nauticana.manhour.service.WorkerService;
import com.nauticana.manhour.utils.DataCache;
import com.nauticana.manhour.utils.Icons;
import com.nauticana.manhour.utils.Labels;
import com.nauticana.manhour.utils.PortalLanguage;
import com.nauticana.manhour.utils.Utils;

@Controller
@RequestMapping("/worker")
public class WorkerController  extends AbstractController<Worker,Integer> {

	public WorkerController() {
		super(Worker.tableName, "workerList", "workerEdit");
	}

	@Autowired
	private UtilService us;

	@Autowired
	private ProjectTeamPersonService ptps;

	@Autowired
	private ProjectTeamService pts;

	@Autowired
	private SubcontractorService ss;

	@RequestMapping(value = "/selectPersonnel")
	public ModelAndView selectPersonnel(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");
		if (!utilService.allowSelect(username, LocalPerson.tableName))
			return new ModelAndView("redirect:/unauthorized");
		
		// Read language of session
		PortalLanguage language = DataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		String parentKey = request.getParameter("parentKey");
		String operation = request.getParameter("operation");
		String nextpage = request.getParameter(Labels.NEXTPAGE);
		if (Labels.SEARCH.equals(operation)) {
			ArrayList<String> filter = new ArrayList<String>();
			for (int i = 0; i < LocalPerson.fieldNames.length; i++) {
				String s = request.getParameter(LocalPerson.fieldNames[i]);
				if (!Utils.emptyStr(s)) filter.add(LocalPerson.fieldNames[i] + "," + s);
			}
			ModelAndView model = new ModelAndView("personnelSelect");
			if (filter.size() > 0) {
				List<LocalPerson> records = us.localPersonnel(filter);
				model.addObject("records", records);
			}
			// Assign text objects from session language
			model.addObject(Labels.PAGETITLE, language.getText(Category.tableName));
			model.addObject(Labels.SELECT, language.getText(Labels.SELECT));
			model.addObject(Labels.OK, language.getText(Labels.OK));
			model.addObject(Labels.CANCEL, language.getText(Labels.CANCEL));
			model.addObject(Icons.SELECT, Icons.getIcon(Icons.SELECT));
			model.addObject(Icons.OK, Icons.getIcon(Icons.OK));
			model.addObject(Icons.CANCEL, Icons.getIcon(Icons.CANCEL));
			model.addObject(Labels.NEXTPAGE, nextpage);
			model.addObject(LocalPerson.tableName, language.getText(LocalPerson.tableName));
			for (int i = 0; i < LocalPerson.fieldNames.length; i++) {
				model.addObject(LocalPerson.fieldNames[i], language.getText(LocalPerson.fieldNames[i]));
			}
			return model;
		} else {
			String personIds = request.getParameter("personIds");
			if (!Utils.emptyStr(personIds)) {
				String[] p = personIds.split(",");
				ProjectTeam pt = pts.findById(new ProjectTeamId(parentKey));
				for (int i = 0; i < p.length; i++) {
					int personId = Integer.parseInt(p[i]);
					Worker w = ((WorkerService) modelService).findByPersonId(personId);
					if (w == null) {
						LocalPerson lp = us.localPersonnel(personId);
						w = new Worker();
						w.setCaption(lp.getName() + " " + lp.getSurname());
						w.setPersonId(personId);
						w = modelService.create(w);
					}
					ProjectTeamPersonId id = new ProjectTeamPersonId(pt.getId().getProjectId(), pt.getId().getTeamId(), w.getId());
					ProjectTeamPerson ptp = ptps.findById(id);
					if (ptp == null) {
						ptp = new ProjectTeamPerson(id, w, pt, '-');
						ptp = ptps.create(ptp);
					}
				}
			}
			if (Utils.emptyStr(nextpage))
				return new ModelAndView("redirect:list");
			else
				return new ModelAndView("redirect:" + nextpage);
		}
	}
	
	@RequestMapping(value = "/selectWorker")
	public ModelAndView selectWorker(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");
		if (!utilService.allowSelect(username, Worker.tableName))
			return new ModelAndView("redirect:/unauthorized");
		
		// Read language of session
		PortalLanguage language = DataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		String parentKey = request.getParameter("parentKey");
		System.out.println("Parentkey : " + parentKey);
		String operation = request.getParameter("operation");
		System.out.println("Operation : " + operation);
		String nextpage = request.getParameter(Labels.NEXTPAGE);
		System.out.println("Nextpage : " + nextpage);
		if (Labels.CHOOSE.equals(operation)) {
			String workerIds = request.getParameter("workerIds");
			System.out.println("WorkerIds : " + workerIds);
			if (!Utils.emptyStr(workerIds)) {
				String[] p = workerIds.split(",");
				ProjectTeam pt = pts.findById(new ProjectTeamId(parentKey));
				for (int i = 1; i < p.length; i++) {
					int workerId = Integer.parseInt(p[i]);
					Worker w = ((WorkerService) modelService).findById(workerId);
					ProjectTeamPersonId id = new ProjectTeamPersonId(pt.getId().getProjectId(), pt.getId().getTeamId(),
							w.getId());
					ProjectTeamPerson ptp = ptps.findById(id);
					if (ptp == null) {
						ptp = new ProjectTeamPerson(id, w, pt, '-');
						ptp = ptps.create(ptp);
					}
				}
			}
			if (Utils.emptyStr(nextpage))
				return new ModelAndView("redirect:list");
			else
				return new ModelAndView("redirect:" + nextpage);
		}
		ModelAndView model = new ModelAndView("workerSelect");
		List<Subcontractor> scl = ss.findAll();
		System.out.println("subcontractors : " + scl.size() + " subcontractors");
		model.addObject("subcontractors", scl);
		String s = request.getParameter("subcontractorId");
		if (Utils.emptyStr(s))	s = scl.get(0).getId()+"";
		List<Worker> workers = ((WorkerService)modelService).findBySubcontractor(ss.findById(Integer.parseInt(s)));
		model.addObject("workers", workers);
		model.addObject("subcontractorId", s);
		System.out.println("subcontractorId : " + s);
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(Category.tableName));
		model.addObject(Labels.SELECT, language.getText(Labels.SELECT));
		model.addObject(Labels.OK, language.getText(Labels.OK));
		model.addObject(Labels.CANCEL, language.getText(Labels.CANCEL));
		model.addObject(Icons.SELECT, Icons.getIcon(Icons.SELECT));
		model.addObject(Icons.OK, Icons.getIcon(Icons.OK));
		model.addObject(Icons.CANCEL, Icons.getIcon(Icons.CANCEL));
		model.addObject(Labels.NEXTPAGE, nextpage);
		model.addObject("parentKey", parentKey);
		model.addObject(Worker.tableName, language.getText(Worker.tableName));
		for (int i = 0; i < Worker.fieldNames.length; i++) {
			model.addObject(Worker.fieldNames[i], language.getText(Worker.fieldNames[i]));
		}
		return model;
	}
	
	
}
