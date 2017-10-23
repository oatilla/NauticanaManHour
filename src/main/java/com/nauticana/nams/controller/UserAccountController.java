package com.nauticana.nams.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.nams.abstrct.AbstractController;
import com.nauticana.nams.model.LoginBean;
import com.nauticana.nams.model.UserAccount;
import com.nauticana.nams.model.UserAuthorization;
import com.nauticana.nams.service.LanguageService;
import com.nauticana.nams.utils.Labels;
import com.nauticana.nams.utils.PortalLanguage;

@Controller
@RequestMapping("/" + UserAccount.rootMapping)
public class UserAccountController extends AbstractController<UserAccount, String> {

	@Autowired
	private LanguageService languageService;

	public static final String[] lookuplists = new String[] {"languageList"};
	public static final String[] detailTables = new String[] {UserAuthorization.tableName};
	public static final String[][] detailFields = new String[][] {UserAuthorization.fieldNames};
	public static final String listView   = UserAccount.rootMapping + "List";
	public static final String editView   = UserAccount.rootMapping + "Edit";
	public static final String showView   = UserAccount.rootMapping + "Show";
	public static final String selectView = UserAccount.rootMapping + "Select";

	private static String[] domainNames = null;
	private static String[] domainlists = null;
	
	@Override
	protected String rootMapping() {return UserAccount.rootMapping;}

	@Override
	protected String tableName() {return UserAccount.tableName;}

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
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(UserAccount.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

	@Override
	protected String[] domainlists() {return domainlists;}

	@Override
	protected String[] lookuplists() {return lookuplists;}

	@Override
	protected String[][] lookupService(int i) {
		return languageService.findAllStr();
	}

	@Override
	protected String[] actions() {return null;}

	@Override
	protected String[][] detailActions() {return null;}

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request) throws IOException{
		ModelAndView model = new ModelAndView("login");
		LoginBean loginBean = new LoginBean();
        model.addObject("loginBean", loginBean);
		model.addObject(lookuplists[0], getLookupValues(0));
		return model;
	}

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, @ModelAttribute("loginBean")LoginBean loginBean) {
		String uname = loginBean.getUsername();
//		System.out.println("Got username " + uname);
		UserAccount userAccount = modelService.findById(uname);
		if (userAccount.checkPassword(loginBean.getPassword())) {
			// Set session variables
			HttpSession session = request.getSession(true);
			session.setAttribute(Labels.USERNAME, loginBean.getUsername());
			session.setAttribute(Labels.LANGUAGE, loginBean.getLanguage());
			PortalLanguage language = dataCache.getLanguage(loginBean.getLanguage());
			if ("RIGHT".equals(languageService.findById(loginBean.getLanguage()).getDirection()))
				session.setAttribute(Labels.LANGUAGE_DIRECTION, " dir=\"RTL\"");
			else
				session.setAttribute(Labels.LANGUAGE_DIRECTION, "");
			// Prepare left menu
			String menu = namsJdbcService.userMenu(uname, language);
			System.out.println(menu);
			session.setAttribute(Labels.MENU, menu);
			System.out.println("User Login Successful : " + uname);
			return new ModelAndView("redirect:/");
		} else {
			ModelAndView model = new ModelAndView("login");
			model.addObject("loginBean", loginBean);
			model.addObject("languageList");
			model.addObject("message", "Invalid credentials!!");
			System.out.println("User Login Error : ");//+loginBean.getUsername()+"/"+loginBean.getPassword()+ " <> " + userAccount.getId()+"/"+userAccount.getPassword());
	        return model;
	    }
	}

	@RequestMapping(value="/logoff",method=RequestMethod.GET)
	public ModelAndView logoff(HttpServletRequest request) throws IOException{
		// Set session variables
		HttpSession session = request.getSession(true);
		session.removeAttribute(Labels.USERNAME);
		session.removeAttribute(Labels.LANGUAGE);
		session.removeAttribute(Labels.MENU);
		return new ModelAndView("redirect:/");
	}
}
