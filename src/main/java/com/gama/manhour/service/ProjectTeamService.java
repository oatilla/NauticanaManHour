package com.gama.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gama.manhour.exception.RecordNotFound;
import com.gama.manhour.model.DomainValueId;
import com.gama.manhour.model.ProjectTeam;
import com.gama.manhour.model.ProjectTeamId;
import com.gama.manhour.repository.ProjectRepository;
import com.gama.manhour.repository.ProjectTeamRepository;

@Service
public class ProjectTeamService implements IAbstractService<ProjectTeam> {

	
	@Autowired
	public ProjectTeamRepository r;
	
	
	
	public ProjectTeam findById(ProjectTeamId id) {
		return r.findOne(id);
	}

	@Override
	public ProjectTeam create(ProjectTeam entity) {
		return r.save(entity);
	}

	@Override
	public void save(ProjectTeam entity) {
		r.save(entity);
	}

	@Override
	public void removeStrId(String id) throws RecordNotFound {
		
		
	}

	@Override
	public List<ProjectTeam> findAll() {
		return r.findAll();
	}

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return ProjectTeam.fieldNames;
	}

	@Override
	public ProjectTeam newEntity() {
		return new ProjectTeam();
	}

	@Override
	public ProjectTeam findStrId(String id) {
		return null;
	}

}
