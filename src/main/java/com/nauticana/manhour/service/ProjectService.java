package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.manhour.model.Project;

@Service
public class ProjectService extends AbstractService<Project, Integer> {

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
	public Integer StrToId(String id) {
		return Integer.parseInt(id);
	}
}
