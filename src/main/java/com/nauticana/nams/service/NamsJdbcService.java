package com.nauticana.nams.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ExternalPerson;
import com.nauticana.manhour.model.ReportProjectWbsStatus;
import com.nauticana.manhour.model.ViewProjectCategory;
import com.nauticana.manhour.model.ViewProjectWbsMhApprove;
import com.nauticana.manhour.model.ViewProjectWbsQtyApprove;
import com.nauticana.manhour.model.ViewWbsQuantity;
import com.nauticana.manhour.model.ViewWbsTeamStatus;
import com.nauticana.nams.model.UserMenu;
import com.nauticana.nams.repository.NamsJbdcRepository;
import com.nauticana.nams.utils.PortalLanguage;
import com.nauticana.nams.utils.Utils;

@Service
public class NamsJdbcService {

	@Autowired
	private NamsJbdcRepository r;
	
	public List<UserMenu> userMenu(String username) {
		return r.userMenu(username);
	}
	
	public String userMenu(String username, PortalLanguage language) {
		List<UserMenu> pages = userMenu(username);
		String menu = " <ul class=\"sidebar-menu\">\n";
		String last = "";

		for (UserMenu page : pages) {
			if (!page.getMenuCaption().equals(last)) {
				if (!Utils.emptyStr(last))	menu = menu+"   </ul>\n  </li>";
				last = page.getMenuCaption();
				menu=menu+"  <li class=\"treeview active\"> <a href=\"#\"><i class=\"fa " + page.getMenuIcon() + "\"></i> <span> " + language.getText(last) + " </span> <span class=\"pull-right-container\"> </span> </a>\n   <ul class=\"treeview-menu menu-open\" style=\"display: block;\">\n";
			}
			menu=menu+"    <li> <a href=\"#\" onClick=\"doAjaxGet('" + page.getUrl() + "');\"> <i class=\"fa " + page.getPageIcon() + "\"> " + language.getText(page.getPageCaption()) + " </i> </a> </li>" + System.lineSeparator();
		}
		if (!Utils.emptyStr(last))	menu = menu+"   </ul>\n  </li>\n";
		return menu+" </ul>\n";
	}

	public List<ViewProjectCategory> findAllCategories(int projectId) {
		return r.viewProjectCategory(projectId, -1);
	}
	
	public String findAllCategoryHtml(int projectId, int projectFilter, String clsName, String[] links, String[] caps) {
		int level = 1;
		boolean endLi = false;
		
		String s = "<UL class=\"" + clsName + "\">";
		List<ViewProjectCategory> cat = r.viewProjectCategory(projectId, projectFilter);
		ViewProjectCategory[] pr = new ViewProjectCategory[10];
		
		for (int i = 0; i< cat.size(); i++) {
			ViewProjectCategory c = cat.get(i);
			pr[c.getCatLevel()] = c;
			if (c.getCatLevel()>1)
				pr[c.getCatLevel()-1].hasChild = true;
			if (!Utils.emptyStr(c.getProjectId())) {
				c.checked = "checked=\"checked\"";
				for (int j = 1; j < c.getCatLevel(); j++)
					pr[j].expended = "checked=\"checked\"";
			} 
			if (links != null) {
				for (int j = 0; j < links.length; j++) {
					c.links = c.links + " &nbsp; <a href=\"#\" onClick=\"doAjaxGet('" + links[j] + c.getCategoryId() + "');\">" + caps[j] + "</a>"; 
				}
			}
			
		}
		
		for (ViewProjectCategory c : cat) {
			if (level < c.getCatLevel()) {
				s = s + "\n <UL>\n";
				endLi = false;
			}
			for (int i = c.getCatLevel(); i < level; i++) {
				s = s + "  </LI>\n </UL>\n";
				endLi = true;
			}
			if (endLi) s = s + "  </LI>\n";

			if (c.hasChild)
				s = s + "<LI><input type=checkbox name=\"Arrow\" id=\"" + c.getTreeCode() + "\" " + c.expended + "/><label><input type=checkbox name=\"WBS\" value=\"" + c.getCategoryId() + "\"" + c.checked + " /><span></span></label><label for=\"" + c.getTreeCode() + "\">" + c.getTreeCode() + " &nbsp; " + c.getCaption() + "</label>" + c.links;
			else
				s = s + "<LI><label><input type=checkbox name=\"WBS\" value=\"" + c.getCategoryId() + "\"" + c.checked + " /><span></span></label><label> &nbsp; &nbsp; " + c.getTreeCode() + " &nbsp; " + c.getCaption() + "</label>" + c.links;
			
//			if (Utils.emptyStr(c.getProjectId()))
//				s = s + "  <LI> " + "<input type=checkbox class=\"flat-red\" name=\"WBS\" value=\"" + c.getCategoryId() + "\"/> <b> " + c.getTreeCode() + " </b> " + c.getCaption(); 
//			else
//				s = s + "  <LI> " + "<input type=checkbox class=\"flat-red\" name=\"WBS\" value=\"" + c.getCategoryId() + "\" checked=\"checked\"/> <b> " + c.getTreeCode() + " </b> " + c.getCaption();
			endLi = true;
			level = c.getCatLevel();
		}
		for (int i = 1; i < level; i++) {
			s = s + "  </LI>\n </UL>\n";
			endLi = true;
		}
		if (endLi) s = s + "  </LI>\n";
		return s + "</UL>\n";
	}
	
