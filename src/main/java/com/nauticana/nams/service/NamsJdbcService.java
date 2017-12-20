package com.nauticana.nams.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public List<String> tableDomainList(String tableName) {
		return r.tableDomainsList(tableName);
	}

	public String[] tableDomains(String tableName) {
		return r.tableDomains(tableName);
	}

	public boolean authorityChk(String username, String objectType, String action, String keyValue) {
		return r.authorityChk(username, objectType, action, keyValue);
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
	
}
