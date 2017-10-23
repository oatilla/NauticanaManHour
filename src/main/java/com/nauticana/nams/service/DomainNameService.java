package com.nauticana.nams.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.model.DomainName;

@Service
public class DomainNameService extends AbstractService<DomainName,String> {
		
	@Override
	public String tableName() {
		return DomainName.tableName;
	}

	@Override
	public String[] fieldNames() {
		return DomainName.fieldNames;
	}

	@Override
	public DomainName newEntity(String parentKey) {
		return new DomainName();
	}
	
	@Override
	public String StrToId(String id) {
		return id;
	}

	@Override
	public DomainName newEntityWithId(String strId) {
		DomainName entity = new DomainName();
		entity.setId(StrToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllStr() {
		List<DomainName> list = findAll();
		String[][] items = new String[list.size()+1][2];
		int i = -1;
		for(DomainName x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getId() + " " + x.getId();
		}
		return items;
//		Map<String, String> map = new HashMap<String, String>();
//		map.put(null, " - ");
//		for(DomainName x : list) {
//			map.put(x.getId(), x.getCaption());
//		}
//		return map;
	}
}
