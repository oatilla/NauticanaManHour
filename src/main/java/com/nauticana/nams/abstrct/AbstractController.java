package com.nauticana.nams.abstrct;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.manhour.exception.RecordNotFound;
import com.nauticana.nams.service.NamsJdbcService;
import com.nauticana.nams.utils.DataCache;
import com.nauticana.nams.utils.Labels;
import com.nauticana.nams.utils.PortalLanguage;
import com.nauticana.nams.utils.Utils;

public abstract class AbstractController<ModelBean, ModelId extends Serializable> {

	
	@Autowired
	protected AbstractService<ModelBean, ModelId> modelService;

	@Autowired
	protected NamsJdbcService namsJdbcService;

	@Autowired
	protected DataCache dataCache;

	protected abstract String rootMapping();
	protected abstract String tableName();
	protected abstract String listView();
	protected abstract String editView();
	protected abstract String showView();
	protected abstract String selectView();
	protected abstract String prevPage(String id);
	protected abstract String[] domainNames();
	protected abstract String[] domainlists();
	protected abstract String[] lookuplists();
	protected abstract String[][] lookupService(int i);
	protected abstract String[] detailTables();
	protected abstract String[][] detailFields();
	protected abstract String[] actions();
	protected abstract String[][] detailActions();
	
	public static String[] initDomainList(String[] domainNames) {
		String[] s = new String[domainNames.length];
		for (int j = 0; j < s.length; j++) {
			s[j] = domainNames[j] + "_LIST";
		}
		return s;
	}
	
