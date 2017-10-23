package com.nauticana.nams.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nauticana.nams.abstrct.AbstractController;
import com.nauticana.nams.model.AuthorityGroup;
import com.nauticana.nams.model.ObjectAuthorization;
import com.nauticana.nams.model.ObjectAuthorizationId;
import com.nauticana.nams.service.AuthorityObjectActionService;
import com.nauticana.nams.service.AuthorityObjectService;
import com.nauticana.nams.utils.Labels;
import com.nauticana.nams.utils.Utils;

@Controller
@RequestMapping("/" + ObjectAuthorization.rootMapping)
public class ObjectAuthorizationController extends AbstractController<ObjectAuthorization, ObjectAuthorizationId>{

	public static final String[] lookuplists = new String[] {"authorityObjectList"};
	public static final String[] detailTables = null;
	public static final String[][] detailFields = null;
	public static final String listView   = ObjectAuthorization.rootMapping + "List";
	public static final String editView   = ObjectAuthorization.rootMapping + "Edit";
	public static final String showView   = ObjectAuthorization.rootMapping + "Show";
	public static final String selectView = ObjectAuthorization.rootMapping + "Select";

	private static String[] domainNames = null;
	private static String[] domainlists = null;

	@Autowired
	private AuthorityObjectService aos;
	
	@Autowired
	private AuthorityObjectActionService aoas;
	
	@Override
	protected String rootMapping() {return ObjectAuthorization.rootMapping;}

	@Override
	protected String tableName() {return ObjectAuthorization.tableName;}

	@Override
	protected String listView() {return listView;}

	@Override
	protected String editView() {return editView;}

	@Override
	protected String showView() {return showView;}

	@Override
	protected String selectView() {return selectView;}

	@Override
	protected String prevPage(String id) {if (Utils.emptyStr(id)) return rootMapping()+"/list"; return AuthorityGroup.rootMapping+"/show?id="+id.split(",")[0];}

	@Override
	protected String[] detailTables() {return detailTables;}

	@Override
	protected String[][] detailFields() {return detailFields;}
	
	@Override
	protected String[] domainNames() {if (domainNames == null) {domainNames=namsJdbcService.tableDomains(ObjectAuthorization.tableName); domainlists = initDomainList(domainNames);} return domainNames;}

	@Override
	protected String[] domainlists() {return domainlists;}

	@Override
	protected String[] lookuplists() {return lookuplists;}

	@Override
	protected String[][] lookupService(int i) {
		switch (i) {
		case 0: return aos.findAllStr();
		}
		return null;
	}

	@Override
	protected String[] actions() {return null;}

	@Override
	protected String[][] detailActions() {return null;}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newGet(HttpServletRequest request) {

		ModelAndView model = super.newGet(request);
		
		String authorityGroup  = request.getParameter("authorityGroup");
		String objectType     = request.getParameter("objectType");
		if (!Utils.emptyStr(objectType)) {
			ObjectAuthorization record = modelService.newEntity(authorityGroup);
			record.getId().setObjectType(objectType);
			model.addObject("record", record);
			model.addObject("authorityGroup", authorityGroup);
			model.addObject("objectType", objectType);
			model.addObject("actionList", aoas.findAllStr(objectType));
			model.addObject(Labels.PREVPAGE, prevPage(authorityGroup));
		}
		return model;
	}


}
