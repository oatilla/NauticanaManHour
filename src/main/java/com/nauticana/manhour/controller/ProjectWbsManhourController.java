package com.nauticana.manhour.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
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
import com.nauticana.manhour.model.ProjectWbsId;
import com.nauticana.manhour.model.ProjectWbsManhour;
import com.nauticana.manhour.model.ProjectWbsManhourId;
import com.nauticana.manhour.model.ViewProjectWbsMhApprove;
import com.nauticana.manhour.service.CategoryService;
import com.nauticana.manhour.service.ManhourJdbcService;
import com.nauticana.manhour.service.ProjectService;
import com.nauticana.manhour.service.ProjectTeamService;
import com.nauticana.manhour.service.ProjectWbsManhourService;
import com.nauticana.manhour.service.ProjectWbsService;
import com.nauticana.nams.abstrct.AbstractController;
import com.nauticana.nams.utils.CategoryComparator;
import com.nauticana.nams.utils.Icons;
import com.nauticana.nams.utils.Labels;
import com.nauticana.nams.utils.PortalLanguage;
import com.nauticana.nams.utils.Utils;

@Controller
@RequestMapping("/" + ProjectWbsManhour.rootMapping)
public class ProjectWbsManhourController extends AbstractController<ProjectWbsManhour, ProjectWbsManhourId>{

	@Autowired
	protected ProjectService ps;

	@Autowired
	protected CategoryService cs;

	@Autowired
	protected ProjectTeamService pts;
	
	@Autowired
	protected ProjectWbsService pws;
	
	@Autowired
	private ManhourJdbcService manhourJdbcService;

	public static final String[] lookuplists = null;
	public static final String[] detailTables = null;
	public static final String[][] detailFields = null;
	public static final String listView   = ProjectWbsManhour.rootMapping + "List";
	public static final String editView   = ProjectWbsManhour.rootMapping + "Edit";
	public static final String showView   = ProjectWbsManhour.rootMapping + "Show";
	public static final String selectView = ProjectWbsManhour.rootMapping + "Select";

	private static String[] domainNames = null;
	private static String[] domainlists = null;
	
	@Override
	protected String rootMapping() {return ProjectWbsManhour.rootMapping;}

	@Override
	protected String tableName() {return ProjectWbsManhour.tableName;}

	@Override
	protected String listView() {return listView;}

	@Override
	protected String editView() {return editView;}

	@Override
	protected String showView() {return showView;}

	@Override
	protected String selectView() {return selectView;}

	@Override
	protected String prevPage(String id) {
		if (Utils.emptyStr(id)) return rootMapping()+"/list";
		String[] s = id.split(",");
		return ProjectWbs.rootMapping+"/show?id="+s[0]+","+s[1];
	}

	@Override
	protected String[] detailTables() {return detailTables;}

	@Override
	protected String[][] detailFields() {return detailFields;}
	
