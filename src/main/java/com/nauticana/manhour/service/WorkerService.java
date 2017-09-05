package com.nauticana.manhour.service;

import com.nauticana.manhour.exception.RecordNotFound;
import com.nauticana.manhour.model.Worker;

public class WorkerService extends AbstractService<Worker, Integer> {

	@Override
	public Worker findStrId(String id) {
		return findById(Integer.parseInt(id));
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		remove(Integer.parseInt(id));		
	}

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return Worker.fieldNames;
	}

	@Override
	public Worker newEntity() {
		// TODO Auto-generated method stub
		return new Worker();
	}

}
