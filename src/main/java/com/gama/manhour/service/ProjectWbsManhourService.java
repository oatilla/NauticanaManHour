package com.gama.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gama.manhour.exception.RecordNotFound;
import com.gama.manhour.model.ProjectWbsManhour;
import com.gama.manhour.model.ProjectWbsManhourId;
import com.gama.manhour.repository.ProjectWbsManhourRepository;

@Service
public class ProjectWbsManhourService implements IAbstractService<ProjectWbsManhour> {

	
	@Autowired
	public ProjectWbsManhourRepository r;
	
	
	
	public ProjectWbsManhour findById(ProjectWbsManhourId id) {
		return r.findOne(id);
	}

	@Override
	public ProjectWbsManhour create(ProjectWbsManhour entity) {
		return r.save(entity);
	}

	@Override
	public void save(ProjectWbsManhour entity) {
		r.save(entity);
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		
		
	}

	@Override
	public List<ProjectWbsManhour> findAll() {
		return r.findAll();
	}

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return ProjectWbsManhour.fieldNames;
	}

	@Override
	public ProjectWbsManhour newEntity() {
		return new ProjectWbsManhour();
	}

	@Override
	public ProjectWbsManhour findStrId(String id) {
		return null;
	}

}
