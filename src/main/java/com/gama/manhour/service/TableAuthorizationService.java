package com.gama.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gama.manhour.exception.RecordNotFound;
import com.gama.manhour.model.TableAuthorization;
import com.gama.manhour.model.TableAuthorizationId;
import com.gama.manhour.repository.TableAuthorizationRepository;

@Service
public class TableAuthorizationService implements IAbstractService<TableAuthorization> {

	
	@Autowired
	public TableAuthorizationRepository r;
	
	
	
	public TableAuthorization findById(TableAuthorizationId id) {
		return r.findOne(id);
	}

	@Override
	public TableAuthorization create(TableAuthorization entity) {
		return r.save(entity);
	}

	@Override
	public void save(TableAuthorization entity) {
		r.save(entity);
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		
		
	}

	@Override
	public List<TableAuthorization> findAll() {
		return r.findAll();
	}

	@Override
	public String[] getFieldNames() {
		return TableAuthorization.fieldNames;
	}

	@Override
	public TableAuthorization newEntity() {
		return new TableAuthorization();
	}

	@Override
	public TableAuthorization findStrId(String id) {
		return null;
	}

}