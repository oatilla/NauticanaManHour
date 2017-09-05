package com.gama.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gama.manhour.exception.RecordNotFound;
import com.gama.manhour.model.ProjectWbsQuantity;
import com.gama.manhour.model.ProjectWbsQuantityId;
import com.gama.manhour.repository.ProjectWbsQuantityRepository;

@Service
public class ProjectWbsQuantityService implements IAbstractService<ProjectWbsQuantity> {

	
	@Autowired
	public ProjectWbsQuantityRepository r;
	
	
	
	public ProjectWbsQuantity findById(ProjectWbsQuantityId id) {
		return r.findOne(id);
	}

	@Override
	public ProjectWbsQuantity create(ProjectWbsQuantity entity) {
		return r.save(entity);
	}

	@Override
	public void save(ProjectWbsQuantity entity) {
		r.save(entity);
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		
		
	}

	@Override
	public List<ProjectWbsQuantity> findAll() {
		return r.findAll();
	}

	@Override
	public String[] getFieldNames() {
		return ProjectWbsQuantity.fieldNames;
	}

	@Override
	public ProjectWbsQuantity newEntity() {
		return new ProjectWbsQuantity();
	}

	@Override
	public ProjectWbsQuantity findStrId(String id) {
		return null;
	}

}
