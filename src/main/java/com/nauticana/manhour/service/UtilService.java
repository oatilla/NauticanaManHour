package com.nauticana.manhour.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.query.AggrWbsQuantity;
import com.nauticana.manhour.query.LocalPerson;
import com.nauticana.manhour.query.ProjectCategory;
import com.nauticana.manhour.query.UserMenu;
import com.nauticana.manhour.repository.JbdcRepository;

@Service
public class UtilService {

	@Autowired
	private JbdcRepository r;
	
	public boolean allowSelect(String id, String tablename) {
		return r.allowSelect(tablename, id);
	}

	public boolean allowInsert(String id, String tablename) {
		return r.allowInsert(tablename, id);
	}

	public boolean allowUpdate(String id, String tablename) {
		return r.allowUpdate(tablename, id);
	}

	public boolean allowDelete(String id, String tablename) {
		return r.allowDelete(tablename, id);
	}

	public List<UserMenu> userMenu(String username) {
		return r.userMenu(username);
	}

	public List<ProjectCategory> findAllCategories(int projectId) {
		return r.projectCategory(projectId);
	}
	
	public List<ProjectCategory> findAllCategories(int projectId, int teamId) {
		return r.teamCategory(projectId, teamId);
	}
	
	public int nextTeamId(int projectId) {
		return r.nextTeamId(projectId);
	}

	public List<LocalPerson> localPersonnel(ArrayList<String> w) {
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
		return r.localPerson(f,e,c);
	}
	
	public LocalPerson localPersonnel(int personId) {
		return r.localPerson(personId);
	}

	public List<String> tableDomains(String tableName) {
		return r.tableDomains(tableName);
	}

	public List<AggrWbsQuantity> aggrProjectWbsQuantity(int projectId) {
		return r.aggrProjectWbsQuantity(projectId);
	}

	public List<AggrWbsQuantity> allProjectWbsQuantity() {
		return r.allProjectWbsQuantity();
	}

	public AggrWbsQuantity singleProjectWbsQuantity(int projectId, int categoryId) {
		return r.singleProjectWbsQuantity(projectId, categoryId);
	}

}
