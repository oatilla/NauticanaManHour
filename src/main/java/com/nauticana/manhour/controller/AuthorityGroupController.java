package com.nauticana.manhour.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.manhour.model.AuthorityGroup;
import com.nauticana.manhour.model.PageAuthorization;
import com.nauticana.manhour.model.TableAuthorization;
import com.nauticana.manhour.model.UserAuthorization;
import com.nauticana.manhour.utils.DataCache;
import com.nauticana.manhour.utils.Icons;
import com.nauticana.manhour.utils.Labels;
import com.nauticana.manhour.utils.PortalLanguage;
import com.nauticana.manhour.utils.Utils;


@Controller
@RequestMapping("/authorityGroup")
public class AuthorityGroupController extends AbstractController<AuthorityGroup, String> {

//	@Autowired
//	protected AuthorityGroupService modelService;
	
	public AuthorityGroupController() {
		super(AuthorityGroup.tableName, "authorityGroupList", "authorityGroupEdit");
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
		AuthorityGroup authorityGroup = modelService.findById(request.getParameter("id"));
		
		ModelAndView model = new ModelAndView("authorityGroupShow");
		model.addObject("record", authorityGroup);
		
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(tableName));
		model.addObject(Labels.NEW, language.getText(Labels.NEW));
		model.addObject(Labels.EDIT, language.getText(Labels.EDIT));
		model.addObject(Labels.CHOOSE, language.getText(Labels.CHOOSE));
		model.addObject(Labels.DELETE, language.getText(Labels.DELETE));
		model.addObject(Icons.EDIT, Icons.getIcon(Icons.EDIT));
		model.addObject(Icons.NEW, Icons.getIcon(Icons.NEW));
		model.addObject(Icons.DELETE, Icons.getIcon(Icons.DELETE));
		model.addObject("DATATABLE1", Labels.dataTableSetting1);
		model.addObject("DATATABLE2", Labels.dataTableSetting2);
		model.addObject("DATATABLE3", Labels.dataTableSetting3);
		model.addObject(tableName, language.getText(tableName));
		for (int i = 0; i < modelService.getFieldNames().length; i++) {
			model.addObject(modelService.getFieldNames()[i], language.getText(modelService.getFieldNames()[i]));
		}
		model.addObject(TableAuthorization.tableName, language.getText(TableAuthorization.tableName));
		for (int i = 0; i < TableAuthorization.fieldNames.length; i++) {
			model.addObject(TableAuthorization.fieldNames[i], language.getText(TableAuthorization.fieldNames[i]));
		}
		model.addObject(UserAuthorization.tableName, language.getText(UserAuthorization.tableName));
		for (int i = 0; i < UserAuthorization.fieldNames.length; i++) {
			model.addObject(UserAuthorization.fieldNames[i], language.getText(UserAuthorization.fieldNames[i]));
		}
		model.addObject(PageAuthorization.tableName, language.getText(PageAuthorization.tableName));
		for (int i = 0; i < PageAuthorization.fieldNames.length; i++) {
			model.addObject(PageAuthorization.fieldNames[i], language.getText(PageAuthorization.fieldNames[i]));
		}

		return model;
	}
}
