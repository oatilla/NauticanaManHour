package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;

import com.nauticana.manhour.exception.RecordNotFound;
import com.nauticana.manhour.model.Subcontractor;

@Service
public class SubcontractorService extends AbstractService<Subcontractor,Integer > {

	@Override
	public Subcontractor findStrId(String id) {
		// TODO Auto-generated method stub
		return findById(Integer.parseInt(id));
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		// TODO Auto-generated method stub
		remove(Integer.parseInt(id));
	}

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return Subcontractor.fieldNames;
	}

	@Override
	public Subcontractor newEntity() {
		// TODO Auto-generated method stub
		return new Subcontractor();
	}
}
