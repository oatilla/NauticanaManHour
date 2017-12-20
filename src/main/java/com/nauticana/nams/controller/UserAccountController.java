package com.nauticana.nams.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
import com.nauticana.nams.utils.Utils;

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
	public ModelAndView loginGet(HttpServletRequest request) throws IOException{
		ModelAndView model = new ModelAndView("login");
		LoginBean loginBean = new LoginBean();
		PortalLanguage language = dataCache.getLanguage(request.getLocale().getCountry().toUpperCase());
		if (language == null) {
			loginBean.setLanguage("EN");
			language = dataCache.getLanguage("EN");
		} else
			loginBean.setLanguage(language.code);
        model.addObject("loginBean", loginBean);
		model.addObject(lookuplists[0], getLookupValues(0));
		model.addObject(Labels.APPLICATION_TITLE, language.getText(Labels.APPLICATION_TITLE));
		return model;
	}

	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView loginPost(HttpServletRequest request, @ModelAttribute("loginBean")LoginBean loginBean) {
		String uname = loginBean.getUsername();
		UserAccount userAccount = modelService.findById(uname);
		boolean ok = true;
		String message = "";

		if (userAccount == null) {
//			message = "User not found!";
			message = "Invalid credentials!";
			ok = false;
		} else {
			ok = userAccount.checkPassword(loginBean.getPassword());
			if (!ok)
				message = "Invalid credentials!";
			else {
				switch (userAccount.getStatus()) {
				case 'A':
					break;
				case 'I':
					break;
				case 'E':
					break;
				case 'L':
					ok = false;
					message = "User is locked!";
					break;
				case 'S':
					ok = false;
					message = "User is locked by system administrator!";
					break;

				default:
					break;
				}
			}
		}
		
		if (ok) {
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
			if (userAccount.getStatus() == 'I' || userAccount.getStatus() == 'E')
				return new ModelAndView("redirect:setPassword?mode=I");
			return new ModelAndView("redirect:/");
		} else {
			ModelAndView model = new ModelAndView("login");
			model.addObject("loginBean", loginBean);
			model.addObject(lookuplists[0], getLookupValues(0));
			model.addObject("message", message);
			System.out.println("User Login Error : " + message);
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

	@Override
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView editPost(@ModelAttribute UserAccount record, BindingResult result, HttpServletRequest request) {
		if (Utils.emptyStr(record.getPassword())) {
			UserAccount original = modelService.findById(record.getId());
			record.setPassword(original.getPassword());
		}
		return super.editPost(record, result, request);
	}

	@RequestMapping(value = "/setPassword", method = RequestMethod.GET)
	public ModelAndView setPasswordGet(HttpServletRequest request) {
		// Check for user and insert authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		
		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		String prevpage = "/";
		String mode = (String) request.getParameter("mode");
		if ("I".equals(mode))
			prevpage = "login";
			
		ModelAndView model = new ModelAndView("setPassword");
		model.addObject("userAccountName", username);
		model.addObject(Labels.PAGETITLE, language.getText(Labels.CHANGE_PASSWORD));
		model.addObject(Labels.SAVE, language.getIconText(Labels.SAVE));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.POSTLINK, rootMapping()+"/setPassword");
		model.addObject(Labels.PREVPAGE, prevpage);
		model.addObject(Labels.APPLICATION_TITLE, language.getText(Labels.APPLICATION_TITLE));
		for (int i = 0; i < modelService.fieldNames().length; i++) {
			model.addObject(modelService.fieldNames()[i], language.getText(modelService.fieldNames()[i]));
		}
		return model;
	}

	@RequestMapping(value = "/setPassword", method = RequestMethod.POST)
	public ModelAndView setPasswordPost(HttpServletRequest request) {
		// Check for user and insert authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/");
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		
		String userAccountName = request.getParameter("userAccountName");
		String passText        = request.getParameter("passText");
		
		if (Utils.emptyStr(passText) || !username.equals(userAccountName)) return new ModelAndView("redirect:/");

		UserAccount entity = modelService.findById(userAccountName);
		if (entity == null) return new ModelAndView("redirect:/");
		entity.setPassword(passText);
		entity.setStatus('A');
		try {
			modelService.save(entity);
		} catch(Exception e) {
			return errorPage(language, Labels.ERR_DATABASE_ERROR, e.getMessage());
		}
		return new ModelAndView("redirect:/");
	}
}
