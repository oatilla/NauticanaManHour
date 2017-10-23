package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.Subcontractor;
import com.nauticana.nams.abstrct.AbstractService;

@Service
public class SubcontractorService extends AbstractService<Subcontractor,Integer > {

	@Override
	public String tableName() {
		return Subcontractor.tableName;
	}

	@Override
	public String[] fieldNames() {
		return Subcontractor.fieldNames;
	}

	@Override
	public Subcontractor newEntity(String parentKey) {
		return new Subcontractor();
	}

	@Override
	public Integer StrToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public Subcontractor newEntityWithId(String strId) {
		Subcontractor entity = new Subcontractor();
		entity.setId(StrToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllStr() {
		return null;
	}
}
