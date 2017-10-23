package com.nauticana.manhour.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.Cbs;
import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.model.Language;

@Service
public class CbsService  extends AbstractService<Cbs, String> {
	
	@Override
	public String tableName() {
		return Language.tableName;
	}

	@Override
	public String[] fieldNames() {
		return Language.fieldNames;
	}

	@Override
	public Cbs newEntity(String parentKey) {
		return new Cbs();
	}

	@Override
	public String StrToId(String id) {
		return id;
	}

	@Override
	public Cbs newEntityWithId(String strId) {
		Cbs entity = new Cbs();
		entity.setId(StrToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllStr() {
		List<Cbs> list = findAll();
		String[][] items = new String[list.size()+1][2];
		int i = 0;
		items[i][0] = null;
		items[i][1] = " - ";
		for(Cbs x : list) {
			i++;
			items[i][0] = x.getId();
			items[i][1] = x.getId() + " " + x.getCaption();
		}
		return items;
	}
//	public Map<String, String> findAllStr() {
//		List<Cbs> list = findAll();
//		Map<String, String> map = new TreeMap<String, String>();
//		map.put(null, " - ");
//		for(Cbs x : list) {
//			map.put(x.getId(), x.getId() + " " + x.getCaption());
//		}
//		return map;
//	}
}
