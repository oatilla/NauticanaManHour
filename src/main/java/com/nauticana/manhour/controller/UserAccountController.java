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

//	@Autowired
//	protected UserAccountService modelService;

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
			String menu = " <ul class=\"sidebar-menu\">" + System.lineSeparator();
			String last = "";
			
//			 <li class="treeview">
//	          <a href="#">
//	            <i class="fa fa-files-o"></i>
//	            <span>Layout Options</span>
//	            <span class="pull-right-container">
//	              <span class="label label-primary pull-right">4</span>
//	            </span>
//	          </a>
//	          <ul class="treeview-menu">
//	            <li><a href="pages/layout/top-nav.html"><i class="fa fa-circle-o"></i> Top Navigation</a></li>
//	            <li><a href="pages/layout/boxed.html"><i class="fa fa-circle-o"></i> Boxed</a></li>
//	            <li><a href="pages/layout/fixed.html"><i class="fa fa-circle-o"></i> Fixed</a></li>
//	            <li><a href="pages/layout/collapsed-sidebar.html"><i class="fa fa-circle-o"></i> Collapsed Sidebar</a></li>
//	          </ul>
//	        </li>
						
			for (UserMenu page : pages) {
				if (!page.getMenuCaption().equals(last)) {
					if (!Utils.emptyStr(last))	menu = menu+"   </ul>" + System.lineSeparator() + "  </li>";
					last = page.getMenuCaption();
					menu=menu+"  <li class=\"treeview\"><div> <i  class=\"fa fa-circle-o\">" + language.getText(last) + "</div>" + System.lineSeparator() + "   <ul>" + System.lineSeparator();
				}				
				menu=menu+"    <li> <a href=\"" + page.getUrl() + "\" target=frmupage> <i  class=\"fa fa-circle-o\"> " + language.getText(page.getPageCaption()) + " </a> </li>" + System.lineSeparator();
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
	
	@Override
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newGet(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/login");
		PortalLanguage language = DataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		if (!DataCache.insertAllowed(tableName, username))
			return new ModelAndView("redirect:/unauthorized");
		UserAccount record = new UserAccount();
		ModelAndView model = new ModelAndView(editView);
		model.addObject("record", record);
		model.addObject("statusList", getStatusList());
		model.addObject(Labels.PAGETITLE, language.getText(tableName));
		model.addObject(Labels.SAVE, language.getText(Labels.SAVE));
		for (int i = 0; i < UserAccount.fieldNames.length; i++) {
			model.addObject(UserAccount.fieldNames[i], language.getText(UserAccount.fieldNames[i]));
		}
		return model;
	}

	@Override
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editGet(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/login");
		PortalLanguage language = DataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		if (!DataCache.updateAllowed(tableName, username))
			return new ModelAndView("redirect:/unauthorized");
		String id = request.getParameter("id");
		UserAccount record = modelService.findById(id);
		ModelAndView model = new ModelAndView(editView);
		model.addObject("record", record);
		model.addObject("statusList", getStatusList());
		model.addObject("edit", true);
		model.addObject(Labels.PAGETITLE, language.getText(tableName));
		model.addObject(Labels.SAVE, language.getText(Labels.SAVE));
		for (int i = 0; i < UserAccount.fieldNames.length; i++) {
			model.addObject(UserAccount.fieldNames[i], language.getText(UserAccount.fieldNames[i]));
		}
		return model;
	}

	@ModelAttribute("status")
	public Map<String, String> getStatusList() {
		return DataCache.getDomainOptions("USER_STATUS");
	}
}
