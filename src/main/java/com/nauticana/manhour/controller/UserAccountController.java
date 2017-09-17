package com.nauticana.manhour.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.manhour.model.LoginBean;
import com.nauticana.manhour.model.UserAccount;
import com.nauticana.manhour.model.UserAuthorization;
import com.nauticana.manhour.query.UserMenu;
import com.nauticana.manhour.service.CaptionTranslationService;
import com.nauticana.manhour.service.DomainNameService;
import com.nauticana.manhour.service.DomainValueService;
import com.nauticana.manhour.service.LanguageService;
import com.nauticana.manhour.service.UtilService;
import com.nauticana.manhour.utils.DataCache;
import com.nauticana.manhour.utils.Labels;
import com.nauticana.manhour.utils.PortalLanguage;
import com.nauticana.manhour.utils.Utils;

@Controller
@RequestMapping("/userAccount")
public class UserAccountController extends AbstractController<UserAccount, String> {

	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private CaptionTranslationService captionTranslationService;
	
	@Autowired
	private DomainNameService domainNameService;
	
	@Autowired
	private DomainValueService domainValueService;
	
	@Autowired
	private UtilService utilService;

	public UserAccountController() {
		super(UserAccount.tableName, "userAccountList", "userAccountEdit");
	}
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request) throws IOException{
		ModelAndView model = new ModelAndView("login");
		LoginBean loginBean = new LoginBean();
        model.addObject("loginBean", loginBean);
		model.addObject("languageList");
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
			PortalLanguage language = DataCache.getLanguage(loginBean.getLanguage());
//			System.out.println("Got PortalLanguage " + language.caption);
//			System.out.println("Reading left menu query");
			// Prepare left menu
			List<UserMenu> pages = utilService.userMenu(uname);
			System.out.println("Generating HTML for left menu");
//			String menu = " <ul>" + System.lineSeparator();
			String menu = " <ul class=\"sidebar-menu\">" + System.lineSeparator();
			String last = "";
// Menu HTML kodu tablosundan alınan verilerle oluşturuluyor.			
			for (UserMenu page : pages) {
//				if (!page.getMenuCaption().equals(last)) {
//					if (!Utils.emptyStr(last))	menu = menu+"   </ul>" + System.lineSeparator() + "  </li>";
//					last = page.getMenuCaption();
//					menu=menu+"  <li><div> <i class=\"fa " + page.getMenuIcon() + "\"></i> " + language.getText(last) + " </div>" + System.lineSeparator() + "   <ul>" + System.lineSeparator();
//				}
//				menu=menu+"    <li> <a href=\"#\" onClick=\"doAjaxGet('" + page.getUrl() + "');\"> <i class=\"fa " + page.getPageIcon() + "\"></i> " + language.getText(page.getPageCaption()) + " </a> </li>" + System.lineSeparator();

				// Atillanin adminlte icin hazirladigi menu
				
				if (!page.getMenuCaption().equals(last)) {
					if (!Utils.emptyStr(last))	menu = menu+"   </ul>" + System.lineSeparator() + "  </li>";
					last = page.getMenuCaption();
					menu=menu+"  <li class=\"treeview active\"> "
							+ "<a href=\"#\"><i class=\"fa " + page.getMenuIcon() + "\"></i> <span>" + language.getText(last) + "</span>\r\n" + 
							"            <span class=\"pull-right-container\">\r\n" + 
			//				"              <i class=\"fa fa-angle-left pull-right\"></i>\r\n" + 
							"            </span>\r\n" + 
							"          </a> " + System.lineSeparator() + "   <ul class=\"treeview-menu menu-open\" style=\"display: block;\">" + System.lineSeparator();
				}
				menu=menu+"    <li> <a href=\"#\" onClick=\"doAjaxGet('" + page.getUrl() + "');\"> <i class=\"fa " + page.getPageIcon() + "\"></i> " + language.getText(page.getPageCaption()) + " </a> </li>" + System.lineSeparator();
		
			}
			if (!Utils.emptyStr(last))	menu = menu+"   </ul>" + System.lineSeparator() + "  </li>";
			menu = menu+" </ul>" + System.lineSeparator();
			System.out.println(menu);
//			System.out.println("Setting menu attribute");
			session.setAttribute(Labels.MENU, menu);
//			System.out.println("redirect:/");
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
	
	@ModelAttribute("languageList")
	public Map<String, String> getLanguageList() {
		Map<String, PortalLanguage> m = DataCache.getLanguages();
		if (m == null) {
			try {
				m = DataCache.loadLanguages(languageService, captionTranslationService);
			} catch (IOException e) {
				e.printStackTrace();
			}
			DataCache.loadDomains(domainNameService, domainValueService);
		}
		Map<String, String> languageList = new HashMap<String, String>();
		for(String key: m.keySet()) {
			String value  = m.get(key).caption;
			languageList.put(key, value);
		}
		return languageList;
	}
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView showGet(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");
		if (!utilService.allowSelect(username, tableName))
			return new ModelAndView("redirect:/unauthorized");
		
		// Read language of session
		PortalLanguage language = DataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		// Read data and assign to model and view object
		UserAccount userAccount = modelService.findById(modelService.StrToId(request.getParameter("id")));
		
		ModelAndView model = new ModelAndView("userAccountShow");
		model.addObject("record", userAccount);
		
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(tableName));
		model.addObject(Labels.NEW, language.getText(Labels.NEW));
		model.addObject(Labels.EDIT, language.getText(Labels.EDIT));
		model.addObject(Labels.CHOOSE, language.getText(Labels.CHOOSE));
		model.addObject(Labels.DELETE, language.getText(Labels.DELETE));
		model.addObject(tableName, language.getText(tableName));
		for (int i = 0; i < modelService.getFieldNames().length; i++) {
			model.addObject(modelService.getFieldNames()[i], language.getText(modelService.getFieldNames()[i]));
		}
		model.addObject(UserAuthorization.tableName, language.getText(UserAuthorization.tableName));
		for (int i = 0; i < UserAuthorization.fieldNames.length; i++) {
			model.addObject(UserAuthorization.fieldNames[i], language.getText(UserAuthorization.fieldNames[i]));
		}
		return model;
	}
}
