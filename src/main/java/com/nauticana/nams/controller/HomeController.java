package com.nauticana.nams.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.nams.model.Language;
import com.nauticana.nams.service.NamsJdbcService;
import com.nauticana.nams.service.LanguageService;
import com.nauticana.nams.utils.DataCache;
import com.nauticana.nams.utils.Icons;
import com.nauticana.nams.utils.Labels;
import com.nauticana.nams.utils.PortalLanguage;
import com.nauticana.nams.utils.Utils;

@Controller
public class HomeController {

	@Autowired
	private NamsJdbcService utilService;

	@Autowired
	private LanguageService languageService;

	@Autowired
	private DataCache dataCache;

	@RequestMapping(value="/")
	public ModelAndView home(HttpServletRequest request) throws IOException{
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		String langcode = request.getParameter("langcode");
		if (!Utils.emptyStr(langcode)) {
			session.setAttribute(Labels.LANGUAGE, langcode);
			language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
			if ("RIGHT".equals(languageService.findById(langcode).getDirection()))
				session.setAttribute(Labels.LANGUAGE_DIRECTION, " dir=\"RTL\"");
			else
				session.setAttribute(Labels.LANGUAGE_DIRECTION, "");
			String menu = utilService.userMenu(username, language);
			System.out.println(menu);
			session.setAttribute(Labels.MENU, menu);
		}
		ModelAndView model = new ModelAndView("home");
		model.addObject("PAGETITLE", language.getText(Labels.APPLICATION_TITLE));
		model.addObject(Labels.LANGUAGE_DIRECTION, session.getAttribute(Labels.LANGUAGE_DIRECTION));
		model.addObject("userCaption", username);
		model.addObject(Icons.MENU, Icons.getIcon(Icons.MENU));
		model.addObject(Icons.LOGIN, Icons.getIcon(Icons.LOGIN));
		model.addObject(Icons.LOGOFF, Icons.getIcon(Icons.LOGOFF));
		model.addObject("menu", (String) session.getAttribute(Labels.MENU));
		model.addObject("activeLang", session.getAttribute(Labels.LANGUAGE));
		Language l = languageService.findById((String) session.getAttribute(Labels.LANGUAGE));
		if (l != null)
			model.addObject("langClass", "flag-icon " + l.getFlag());
		model.addObject("languageList", getLanguages());
		return model;
	}
	
	public Map<String, String> getLanguages() {
		String [][] s = languageService.findAllFlag();
		Map<String,String> items = new LinkedHashMap<String, String>();
		for (int j = 0; j < s.length; j++) {
			items.put(s[j][0], s[j][1]);
		}
		return items;
	}
	
}
