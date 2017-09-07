package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.model.Worker;

@Service
public class WorkerService extends AbstractService<Worker, Integer> {

	@Override
	public String[] getFieldNames() {
		return Worker.fieldNames;
	}

	@Override
	public Worker newEntity() {
		return new Worker();
	}

	@Override
	public Integer StrToId(String id) {
		return Integer.parseInt(id);
	}

}
