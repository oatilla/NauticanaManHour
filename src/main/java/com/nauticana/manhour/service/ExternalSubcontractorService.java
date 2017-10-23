package com.nauticana.manhour.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.ExternalSubcontractor;
import com.nauticana.nams.abstrct.AbstractService;
@Service
public class ExternalSubcontractorService extends AbstractService<ExternalSubcontractor, String> {

	@Override
	public String tableName() {
		return ExternalSubcontractor.tableName;
	}

	@Override
	public String[] fieldNames() {
		return ExternalSubcontractor.fieldNames;
	}

	@Override
	public ExternalSubcontractor newEntity(String parentKey) {
		return null;
	}

	@Override
	public ExternalSubcontractor newEntityWithId(String strId) {
		return null;
	}

	@Override
	public String StrToId(String id) {
		return null;
	}

	@Override
	public String[][] findAllStr() {
		List<ExternalSubcontractor> list = findAll();
		String[][] items = new String[list.size()+1][2];
		int i = 0;
		items[i][0] = null;
		items[i][1] = " - ";
		for(ExternalSubcontractor x : list) {
			i++;
			items[i][0] = x.getExtSubcontractor();
			items[i][1] = x.getCaption();
		}
		return items;
//		Map<String, String> map = new TreeMap<String, String>();
//		map.put(null, " - ");
//		for(ExternalSubcontractor x : list) {
//			map.put(x.getExtSubcontractor().toString(), x.getCaption().toString());
//		}
//		return map;	
	}

}
