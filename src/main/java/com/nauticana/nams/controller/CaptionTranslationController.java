package com.nauticana.nams.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nauticana.nams.abstrct.AbstractController;
import com.nauticana.nams.model.CaptionTranslation;
import com.nauticana.nams.model.CaptionTranslationId;
import com.nauticana.nams.model.Language;
import com.nauticana.nams.utils.Utils;

@Controller
@RequestMapping("/" + CaptionTranslation.rootMapping)
public class CaptionTranslationController extends AbstractController<CaptionTranslation, CaptionTranslationId> {

	public static final String[] lookuplists = null;
	public static final String[] detailTables = null;
	public static final String[][] detailFields = null;
	public static final String listView = CaptionTranslation.rootMapping + "List";
	public static final String editView = CaptionTranslation.rootMapping + "Edit";
	public static final String showView = CaptionTranslation.rootMapping + "Show";
	public static final String selectView = CaptionTranslation.rootMapping + "Select";
	
	private static String[] domainNames = null;
	private static String[] domainlists = null;

	@Override
	protected String rootMapping() {return CaptionTranslation.rootMapping;}

	@Override
	protected String tableName() {return CaptionTranslation.tableName;}

	@Override
	protected String listView() {return listView;}

	@Override
	protected String editView() {return editView;}

	@Override
	protected String showView() {return showView;}

	@Override
	protected String selectView() {return selectView;}

	@Override
	protected String prevPage(String id) {
		if (Utils.emptyStr(id)) return rootMapping()+"/list";
		String[] s = id.split(",");
		if (s.length == 1) return Language.rootMapping+"/show?id="+id.split(",")[0];
		return Language.rootMapping+"/show?id="+id.split(",")[1];
	}

	@Override
	protected String[] detailTables() {return detailTables;}

	@Override
	protected String[][] detailFields() {return detailFields;}
	
	@Override
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(CaptionTranslation.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

	@Override
	protected String[] domainlists() {return domainlists;}

	@Override
	protected String[] lookuplists() {return lookuplists;}

	@Override
	protected String[][] lookupService(int i) {
		return null;
	}

	@Override
	protected String[] actions() {return null;}

	@Override
	protected String[][] detailActions() {return null;}
}
