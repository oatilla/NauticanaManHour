package com.nauticana.manhour.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.manhour.model.ExternalPerson;
import com.nauticana.manhour.model.Project;
import com.nauticana.manhour.model.ProjectTeam;
import com.nauticana.manhour.model.ProjectTeamId;
import com.nauticana.manhour.model.ProjectTeamPerson;
import com.nauticana.manhour.model.ProjectTeamPersonId;
import com.nauticana.manhour.model.ProjectTeamTemplate;
import com.nauticana.manhour.model.Worker;
import com.nauticana.manhour.service.ManhourJdbcService;
import com.nauticana.manhour.service.ProjectTeamPersonService;
import com.nauticana.manhour.service.WorkerService;
import com.nauticana.nams.abstrct.AbstractController;
import com.nauticana.nams.utils.Labels;
import com.nauticana.nams.utils.PortalLanguage;
import com.nauticana.nams.utils.Utils;

@Controller
@RequestMapping("/" + ProjectTeam.rootMapping)
public class ProjectTeamController extends AbstractController<ProjectTeam, ProjectTeamId>{

	public static final String[] lookuplists = null;
	public static final String[] detailTables = new String[] {ProjectTeamPerson.tableName, ProjectTeamTemplate.tableName,Worker.tableName};
	public static final String[][] detailFields = new String[][] {ProjectTeamPerson.fieldNames, ProjectTeamTemplate.fieldNames,Worker.fieldNames};
	public static final String[][] detailActions = new String[][] {ProjectTeamPerson.actions};
	public static final String listView   = ProjectTeam.rootMapping + "List";
	public static final String editView   = ProjectTeam.rootMapping + "Edit";
	public static final String showView   = ProjectTeam.rootMapping + "Show";
	public static final String selectView = ProjectTeam.rootMapping + "Select";

	private static String[] domainNames = null;
	private static String[] domainlists = null;
	
	@Override
	protected String rootMapping() {return ProjectTeam.rootMapping;}

	@Override
	protected String tableName() {return ProjectTeam.tableName;}

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
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(ProjectTeam.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

	@Override
	protected String[] domainlists() {return domainlists;}

	@Override
	protected String[] lookuplists() {return lookuplists;}

	@Override
	protected String[][] lookupService(int i) {
		return null;
	}

	@Override
	protected String[] actions() {return ProjectTeam.actions;}

	@Override
	protected String[][] detailActions() {return detailActions;}
	
	@Autowired
	private WorkerService workerService;
	
	@Autowired
	private ManhourJdbcService manhourJdbcService;
	
	@Autowired
	private ProjectTeamPersonService projectTeamPersonService;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newGet(HttpServletRequest request) {
		ModelAndView model = super.newGet(request);
		HttpSession session = request.getSession(true);
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		String parentKey = request.getParameter(Labels.PARENTKEY);
		String prevpage = prevPage(parentKey);
		model.addObject("SELECT_EMPLOYEE", language.getIconText("SELECT_EMPLOYEE"));
		model.addObject("SELECT_SUBCONTRACTOR", language.getIconText("SELECT_SUBCONTRACTOR"));
		model.addObject("ADD_SIBLINGS", language.getIconText("ADD_SIBLINGS"));
		model.addObject(Labels.PREVPAGE, prevpage);

		Worker w = null;
		String strPersonId = request.getParameter("personId");
		if (!Utils.emptyStr(strPersonId)) {
			int personId = Integer.parseInt(strPersonId);
			w = workerService.findByPersonId(personId);
			if (w == null) w = workerService.getLocalPerson(manhourJdbcService.localPersonnel(personId));
			model.addObject("personId", personId);
		}
		if (w == null) {
			String strWorkerId = request.getParameter("workerId");
			if (!Utils.emptyStr(strWorkerId)) {
				w = workerService.findById(Integer.parseInt(strWorkerId));
			}
		}
		if (w != null) {
			model.addObject("workerId", w.getId());
			model.addObject("workerCaption", w.getCaption());
		}
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView newPost(@ModelAttribute ProjectTeam record, BindingResult result, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		String nextpage = request.getParameter(Labels.NEXTPAGE);

		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		if (result.hasErrors()) {
			String msgText = "";
			for (ObjectError e : result.getAllErrors()) {
				msgText = msgText + "\n" + e.toString();
			}
			return errorPage(language, Labels.ERR_BINDING, msgText);
		}

		String strWorkerId = request.getParameter("workerId");
		if (!Utils.emptyStr(strWorkerId)) {
			Worker w = workerService.findById(Integer.parseInt(strWorkerId));
			if (w == null)
				return errorPage(language, Labels.ERR_RECORDNOTFOUND, " Worker Id : " + strWorkerId);
			try {
				ProjectTeam pt = modelService.create(record);
				ProjectTeamPersonId ptpId = new ProjectTeamPersonId(pt.getId().getProjectId(), pt.getId().getTeamId(), w.getId());
				ProjectTeamPerson ptp = new ProjectTeamPerson(ptpId,w,pt,(byte) 1);
				projectTeamPersonService.create(ptp);
				//if addSublings selected, team leads personnel added to team
				if ("1".equals(request.getParameter("addSublings")) && w.getPersonId() > 0) {
					ArrayList<String> filter = new ArrayList<String>();
					filter.add("SUPERVISOR" + "," + w.getPersonId());
					List<ExternalPerson> siblings = manhourJdbcService.localPersonnel(filter);
					for (ExternalPerson lp : siblings) {
						Worker sbl = workerService.findByPersonId(lp.getPersonId());
						if (sbl == null) sbl = workerService.getLocalPerson(lp);
						if (sbl != null) {
							ProjectTeamPersonId memberId = new ProjectTeamPersonId(pt.getId().getProjectId(), pt.getId().getTeamId(), sbl.getId());
							ProjectTeamPerson teamMember = projectTeamPersonService.findById(memberId);
							if (teamMember == null) {
								teamMember = new ProjectTeamPerson(memberId, sbl, pt, (byte) 0);
								teamMember = projectTeamPersonService.create(teamMember);
							}
						}
					}
				}
				nextpage = "projectTeam/show?id=" + pt.getId().toString();
			} catch(Exception e) {
				return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
			}

		}

		if (Utils.emptyStr(nextpage))
			return new ModelAndView("redirect:list");
		else
			return new ModelAndView("redirect:/" + nextpage);
	}
}
