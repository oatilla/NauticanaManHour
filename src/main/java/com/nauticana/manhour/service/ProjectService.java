package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.manhour.model.Project;
import com.nauticana.nams.abstrct.AbstractService;

@Service
public class ProjectService extends AbstractService<Project, Integer> {

	@Override
	public String tableName() {
		return Project.tableName;
	}

	@Override
	public String[] fieldNames() {
		return Project.fieldNames;
	}

	@Override
	@Transactional
	public Project newEntity(String parentKey) {
		Project entity = new Project();
		entity.setStatus("INITIAL");
		return entity;
	}

	@Override
	public Integer StrToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public Project newEntityWithId(String strId) {
		Project entity = new Project();
		entity.setId(StrToId(strId));
		entity.setStatus("INITIAL");
		return entity;
	}

	@Override
	public String[][] findAllStr() {
		return null;
	}

}