	@Override
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(ProjectWbsManhour.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

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

	
	@RequestMapping(value = "/approve", method = RequestMethod.GET)
	public ModelAndView approveGet(HttpServletRequest request) throws IOException, ParseException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		List<String> ids = namsJdbcService.authorizedIds(username, Labels.PROJECT_TEAM, Labels.APPROVE_MANHOUR);
		List<Project> projects = ps.findByIds(ids);
		if (projects.size() == 0)
			return errorPage(language, "ERR_UNAUTHORIZED", Labels.PROJECT_TEAM + "-" + Labels.APPROVE_MANHOUR);
		int projectId = projects.get(0).getId();
		String strId = request.getParameter("projectId");
		if (!Utils.emptyStr(strId))
			projectId = Integer.parseInt(strId);
		List<ViewProjectWbsMhApprove> records = manhourJdbcService.getProjectWbsMhApprove(projectId, language.code);
		ModelAndView model = new ModelAndView("projectWbsManhourApprove");
		model.addObject("projects", projects);
		model.addObject("projectId", projectId);
		model.addObject("records", records);
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(ProjectWbsManhour.tableName));
		model.addObject(Labels.SELECT,    language.getIconText(Labels.SELECT));
		model.addObject(Labels.OK,        language.getIconText(Labels.OK));
		model.addObject(Labels.CANCEL,    language.getIconText(Labels.CANCEL));
		model.addObject(Labels.TENDER,    language.getText(Labels.TENDER));
		model.addObject(Labels.APPROVE_ALL, language.getIconText(Labels.APPROVE_ALL));
		model.addObject(Labels.APPROVE_MANHOUR, language.getIconText(Labels.APPROVE_MANHOUR));
		model.addObject(Labels.APPROVED_MANHOUR,   language.getText(Labels.APPROVED_MANHOUR));
		model.addObject(Labels.MANHOUR_TO_APPROVE, language.getText(Labels.MANHOUR_TO_APPROVE));
		model.addObject(Labels.MANHOUR,    language.getText(Labels.MANHOUR));
		model.addObject(Labels.dataTableNames[0], Labels.dataTableSettings[0]);
		model.addObject(ViewProjectWbsMhApprove.tableName, language.getText(ViewProjectWbsMhApprove.tableName));
		for (int i = 0; i < ViewProjectWbsMhApprove.fieldNames.length; i++) {
			model.addObject(ViewProjectWbsMhApprove.fieldNames[i], language.getText(ViewProjectWbsMhApprove.fieldNames[i]));
		}
		return model;
	}

	@RequestMapping(value = "/approve", method = RequestMethod.POST)
	public ModelAndView approvePost(HttpServletRequest request) throws IOException, ParseException {
			
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
			
		// Read POSTED request variables
		String strProjectId = request.getParameter("projectId");
		String strCategoryId = request.getParameter("categoryId");
		String strTeamId = request.getParameter("teamId");
		String strActivityDate = request.getParameter("activityDate");
		
		System.out.println("Submitted projectId " + strProjectId + " categoryId " + strCategoryId + " teamId " + strTeamId + " activityDateId " + strActivityDate);
			
		Date activityDate;
		try {activityDate = Labels.ymdDF.parse(strActivityDate);} catch (Exception e) {activityDate = null;}
		try {
			((ProjectWbsManhourService)modelService).approve(Integer.parseInt(strProjectId), Integer.parseInt(strCategoryId), Integer.parseInt(strTeamId), activityDate, null);
		} catch(Exception e) {
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
		return new ModelAndView("redirect:/" + rootMapping() + "/approve?projectId="+strProjectId);
	}
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public ModelAndView select(HttpServletRequest request) throws IOException, ParseException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName());

		String dateStr = request.getParameter("date");
		if (Utils.emptyStr(dateStr)) {
			dateStr = Labels.ymdDF.format(System.currentTimeMillis());
		}
		
		List<String> ids = namsJdbcService.authorizedIds(username, Labels.PROJECT_TEAM, Labels.APPROVE_MANHOUR);
		List<Project> projects = ps.findByIds(ids);
		if (projects.size() == 0)
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.PROJECT_TEAM + "-" + Labels.APPROVE_MANHOUR);
		int projectId = projects.get(0).getId();
		String strId = request.getParameter("projectId");
		if (!Utils.emptyStr(strId))
			projectId = Integer.parseInt(strId);
		ModelAndView model = new ModelAndView(selectView);
		model.addObject("projects", projects);
		model.addObject("projectId", projectId);
		model.addObject("date", dateStr);
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(ProjectWbsManhour.tableName));
		model.addObject(Labels.SELECT, language.getIconText(Labels.SELECT));
		model.addObject(Labels.OK, language.getIconText(Labels.OK));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Icons.PROJECT_MANHOUR, Icons.getIcon(Icons.PROJECT_MANHOUR));
		model.addObject(Labels.PROJECT_MANHOUR_1, language.getIconText(Labels.PROJECT_MANHOUR_1));
		model.addObject(Labels.PROJECT_MANHOUR_N, language.getIconText(Labels.PROJECT_MANHOUR_N));
		model.addObject(Labels.dataTableNames[0], Labels.dataTableSettings[0]);
		model.addObject(tableName(), language.getText(tableName()));
		for (int i = 0; i < modelService.fieldNames().length; i++) {
			model.addObject(modelService.fieldNames()[i], language.getText(modelService.fieldNames()[i]));
		}
		return model;
	}

	

	@RequestMapping(value = "/editTeam", method = RequestMethod.GET)
	public ModelAndView editTeamGet(HttpServletRequest request) throws IOException, ParseException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		int projectId = 0;
		int teamId = 0;

