package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.manhour.exception.RecordNotFound;
import com.nauticana.manhour.model.Project;

@Service
public class ProjectService extends AbstractService<Project, Integer> {

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
}
