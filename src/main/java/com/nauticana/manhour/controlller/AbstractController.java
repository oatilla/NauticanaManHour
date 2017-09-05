package com.nauticana.manhour.controlller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.manhour.exception.RecordNotFound;
import com.nauticana.manhour.service.AbstractService;
import com.nauticana.manhour.utils.DataCache;
import com.nauticana.manhour.utils.Labels;
import com.nauticana.manhour.utils.PortalLanguage;
import com.nauticana.manhour.utils.Utils;

public abstract class AbstractController<B,K extends Serializable> {

	protected String tableName;
	protected String listView;
	protected String editView;
	
	@Autowired
	protected AbstractService<B,K> modelService;
	
	public AbstractController(String tableName, String listView, String editView) {
		this.tableName = tableName;
		this.listView = listView;
		this.editView = editView;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/user/login");
		if (!DataCache.selectAllowed(tableName, username))
			return new ModelAndView("redirect:/unauthorized");
		
		// Read data and assign to model and view object
		List<B> records = modelService.findAll();
		ModelAndView model = new ModelAndView(listView);
		model.addObject("records", records);
		
		// Read language of session
		PortalLanguage language = DataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(tableName));
		model.addObject(Labels.NEW, language.getText(Labels.NEW));
		model.addObject(Labels.EDIT, language.getText(Labels.EDIT));
		model.addObject(Labels.DELETE, language.getText(Labels.DELETE));
		for (int i = 0; i < modelService.getFieldNames().length; i++) {
			model.addObject(modelService.getFieldNames()[i], language.getText(modelService.getFieldNames()[i]));
		}
		
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request) {

		// Check for user and insert authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/user/login");
		if (!DataCache.insertAllowed(tableName, username))
			return new ModelAndView("redirect:/unauthorized");

		// Create empty bean and assign to model and view object
		B record = modelService.newEntity();
		ModelAndView model = new ModelAndView(editView);
		model.addObject("record", record);
		
		// Read language of session
		PortalLanguage language = DataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(tableName));
		model.addObject(Labels.SAVE, language.getText(Labels.SAVE));
		for (int i = 0; i < modelService.getFieldNames().length; i++) {
			model.addObject(modelService.getFieldNames()[i], language.getText(modelService.getFieldNames()[i]));
		}
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView saveNew(@ModelAttribute B record, BindingResult result, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/user/login");
		if (!DataCache.insertAllowed(tableName, username))
			return new ModelAndView("redirect:/unauthorized");
		if (result.hasErrors()) return new ModelAndView("redirect:/bindingError");
		modelService.save(record);
		return new ModelAndView("redirect:list");
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(HttpServletRequest request) {

		// Check for user and update authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/user/login");
		if (!DataCache.updateAllowed(tableName, username))
			return new ModelAndView("redirect:/unauthorized");

		// Read data record and assign to model and view object
		String id = request.getParameter("id");
		
		B record = modelService.findStrId(id);
		ModelAndView model = new ModelAndView(editView);
		model.addObject("record", record);

		// Read language of session
		PortalLanguage language = DataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(tableName));
		model.addObject(Labels.SAVE, language.getText(Labels.SAVE));
		for (int i = 0; i < modelService.getFieldNames().length; i++) {
			model.addObject(modelService.getFieldNames()[i], language.getText(modelService.getFieldNames()[i]));
		}
		return model;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute B record, BindingResult result, HttpServletRequest request) {

		// Check for user and insert/update authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/user/login");
		if (!DataCache.updateAllowed(tableName, username))
			return new ModelAndView("redirect:/unauthorized");
		if (result.hasErrors()) return new ModelAndView("redirect:/bindingError");

		// Update record in database
		modelService.save(record);
		return new ModelAndView("redirect:list");
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request) {

		// Check for user and delete authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/user/login");
		if (!DataCache.deleteAllowed(tableName, username)) return new ModelAndView("redirect:/unauthorized");

		// Delete record from database
		String id = request.getParameter("id");
		try {
			modelService.removeStrId(id);
			return new ModelAndView("redirect:list");
		} catch (RecordNotFound e) {
			return new ModelAndView("redirect:/recordnotfound");
		}
	}
	
    
}
