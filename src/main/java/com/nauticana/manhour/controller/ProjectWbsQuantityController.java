package com.nauticana.manhour.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.manhour.model.ProjectWbsQuantity;
import com.nauticana.manhour.model.ProjectWbsQuantityId;
import com.nauticana.manhour.query.AggrWbsQuantity;
import com.nauticana.manhour.service.UtilService;
import com.nauticana.manhour.utils.DataCache;
import com.nauticana.manhour.utils.Icons;
import com.nauticana.manhour.utils.Labels;
import com.nauticana.manhour.utils.PortalLanguage;
import com.nauticana.manhour.utils.Utils;

@Controller
@RequestMapping("/projectWbsQuantity")
public class ProjectWbsQuantityController extends AbstractController<ProjectWbsQuantity, ProjectWbsQuantityId> {

//	@Autowired
//	protected ProjectWbsQuantityService modelService;

	@Autowired
	protected UtilService us;

	public ProjectWbsQuantityController() {
		super(ProjectWbsQuantity.tableName, "projectWbsQuantityList", "projectWbsQuantityEdit");
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public ModelAndView select(HttpServletRequest request) throws IOException, ParseException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");
		if (!utilService.allowSelect(username, tableName))
			return new ModelAndView("redirect:/unauthorized");
		
		// Read language of session
		PortalLanguage language = DataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));
		
		List<AggrWbsQuantity> pwq = us.allProjectWbsQuantity();
		ModelAndView model = new ModelAndView("projectWbsQuantitySelect");
		model.addObject("records", pwq);
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(ProjectWbsQuantity.tableName));
		model.addObject(Labels.NEW, language.getText(Labels.NEW));
		model.addObject(Icons.NEW, Icons.getIcon(Icons.NEW));
		model.addObject(Labels.OK, language.getText(Labels.OK));
		model.addObject(Icons.OK, Icons.getIcon(Icons.OK));
		model.addObject(Labels.CANCEL, language.getText(Labels.CANCEL));
		model.addObject(Icons.CANCEL, Icons.getIcon(Icons.SELECT));
		model.addObject(Icons.PROJECT_QUANTITY, Icons.getIcon(Icons.PROJECT_QUANTITY));
		model.addObject("DATATABLE1", Labels.dataTableSetting1);
		model.addObject(tableName, language.getText(tableName));
		for (int i = 0; i < modelService.getFieldNames().length; i++) {
			model.addObject(modelService.getFieldNames()[i], language.getText(modelService.getFieldNames()[i]));
		}
		for (int i = 0; i < AggrWbsQuantity.fieldNames.length; i++) {
			model.addObject(AggrWbsQuantity.fieldNames[i], language.getText(AggrWbsQuantity.fieldNames[i]));
		}
		return model;
	}

}
