package com.nauticana.manhour.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.Worker;
import com.nauticana.manhour.repository.SubcontractorRepository;
import com.nauticana.manhour.utils.Utils;

@Service
public class WorkerService extends AbstractService<Worker, Integer> {

	@Override
	public String[] getFieldNames() {
		return Worker.fieldNames;
	}

	@Autowired
	SubcontractorRepository parentRep;
	@Override
	public Worker newEntity(String parentKey) {
		Worker entity = new Worker();
		if (!Utils.emptyStr(parentKey)) entity.setSubcontractor(parentRep.findOne(StrToId(parentKey)));
		return entity;
	}

	@Override
	public Integer StrToId(String id) {
		return Integer.parseInt(id);
	}

}
