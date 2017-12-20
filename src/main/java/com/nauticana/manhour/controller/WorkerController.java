package com.nauticana.manhour.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.manhour.model.ExternalPerson;
import com.nauticana.manhour.model.ProjectTeam;
import com.nauticana.manhour.model.ProjectTeamId;
import com.nauticana.manhour.model.ProjectTeamPerson;
import com.nauticana.manhour.model.ProjectTeamPersonId;
import com.nauticana.manhour.model.Subcontractor;
import com.nauticana.manhour.model.Worker;
import com.nauticana.manhour.service.ExternalOrganizationService;
import com.nauticana.manhour.service.ManhourJdbcService;
import com.nauticana.manhour.service.ProjectTeamPersonService;
import com.nauticana.manhour.service.ProjectTeamService;
import com.nauticana.manhour.service.SubcontractorService;
import com.nauticana.manhour.service.WorkerService;
import com.nauticana.nams.abstrct.AbstractController;
import com.nauticana.nams.utils.Labels;
import com.nauticana.nams.utils.PortalLanguage;
import com.nauticana.nams.utils.Utils;

@Controller
@RequestMapping("/" + Worker.rootMapping)
public class WorkerController  extends AbstractController<Worker,Integer> {

	public static final String[] lookuplists = new String[] {"organizationList"};
	public static final String[] detailTables = null;
	public static final String[][] detailFields = null;
	public static final String listView = Worker.rootMapping + "List";
	public static final String editView = Worker.rootMapping + "Edit";
	public static final String showView = Worker.rootMapping + "Show";
	public static final String selectView = Worker.rootMapping + "Select";

	private static String[] domainNames = null;
	private static String[] domainlists = null;
	
	@Override
	protected String rootMapping() {return Worker.rootMapping;}