	public List<ViewProjectCategory> findAllCategories(int projectId, int teamId) {
		return r.teamCategory(projectId, teamId);
	}
	
	public String findAllCategoryHtml(int projectId, int teamId) {
		String s = "";
		for (ViewProjectCategory c : r.teamCategory(projectId, teamId)) {
			if (Utils.emptyStr(c.getProjectId()))
				s = s + "  <p> " + "<input type=checkbox class=\"flat-red\" name=\"WBS\" value=\"" + c.getCategoryId() + "\"/> <b> " + c.getTreeCode() + " </b> " + c.getCaption() + "</p>"; 
			else
				s = s + "  <p> " + "<input type=checkbox class=\"flat-red\" name=\"WBS\" value=\"" + c.getCategoryId() + "\" checked=\"checked\"/> <b> " + c.getTreeCode() + " </b> " + c.getCaption() + "</p>"; 
		}
		return s;
	}
	
	public int nextTeamId(int projectId) {
		return r.nextTeamId(projectId);
	}

	public List<ExternalPerson> localPersonnel(ArrayList<String> w) {
		String[] f = null;
		String[] e = null;
		String[] c = null;
		
		if (w.size() > 0) {
			f = new String[w.size()];
			e = new String[w.size()];
			c = new String[w.size()];

			for (int i = 0; i < f.length; i++) {
				String[] v = w.get(i).split(",");
				f[i] = v[0];
				if (v[1].contains("*")) {
					e[i] = "LIKE";
					c[i] = v[1].replaceAll("*", "%");
				} else {
					e[i] = "=";
					c[i] = v[1];
				}
			}
		}
		return r.localPersonnel(f,e,c);
	}
	
	public ExternalPerson localPersonnel(int personId) {
		return r.externalPerson(personId);
	}

	public List<String> tableDomainList(String tableName) {
		return r.tableDomainsList(tableName);
	}

	public String[] tableDomains(String tableName) {
		return r.tableDomains(tableName);
	}

	public List<ViewWbsQuantity> aggrProjectWbsQuantity(int projectId) {
		return r.aggrProjectWbsQuantity(projectId);
	}

	public List<ViewWbsQuantity> allProjectWbsQuantity() {
		return r.allProjectWbsQuantity();
	}

	public ViewWbsQuantity singleProjectWbsQuantity(int projectId, int categoryId) {
		return r.singleProjectWbsQuantity(projectId, categoryId);
	}

	public List<ViewWbsTeamStatus> viewWbsTeamStatus(int projectId) {
		return r.viewWbsTeamStatus(projectId);
	}

	public boolean authorityChk(String username, String objectType, String action, String keyValue) {
		return r.authorityChk(username, objectType, action, keyValue);
	}

	public List<ViewWbsTeamStatus> viewWbsTeamStatus(int projectId, int teamId) {
		return r.viewWbsTeamStatus(projectId, teamId);
	}
	
	public int addProjectApproval(int projectId, String username, String action) {
		return r.addProjectApproval(projectId, username, action);
	}
	
	public List<String> authorityObjectTypes() {
		return r.authorityObjectTypes();
	}

	public List<String> authorityObjectActions(String authorityObject) {
		return r.authorityObjectActions(authorityObject);
	}

	public List<String> authorizedIds(String username, String authorityObject, String action) {
		return r.authorizedObjects(username, authorityObject, action);
	}
	
	public List<ReportProjectWbsStatus> getReportProjectWbsStatus(int projectId, Date begda, Date endda) {
		return r.getReportProjectWbsStatus(projectId, begda, endda);
	}
	
	public List<ReportProjectWbsStatus> getReportProjectWbsStatusForTeam(int projectId, int teamId, Date begda, Date endda) {
		return r.getReportProjectWbsStatusForTeam(projectId, teamId, begda, endda);
	}

	public List<ReportProjectWbsStatus> getReportProjectWbsStatusForSubcontractor(int projectId, int subcontractorId, Date begda, Date endda) {
		return r.getReportProjectWbsStatusForSubcontractor(projectId, subcontractorId, begda, endda);
	}

	public List<ViewProjectWbsMhApprove> getProjectWbsMhApprove(int projectId) {
		return r.getProjectWbsMhApprove(projectId);
	}

	public List<ViewProjectWbsQtyApprove> getProjectWbsQtyApprove(int projectId) {
		return r.getProjectWbsQtyApprove(projectId);
	}

	public List<String> getProjectTeamTemplate(int projectId, int teamId, Date activityDate) {
		return r.getProjectTeamTemplate(projectId, teamId, activityDate);
	}

}
