package com.gama.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gama.manhour.exception.RecordNotFound;
import com.gama.manhour.model.ProjectWbs;
import com.gama.manhour.model.ProjectWbsId;
import com.gama.manhour.repository.ProjectWbsRepository;

@Service
public class ProjectWbsService implements IAbstractService<ProjectWbs> {

	
	@Autowired
	public ProjectWbsRepository r;
	
	
	
	public ProjectWbs findById(ProjectWbsId id) {
		return r.findOne(id);
	}

	@Override
	public ProjectWbs create(ProjectWbs entity) {
		return r.save(entity);
	}

	@Override
	public void save(ProjectWbs entity) {
		r.save(entity);
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		
		
	}

	@Override
	public List<ProjectWbs> findAll() {
		return r.findAll();
	}

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return ProjectWbs.fieldNames;
	}

	@Override
	public ProjectWbs newEntity() {
		return new ProjectWbs();
	}

	@Override
	public ProjectWbs findStrId(String id) {
		return null;
	}

}
