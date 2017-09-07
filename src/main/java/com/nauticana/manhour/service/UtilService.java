package com.nauticana.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return r.projectCategoory(projectId);
	}

}
