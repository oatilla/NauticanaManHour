package com.nauticana.manhour.controlller;

import java.io.IOException;
import java.util.HashMap;
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
import com.nauticana.manhour.service.CaptionTranslationService;
import com.nauticana.manhour.service.DomainNameService;
import com.nauticana.manhour.service.DomainValueService;
import com.nauticana.manhour.service.LanguageService;
import com.nauticana.manhour.utils.DataCache;
import com.nauticana.manhour.utils.Labels;
import com.nauticana.manhour.utils.PortalLanguage;
import com.nauticana.manhour.utils.Utils;

@Controller
@RequestMapping("/user")
public class UserAccountController extends AbstractController<UserAccount, String> {

	@Autowired
	private LanguageService languageService;
	
	@Autowired
	private CaptionTranslationService captionTranslationService;
	
	@Autowired
	private DomainNameService domainNameService;
	
	@Autowired
	private DomainValueService domainValueService;
	
	public UserAccountController() {
		super("USER_ACCOUNTS", "userAccountList", "userAccountEdit");
		//this.modelService = userAccountService;
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
		
		UserAccount userAccount = modelService.findStrId(uname);
		if (userAccount.checkPassword(loginBean.getPassword())) {
			HttpSession session = request.getSession(true);
			session.setAttribute(Labels.USERNAME, loginBean.getUsername());
			session.setAttribute(Labels.LANGUAGE, loginBean.getLanguage());
			System.out.println("User Login Successful");
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
	public ModelAndView add(HttpServletRequest request) {
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

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/login");
		PortalLanguage language = DataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		if (!DataCache.updateAllowed(tableName, username))
			return new ModelAndView("redirect:/unauthorized");
		String id = request.getParameter("id");
		UserAccount record = modelService.findStrId(id);
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
