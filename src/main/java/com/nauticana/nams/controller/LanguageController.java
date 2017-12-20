package com.nauticana.nams.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.nams.abstrct.AbstractController;
import com.nauticana.nams.model.CaptionTranslation;
import com.nauticana.nams.model.Language;
import com.nauticana.nams.utils.PortalLanguage;

@Controller
@RequestMapping("/" + Language.rootMapping)
public class LanguageController extends AbstractController<Language, String> {

	public static final String[] lookuplists = null;
	public static final String[] detailTables = new String[] {CaptionTranslation.tableName};
	public static final String[][] detailFields = new String[][] {CaptionTranslation.fieldNames};
	public static final String[] actionlists = new String[] {"RELOAD_TRANSLATION"};
	public static final String listView   = Language.rootMapping + "List";
	public static final String editView   = Language.rootMapping + "Edit";
	public static final String showView   = Language.rootMapping + "Show";
	public static final String selectView = Language.rootMapping + "Select";

	private static String[] domainNames = null;
	private static String[] domainlists = null;
	
	@Override
	protected String rootMapping() {return Language.rootMapping;}

	@Override
	protected String tableName() {return Language.tableName;}

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
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(Language.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

	@Override
	protected String[] domainlists() {return domainlists;}

	@Override
	protected String[] lookuplists() {return lookuplists;}

	@Override
	protected String[][] lookupService(int i) {
		return null;
	}

	@Override
	protected String[] actions() {return actionlists;}

	@Override
	protected String[][] detailActions() {return null;}
	
	@RequestMapping(value="/reloadTranslation",method=RequestMethod.GET)
	public ModelAndView reloadTranslation(HttpServletRequest request) {
		String id = request.getParameter("id");
		Language language = modelService.findById(id);
		PortalLanguage pl = dataCache.getLanguage(id);
		pl.translations.clear();
		pl.iconText.clear();
		dataCache.loadTranslations(language);
		return new ModelAndView("redirect:show?id="+id);
	}

}