//		String dateStr = request.getParameter("date");
//		if (Utils.emptyStr(dateStr)) {
//			dateStr = Labels.dmyDF.format(System.currentTimeMillis());
//		}
//		Date date= Labels.dmyDF.parse(dateStr);
//		
		String id = request.getParameter("id");
		if (!Utils.emptyStr(id)) {
			System.out.println(id);
//			System.out.println(dateStr);
			
			String s[] = id.split(",");
			projectId = Integer.parseInt(s[0]);
			teamId = Integer.parseInt(s[1]);
			Date date = Labels.ymdDF.parse(s[2]);

			ProjectTeam team = pts.findById(new ProjectTeamId(projectId, teamId));
//			ArrayList<ProjectTeamPerson> p = new ArrayList<ProjectTeamPerson>(team.getProjectTeamPersonnel());
//			List<Category> c = new ArrayList<Category>(team.getProjectTeamCategory());
			List<String>   lc = new ArrayList<String>(manhourJdbcService.getProjectTeamTemplate(projectId, teamId, date));
			List<Category> c = cs.findByIds(lc);
			Collections.sort(c, new CategoryComparator());
			String[] captions = new String[c.size()];
			ProjectWbsId pwId = new ProjectWbsId();
			pwId.setProjectId(projectId);
			for (int i = 0; i < c.size(); i++) {
				pwId.setCategoryId(c.get(i).getId());
				captions[i] = pws.findById(pwId).getCustomerWbsCaption();
				if (Utils.emptyStr(captions[i]))
					captions[i] = c.get(i).getCaption();
			}

//			String[][] manhour = pts.getWbsManhourByDateOvertime(team.getId().getProjectId(), team.getId().getTeamId(), date, (ProjectWbsManhourService) modelService);
			String[][] manhour = pts.getWbsManhourByDate(team.getId().getProjectId(), team.getId().getTeamId(), date, (ProjectWbsManhourService) modelService, c, captions);

			String cats = "";
			String pers = "";
			for (int i = 3; i < manhour.length-1; i++) {
				pers = pers + "," + manhour[i][0];
			}
			for (int i = 2; i < manhour[0].length; i++) {
				cats = cats + "," + manhour[0][i];
			}
			ModelAndView model = new ModelAndView("projectWbsManhourEditTeam");
			model.addObject("records", manhour);
			model.addObject("cats", cats);
			model.addObject("pers", pers);
			model.addObject("date", s[2]);
			model.addObject("projectId", projectId);
			model.addObject("teamId", teamId);
			model.addObject("teamCaption", team.getCaption());

			// Assign text objects from session language
			model.addObject(Labels.DESCRIPTION, language.getText(Labels.DESCRIPTION));
			model.addObject(Labels.PAGETITLE, language.getText(ProjectWbsManhour.tableName));
			model.addObject(Labels.SEARCH, language.getIconText(Labels.SEARCH));
			model.addObject(Labels.SAVE, language.getIconText(Labels.SAVE));
			model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
			model.addObject(Labels.PREVPAGE, rootMapping()+"/select?projectId=" + projectId + "&date=" + s[2]);
			model.addObject("DATATABLE1", Labels.dataTableSetting1);
			model.addObject(tableName(), language.getText(tableName()));

			for (int i = 0; i < modelService.fieldNames().length; i++) {
				model.addObject(modelService.fieldNames()[i], language.getText(modelService.fieldNames()[i]));
			}
			return model;
		}
		return new ModelAndView("redirect:/projectWbsManhour/select");
	}

	@RequestMapping(value = "/editTeam", method = RequestMethod.POST)
	public ModelAndView editTeamPost(HttpServletRequest request) throws IOException, ParseException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		// Read POSTED request variables
		String strProjectId = request.getParameter("projectId");
		String strTeamId = request.getParameter("teamId");
		String strDate = request.getParameter("date");
		String strCats = request.getParameter("cats");
		String strPers = request.getParameter("pers");
		String data = request.getParameter("data");

		System.out.println("Submitted projectId " + strProjectId + " teamId " + strTeamId + " date " + strDate + "\n data \n" + data);
		
		int projectId=0, teamId = 0;
		Date date=null;
		try {
			projectId = Integer.parseInt(strProjectId);
			teamId = Integer.parseInt(strTeamId);
			date = Labels.ymdDF.parse(strDate);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_PARAMETER_MISSING, tableName() + " : projectId,teamId,date");
		}

		String[] catStr = strCats.split(",");
		int[] catIds = new int[catStr.length];
		for (int i = 1; i < catIds.length; i++) {
			catIds[i] = Integer.parseInt(catStr[i].trim());
		}
		String[] perStr = strPers.split(",");
		int[] workerIds = new int[perStr.length];
		for (int i = 1; i < workerIds.length; i++) {
			workerIds[i] = Integer.parseInt(perStr[i].trim());
		}
		
		ProjectTeam team = pts.findById(new ProjectTeamId(projectId, teamId));
		String[] lines = data.split("\n");

		for (int i = 2; i < lines.length-1; i++) {
			String[] cols = lines[i].split(",");
			int workerId = workerIds[i-1];
			ProjectTeamPerson p = null;
			for (ProjectTeamPerson x : team.getProjectTeamPersonnel()) {
				if (x.getId().getWorkerId() == workerId)
					p = x;
			}
			if (p == null)
				return errorPage(language, Labels.ERR_WORKERNOTFOUND, "workerId : " + workerId);
			short mh, ot;
			boolean mhe, ote;
			for (int j = 1; j < cols.length-4; j++) {
				try {
					mh = Short.parseShort(cols[j].trim());
					mhe = true;
				} catch (Exception e) {
					mhe = false;
					mh = 0;
				}
//				try {
//					ot = Short.parseShort(cols[j * 2 + 1].trim());
//					ote = true;
//				} catch (Exception e) {
					ot = 0;
					ote = false;
//				}
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
						entity.setStatus(Labels.INITIAL);
					}
					entity.setManhour(mh);
					entity.setOvertime(ot);
					try {
						modelService.save(entity);
					} catch(Exception e) {
						return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
					}
				}
			}
		}
		return new ModelAndView("redirect:/projectWbsManhour/select?projectId=" + projectId + "&date=" + strDate);
	}
	
	
	
	@RequestMapping(value = "/editTeamLead", method = RequestMethod.GET)
	public ModelAndView editTeamLeadGet(HttpServletRequest request) throws IOException, ParseException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		int projectId = 0;
		int teamId = 0;

		String dateStr = request.getParameter("date");
		if (Utils.emptyStr(dateStr)) {
			dateStr = Labels.ymdDF.format(System.currentTimeMillis());
		}
		Date date= Labels.ymdDF.parse(dateStr);
		
		String id = request.getParameter("id");
		if (!Utils.emptyStr(id)) {
			try {
				String s[] = id.split(",");
				projectId = Integer.parseInt(s[0]);
				teamId = Integer.parseInt(s[1]);
				date = Labels.ymdDF.parse(s[2]);
				dateStr = Labels.ymdDF.format(date);
			} catch (Exception e) {
				return errorPage(language, Labels.ERR_WRONG_PARAMETER, tableName() + " : " + id + " for projectId,teamId,date for ");
			}
			
			ProjectTeam team = pts.findById(new ProjectTeamId(projectId, teamId));
			ProjectTeamPerson p = null;
			for (ProjectTeamPerson x : team.getProjectTeamPersonnel())
				if (x.getTeamLead() == 1)
					p = x;
			if (p == null)
				return errorPage(language, Labels.ERR_TEAMLEADNOTFOUND, "projectId,teamId : " + projectId + "," + teamId);
			int workerId = p.getId().getWorkerId();
			// ArrayList<Category> c = new ArrayList<Category>();
			List<Category> c = new ArrayList<Category>(team.getProjectTeamCategory());
			Collections.sort(c, new CategoryComparator());
			String[] captions = new String[c.size()];
			ProjectWbsId pwId = new ProjectWbsId();
			pwId.setProjectId(projectId);
			for (int i = 0; i < c.size(); i++) {
				pwId.setCategoryId(c.get(i).getId());
				captions[i] = pws.findById(pwId).getCustomerWbsCaption();
				if (Utils.emptyStr(captions[i]))
					captions[i] = c.get(i).getCaption();
			}
			String[][] manhour = pts.getWbsManhourLeadByDate(team.getId().getProjectId(), team.getId().getTeamId(), workerId, date, (ProjectWbsManhourService) modelService, c, captions);

			ModelAndView model = new ModelAndView("projectWbsManhourEditTeamLead");
			model.addObject("records", manhour);
			model.addObject("date", dateStr);
			model.addObject("projectId", projectId);
			model.addObject("teamId", teamId);
			model.addObject("workerId", workerId);
			model.addObject("workerCaption", p.getWorker().getCaption());

			// Assign text objects from session language
			model.addObject(Labels.PAGETITLE, language.getText(ProjectWbsManhour.tableName));
			model.addObject(Labels.SEARCH, language.getIconText(Labels.SEARCH));
			model.addObject(Labels.SAVE, language.getIconText(Labels.SAVE));
			model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
			model.addObject(Labels.PREVPAGE, rootMapping()+"/select?projectId=" + projectId + "&date=" + dateStr);
			model.addObject("DATATABLE1", Labels.dataTableSetting1);
			model.addObject(tableName(), language.getText(tableName()));
			for (int i = 0; i < modelService.fieldNames().length; i++) {
				model.addObject(modelService.fieldNames()[i], language.getText(modelService.fieldNames()[i]));
			}
			return model;
		}
		return new ModelAndView("redirect:/projectWbsManhour/select?" + "date=" + dateStr);
	}

	@RequestMapping(value = "/editTeamLead", method = RequestMethod.POST)
	public ModelAndView editTeamLeadPost(HttpServletRequest request) throws IOException, ParseException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		// Read POSTED request variables
		String strProjectId = request.getParameter("projectId");
		String strTeamId = request.getParameter("teamId");
		String strWorkerId = request.getParameter("workerId");
		String strDate = request.getParameter("date");
		String data = request.getParameter("data");

		System.out.println("Submitted projectId " + strProjectId + " teamId " + strTeamId + " workerId " + strWorkerId + " date " + strDate + "\n data \n" + data);
		
		int projectId=0, teamId = 0, workerId = 0;
		Date date=null;
		try {
			projectId = Integer.parseInt(strProjectId);
			teamId = Integer.parseInt(strTeamId);
			workerId = Integer.parseInt(strWorkerId);
			date = Labels.ymdDF.parse(strDate);
		} catch (Exception e) {
			return errorPage(language, Labels.ERR_PARAMETER_MISSING, tableName() + " : projectId,teamId,workerId,date");
		}
		
		ProjectTeam team = pts.findById(new ProjectTeamId(projectId, teamId));
		String[] lines = data.split("\n");

		ProjectTeamPerson p = null;
		for (ProjectTeamPerson x : team.getProjectTeamPersonnel()) {
			if (x.getId().getWorkerId() == workerId)
				p = x;
		}
		if (p == null)
			return errorPage(language, Labels.ERR_TEAMLEADNOTFOUND, "projectId,teamId,workerId : " + projectId + "," + teamId + "," + workerId);
		for (int i = 1; i < lines.length-1; i++) {
			String[] cols = lines[i].split(",");
			int catId = Integer.parseInt(cols[0].trim());
			short mh=0, lh=0, fh=0, th=0;
			boolean mhe;
			try {mh = Short.parseShort(cols[3].trim());	mhe = true;	} catch (Exception e) {	mhe = false;}
//			try {ot = Short.parseShort(cols[4].trim());	ote = true;	} catch (Exception e) {	ote = false;}
			try {lh = Short.parseShort(cols[4].trim());} catch (Exception e) {}
			try {fh = Short.parseShort(cols[5].trim());} catch (Exception e) {}
			try {th = Short.parseShort(cols[6].trim());} catch (Exception e) {}
			if (mhe) {
				ProjectWbsManhourId id = new ProjectWbsManhourId(projectId, catId, teamId, workerId, date);
				ProjectWbsManhour entity = modelService.findById(id);
				if (entity == null) {
					entity = new ProjectWbsManhour();
					entity.setId(id);
					entity.setProjectTeamPerson(p);
					ProjectWbs w = null;
					for (ProjectWbs x : team.getProject().getProjectWbses()) {
						if (x.getCategory().getId() == catId)
							w = x;
					}
					entity.setProjectWbs(w);
				}
				entity.setManhour(mh);
//					entity.setOvertime(ot);
				entity.setLocalMh(lh);
				entity.setForeignMh(fh);
				entity.setTrMh(th);
				entity.setStatus(Labels.INITIAL);
				try {
					modelService.save(entity);
				} catch(Exception e) {
					return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
				}
			}
		}
		return new ModelAndView("redirect:/projectWbsManhour/select?projectId=" + projectId + "&date=" + strDate);
	}
	
}