	public ModelAndView errorPage(PortalLanguage language, String ERRCODE, String msgText) {
		ModelAndView model = new ModelAndView("errorPage");
		model.addObject("ERRCODE", language.getText(ERRCODE));
		model.addObject("MSGTEXT", msgText);
		return model;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listGet(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName());
		
		// Read data and assign to model and view object
		List<ModelBean> records = modelService.findAll();
		ModelAndView model = new ModelAndView(listView());
		model.addObject(Labels.dataTableNames[0], Labels.dataTableSettings[0]);
		model.addObject("records", records);
		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			model.addObject(Labels.INSERT_ALLOWED, "X");
		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.UPDATE, tableName()))
			model.addObject(Labels.UPDATE_ALLOWED, "X");
		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.DELETE, tableName()))
			model.addObject(Labels.DELETE_ALLOWED, "X");
		
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(tableName()));
		model.addObject(Labels.NEW, language.getIconText(Labels.NEW));
		model.addObject(Labels.EDIT, language.getIconText(Labels.EDIT));
		model.addObject(Labels.DELETE, language.getIconText(Labels.DELETE));
		for (int i = 0; i < modelService.fieldNames().length; i++) {
			model.addObject(modelService.fieldNames()[i], language.getText(modelService.fieldNames()[i]));
		}
		
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newGet(HttpServletRequest request) {

		// Check for user and insert authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");
		
		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		// Create empty bean and assign to model and view object
		String parentKey = request.getParameter(Labels.PARENTKEY);
		String id = request.getParameter("id");
		//System.out.println("Parent Key is : " + parentKey);
		ModelBean record;
		if (!Utils.emptyStr(id))
			record = modelService.newEntityWithId(id);
		else {
			record = modelService.newEntity(parentKey);
			id = parentKey;
		}
		ModelAndView model = new ModelAndView(editView());
		model.addObject("record", record);

		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(tableName()));
		model.addObject(Labels.CHOOSE, language.getIconText(Labels.CHOOSE));
		model.addObject(Labels.SAVE, language.getIconText(Labels.SAVE));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.PREVPAGE, prevPage(id));
		model.addObject(Labels.POSTLINK, rootMapping()+"/new");
		for (int i = 0; i < modelService.fieldNames().length; i++) {
			model.addObject(modelService.fieldNames()[i], language.getText(modelService.fieldNames()[i]));
		}
		if (domainNames() != null)
			for (int i = 0; i < domainNames().length; i++) {
				model.addObject(domainlists()[i], getDomainValues(domainNames()[i], language));
			}
		if (lookuplists() != null)
			for (int i = 0; i < lookuplists().length; i++) {
				model.addObject(lookuplists()[i], getLookupValues(i));
			}	
		return model;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView newPost(@ModelAttribute ModelBean record, BindingResult result, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.INSERT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.INSERT + " ON " + tableName());

		if (result.hasErrors()) {
			String msgText = "";
			for (ObjectError e : result.getAllErrors()) {
				msgText = msgText + "\n" + e.toString();
			}
			return errorPage(language, Labels.ERR_BINDING, msgText);
		}
		modelService.save(record);
		String nextpage = request.getParameter(Labels.NEXTPAGE);
		if (Utils.emptyStr(nextpage))
			return new ModelAndView("redirect:list");
		else
			return new ModelAndView("redirect:/" + nextpage);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView editGet(HttpServletRequest request) {

		// Check for user and update authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.UPDATE, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.UPDATE + " ON " + tableName());

		// Read data record and assign to model and view object
		String id = request.getParameter("id");
		ModelBean record = modelService.findById(modelService.StrToId(id));
		if (record == null)
			return errorPage(language, Labels.ERR_RECORDNOTFOUND, tableName() + " WITH ID : " + id);
		ModelAndView model = new ModelAndView(editView());
		model.addObject("record", record);

		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(tableName()));
		model.addObject(Labels.CHOOSE, language.getIconText(Labels.CHOOSE));
		model.addObject(Labels.SAVE, language.getIconText(Labels.SAVE));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.PREVPAGE, prevPage(id));
		model.addObject(Labels.POSTLINK, rootMapping()+"/edit");
		for (int i = 0; i < modelService.fieldNames().length; i++) {
			model.addObject(modelService.fieldNames()[i], language.getText(modelService.fieldNames()[i]));
		}
		if (domainNames() != null)
			for (int i = 0; i < domainNames().length; i++) {
				model.addObject(domainlists()[i], getDomainValues(domainNames()[i], language));
			}
		if (lookuplists() != null)
			for (int i = 0; i < lookuplists().length; i++) {
				model.addObject(lookuplists()[i], getLookupValues(i));
			}	
		return model;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView editPost(@ModelAttribute ModelBean record, BindingResult result, HttpServletRequest request) {

		// Check for user and insert/update authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.UPDATE, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.UPDATE + " ON " + tableName());

		if (result.hasErrors()) {
			String msgText = "";
			for (ObjectError e : result.getAllErrors()) {
				msgText = msgText + "\n" + e.toString();
			}
			return errorPage(language, Labels.ERR_BINDING, msgText);
		}

		// Update record in database
		modelService.save(record);
		String nextpage = request.getParameter(Labels.NEXTPAGE);
		if (Utils.emptyStr(nextpage))
			return new ModelAndView("redirect:/" + rootMapping() + "/list");
		else
			return new ModelAndView("redirect:/" + nextpage);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteGet(HttpServletRequest request) {

		// Check for user and delete authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.DELETE, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.DELETE + " ON " + tableName());

		// Delete record from database
		String id = "";
		try {
			id = request.getParameter("id");
			modelService.remove(modelService.StrToId(id));
			String nextpage = request.getParameter(Labels.NEXTPAGE);
			if (Utils.emptyStr(nextpage))
				return new ModelAndView("redirect:/" + prevPage(id));
			else
				return new ModelAndView("redirect:/" + nextpage);
		} catch (RecordNotFound e) {
			return errorPage(language, Labels.ERR_RECORDNOTFOUND, tableName() + " WITH ID : " + id);
		}
	}


	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView showGet(HttpServletRequest request) throws IOException {
		
		// Check for user and read authorization on table
		HttpSession session = request.getSession(true);
		String username = (String) session.getAttribute(Labels.USERNAME);
		if (Utils.emptyStr(username)) return new ModelAndView("redirect:/userAccount/login");

		// Read language of session
		PortalLanguage language = dataCache.getLanguage((String) session.getAttribute(Labels.LANGUAGE));

		if (!namsJdbcService.authorityChk(username, Labels.TABLE, Labels.SELECT, tableName()))
			return errorPage(language, Labels.ERR_UNAUTHORIZED, Labels.TABLE + "-" + Labels.SELECT + " ON " + tableName());
		
		// Read data and assign to model and view object
		
		String id = request.getParameter("id");
		ModelBean record = modelService.findById(modelService.StrToId(id));
		if (record == null)
			return errorPage(language, Labels.ERR_RECORDNOTFOUND, tableName() + " WITH ID : " + id);
		ModelAndView model = new ModelAndView(showView());
		model.addObject("record", record);
		
		// Assign text objects from session language
		model.addObject(Labels.PAGETITLE, language.getText(tableName()));
		model.addObject(Labels.NEW, language.getIconText(Labels.NEW));
		model.addObject(Labels.EDIT, language.getIconText(Labels.EDIT));
		model.addObject(Labels.CHOOSE, language.getIconText(Labels.CHOOSE));
		model.addObject(Labels.DELETE, language.getIconText(Labels.DELETE));
		model.addObject(Labels.CANCEL, language.getIconText(Labels.CANCEL));
		model.addObject(Labels.PREVPAGE, prevPage(id));
		model.addObject(tableName(), language.getText(tableName()));
		for (int i = 0; i < modelService.fieldNames().length; i++) {
			model.addObject(modelService.fieldNames()[i], language.getText(modelService.fieldNames()[i]));
		}
		if (detailTables() != null)
		for (int i = 0; i < detailTables().length; i++) {
			model.addObject(Labels.dataTableNames[i], Labels.dataTableSettings[i]);
			model.addObject(detailTables()[i], language.getText(detailTables()[i]));
			for (int j = 0; j < detailFields()[i].length; j++) {
				model.addObject(detailFields()[i][j], language.getText(detailFields()[i][j]));
			}
		}
		if (actions() != null)
			for (int i = 0; i < actions().length; i++) {
				if (namsJdbcService.authorityChk(username, tableName(), actions()[i], id))
					model.addObject(actions()[i], language.getIconText(actions()[i]));
			}
		if (detailActions() != null)
			for (int i = 0; i < detailActions().length; i++) {
				if (detailActions()[i] != null)
				for (int j = 0; j < detailActions()[i].length; j++) {
					if (namsJdbcService.authorityChk(username, detailTables()[i], detailActions()[i][j], id))
						model.addObject(detailActions()[i][j], language.getIconText(detailActions()[i][j]));
				}
			}
		return model;
	}
	
	public Map<String, String> getDomainValues(String domainName, PortalLanguage language) {
		return dataCache.getDomainOptions(domainName, language);
	}

	public Map<String, String> getLookupValues(int i) {
		String [][] s = lookupService(i);
		Map<String,String> items = new LinkedHashMap<String, String>();
		for (int j = 0; j < s.length; j++) {
			items.put(s[j][0], s[j][1]);
		}
		return items;
	}
	
	public List<ModelBean> findAuthorized(String username, String authorityObject, String action) {
		List<String> ids = namsJdbcService.authorizedIds(username, authorityObject, action);
		List<ModelBean> entities = new ArrayList<ModelBean>();
		for (String s : ids) {
			if (s.equals("*"))
				return modelService.findAll();
			ModelId id = modelService.StrToId(s);
			ModelBean entity = modelService.findById(id);
			entities.add(entity);
		}
		return entities;
	}


}
