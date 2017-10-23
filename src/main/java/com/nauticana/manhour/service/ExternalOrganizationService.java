package com.nauticana.manhour.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ExternalOrganization;
import com.nauticana.nams.abstrct.AbstractService;
@Service
public class ExternalOrganizationService extends AbstractService<ExternalOrganization, Integer>{

	@Override
	public String[][] findAllStr() {
		List<ExternalOrganization> list = findAll();
		String[][] items = new String[list.size()+1][2];
		int i = 0;
		items[i][0] = null;
		items[i][1] = " - ";
		for(ExternalOrganization x : list) {
			i++;
			items[i][0] = x.getOrganizationId()+"";
			items[i][1] = x.getOrganizationName();
		}
		return items;
	}
//	public Map<String, String> findAllStr() {
//		List<ExternalOrganization> list = findAll();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put(null, " - ");
//		for(ExternalOrganization x : list) {
//			map.put(x.getOrganizationId().toString(), x.getOrganizationName());
//		}
//		return map;
//	}

	@Override
	public String tableName() {
		return ExternalOrganization.tableName;
	}

	@Override
	public String[] fieldNames() {
		return ExternalOrganization.fieldNames;
	}

	@Override
	public ExternalOrganization newEntity(String parentKey) {
		return null;
	}

	@Override
	public ExternalOrganization newEntityWithId(String strId) {
		return null;
	}

	@Override
	public Integer StrToId(String id) {
		return null;
	}

}