	@Override
	protected String tableName() {return Worker.tableName;}

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
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(Worker.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

	@Override
	protected String[] domainlists() {return domainlists;}

	@Override
	protected String[] lookuplists() {return lookuplists;}

	@Override
	protected String[][] lookupService(int i) {
		switch (i) {
			case 0: return organizationService.findAllStr();
			case 1: return null;
		}
		return null;

	}

	@Override
	protected String[] actions() {return null;}

	@Override
	protected String[][] detailActions() {return null;}

	
	@Autowired
	private ExternalOrganizationService organizationService;
	
	@Autowired
	private ProjectTeamPersonService projectTeamPersonService;

	@Autowired
	private ProjectTeamService projectTeamService;

	@Autowired
	private SubcontractorService subcontractorService;

	@Autowired
	private ManhourJdbcService manhourJdbcService;

	@RequestMapping(value = "/selectPerson")
	public ModelAndView selectPersonnel(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
			return new ModelAndView("redirect:/unauthorized");
		
		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		String parentKey = request.getParameter(Labels.PARENTKEY);

		String operation = request.getParameter("operation");
		String nextpage = request.getParameter(Labels.NEXTPAGE);
		if (Utils.emptyStr(nextpage)) {
			nextpage = "project/show?id="+ parentKey;
		}
		String prevpage = nextpage;
//		System.out.println(" Next/prev page : " + nextpage);
		
		if (Labels.CHOOSE.equals(operation)) {
			String personIds = request.getParameter("personIds");
			if (!Utils.emptyStr(personIds)) {
				String[] p = personIds.split(",");
				ProjectTeam pt = projectTeamService.findById(new ProjectTeamId(parentKey));
				for (int i = 1; i < p.length; i++) {
					int personId = Integer.parseInt(p[i]);
					Worker w = ((WorkerService) modelService).findByPersonId(personId);
					if (w == null) w = ((WorkerService) modelService).getLocalPerson(manhourJdbcService.localPersonnel(personId));
					if (w != null) {
						ProjectTeamPersonId id = new ProjectTeamPersonId(pt.getId().getProjectId(), pt.getId().getTeamId(), w.getId());
						ProjectTeamPerson ptp = projectTeamPersonService.findById(id);
						if (ptp == null) {
							ptp = new ProjectTeamPerson(id, w, pt, (byte) 0);
							try {
								ptp = projectTeamPersonService.create(ptp);
							} catch(Exception e) {
								return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
							}
						}
					}
				}
			}
			if (Utils.emptyStr(nextpage))
				return new ModelAndView("redirect:list");
			else
				return new ModelAndView("redirect:/" + nextpage);
		}
		ArrayList<String> filter = new ArrayList<String>();
		String memberType = request.getParameter("memberType");
		String supervisor = request.getParameter("supervisor");
		
		ModelAndView model;
		if ("LEAD".equals(memberType))
			model = new ModelAndView("leadPersonSelect");
		else
			model = new ModelAndView("personSelect");

		model.addObject(Labels.PARENTKEY, parentKey);

		for (int i = 0; i < ExternalPerson.fieldNames.length; i++) {
			String s = request.getParameter(ExternalPerson.fieldNames[i]);
			if (!Utils.emptyStr(s)) filter.add(ExternalPerson.fieldNames[i] + "," + s);
			else s = "";
			model.addObject(ExternalPerson.fieldNames[i], language.getText(ExternalPerson.fieldNames[i]));
			model.addObject(ExternalPerson.fieldAttrs[i], s);
		}
		if (Labels.SEARCH.equals(operation)) {
			List<ExternalPerson> records = manhourJdbcService.localPersonnel(filter);
			model.addObject("records", records);
		}
		// if add siblings clicked
		if (supervisor != null) {
			model.addObject("supervisor",supervisor);
		}

		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(ExternalPerson.tableName));
		model.addObject(Labels.SEARCH, language.getIconText(Labels.SEARCH));
		model.addObject(Labels.CHOOSE, language.getIconText(Labels.CHOOSE));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.NEXTPAGE, nextpage);
		model.addObject(Labels.PREVPAGE, prevpage);
		model.addObject(ExternalPerson.tableName, language.getText(ExternalPerson.tableName));
		if (domainNames() != null)
			for (int i = 0; i < domainNames().length; i++) {
				model.addObject(domainlists()[i], getDomainValues(domainNames()[i], language));
			}
		if (lookuplists() != null)
			for (int i = 0; i < lookuplists().length; i++) {
				model.addObject(lookuplists()[i], getLookupValues(i));
			}	
		return model;
	}

	
	
	@RequestMapping(value = "/selectWorker", method = RequestMethod.GET)
	public ModelAndView selectWorkerGet(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		
		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName());

		ModelAndView model = new ModelAndView("workerSelect");

		List<Subcontractor> scl = subcontractorService.findAll();
		System.out.println("subcontractors : " + scl.size() + " subcontractors");
		model.addObject("subcontractors", scl);
		String s = request.getParameter("subcontractorId");
		if (Utils.emptyStr(s))	s = scl.get(0).getId()+"";
		model.addObject("subcontractorId", s);
		System.out.println("subcontractorId : " + s);

		List<Worker> workers = ((WorkerService)modelService).findBySubcontractor(subcontractorService.findById(Integer.parseInt(s)));
		model.addObject("workers", workers);
		String postlink = "worker/selectWorker";
		String parentKey = request.getParameter(Labels.PARENTKEY);
		String memberType = request.getParameter("memberType");
		String nextpage = request.getParameter(Labels.NEXTPAGE);
		if (Utils.emptyStr(nextpage)) {
			if (parentKey.split(",").length>1)
				nextpage = "projectTeam/show?id=" + parentKey;
			else
				nextpage = "project/show?id=" + parentKey;
		}
		String prevpage = nextpage;
		
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(Worker.tableName));
		model.addObject(Labels.SELECT, language.getIconText(Labels.SELECT));
		model.addObject(Labels.OK, language.getIconText(Labels.OK));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.POSTLINK	, postlink);
		model.addObject(Labels.NEXTPAGE, nextpage);
		model.addObject(Labels.PREVPAGE, prevpage);
		model.addObject(Labels.SUBCONTRACTOR, language.getIconText(Labels.SUBCONTRACTOR));
		model.addObject(Labels.PARENTKEY, parentKey);
		model.addObject("memberType", memberType);
		for (int i = 0; i < Worker.fieldNames.length; i++)
			model.addObject(Worker.fieldNames[i], language.getText(Worker.fieldNames[i]));
		if (domainNames() != null)
			for (int i = 0; i < domainNames().length; i++)
				model.addObject(domainlists()[i], getDomainValues(domainNames()[i], language));
		if (lookuplists() != null)
			for (int i = 0; i < lookuplists().length; i++)
				model.addObject(lookuplists()[i], getLookupValues(i));
		return model;
	}
	
	@RequestMapping(value = "/selectWorker", method = RequestMethod.POST)
	public ModelAndView selectWorkerPost(HttpServletRequest request) throws IOException {
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		String memberType = request.getParameter("memberType");
		String parentKey = request.getParameter(Labels.PARENTKEY);
		String nextpage = request.getParameter(Labels.NEXTPAGE);
//		System.out.println("Parentkey : " + parentKey);
//		String operation = request.getParameter("operation");
//		System.out.println("operation : " + operation);
//		System.out.println("nextpage : " + nextpage);
//		System.out.println("memberType : " + memberType);

		String workerIds = request.getParameter("workerIds");
		System.out.println("WorkerIds : " + workerIds);
		if (!Utils.emptyStr(workerIds)) {
			String[] p = workerIds.split(",");
			if ("LEAD".equals(memberType)) {
				return new ModelAndView("redirect:/projectTeam/new?parentKey=" + parentKey + "&memberType=" + memberType + "&workerId=" + p[1]);
			} else {
				ProjectTeam pt = projectTeamService.findById(new ProjectTeamId(parentKey));
				for (int i = 1; i < p.length; i++) {
					int workerId = Integer.parseInt(p[i]);
					Worker w = ((WorkerService) modelService).findById(workerId);
					ProjectTeamPersonId id = new ProjectTeamPersonId(pt.getId().getProjectId(), pt.getId().getTeamId(),	w.getId());
					ProjectTeamPerson ptp = projectTeamPersonService.findById(id);
					if (ptp == null) {
						ptp = new ProjectTeamPerson(id, w, pt, (byte) 0);
						try {
							ptp = projectTeamPersonService.create(ptp);
						} catch(Exception e) {
							return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
						}
					}
				}
			}
		}
		if (Utils.emptyStr(nextpage))
			return new ModelAndView("redirect:list");
		else
			return new ModelAndView("redirect:/" + nextpage);
	}
}
