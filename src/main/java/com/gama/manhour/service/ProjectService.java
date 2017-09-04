package com.gama.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gama.manhour.exception.RecordNotFound;
import com.gama.manhour.model.Project;
import com.gama.manhour.repository.ProjectRepository;

@Service
public class ProjectService implements IAbstractService<Project> {

	@Autowired
	private ProjectRepository r;
	
	public Project findById(int id) {
		return r.findOne(id);
	}
	
	public void remove(int id) throws RecordNotFound {
		Project l = r.findOne(id);
		if (l == null) throw new RecordNotFound();
		r.delete(id);
	}

	@Override
	@Transactional
	public Project findStrId(String id) {
		return findById(Integer.parseInt(id));
	}

	@Override
	@Transactional
	public void removeStrId(String id) throws RecordNotFound {
		remove(Integer.parseInt(id));
	}

	@Override
	public String[] getFieldNames() {
		return Project.fieldNames;
	}

	@Override
	@Transactional
	public Project newEntity() {
		return new Project();
	}

	@Override
	public Project create(Project entity) {
		return r.save(entity);
	}

	@Override
	public void save(Project entity) {
		r.save(entity);
	}

	@Override
	public List<Project> findAll() {
		return r.findAll();
	}

}
