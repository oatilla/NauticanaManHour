package com.gama.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gama.manhour.exception.RecordNotFound;
import com.gama.manhour.model.ProjectTeamPerson;
import com.gama.manhour.model.ProjectTeamPersonId;
import com.gama.manhour.repository.ProjectTeamPersonRepository;

@Service
public class ProjectTeamPersonService implements IAbstractService<ProjectTeamPerson> {

	
	@Autowired
	private ProjectTeamPersonRepository r;
	
	public ProjectTeamPerson findStrId(ProjectTeamPersonId id) {
		return r.findOne(id);
	}

	@Override
	public ProjectTeamPerson create(ProjectTeamPerson entity) {
		return r.save(entity);
	}

	@Override
	public void save(ProjectTeamPerson entity) {
		r.save(entity);
	}

	public void removeStrId(ProjectTeamPersonId id) throws RecordNotFound {
		r.delete(id);
		
	}

	@Override
	public List<ProjectTeamPerson> findAll() {
		return r.findAll();
	}

	@Override
	public String[] getFieldNames() {
		return ProjectTeamPerson.fieldNames;
	}

	@Override
	public ProjectTeamPerson newEntity() {
		return new ProjectTeamPerson();
	}

	@Override
	public ProjectTeamPerson findStrId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		// TODO Auto-generated method stub
		
	}
}
