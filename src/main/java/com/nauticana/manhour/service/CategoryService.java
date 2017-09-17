package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.manhour.model.Category;

@Service
public class CategoryService extends AbstractService<Category,Integer> {

	
	@Override
	public String[] getFieldNames() {
		return Category.fieldNames;
	}

	@Override
	@Transactional
	public Category newEntity(String parentKey) {
		return new Category();
	}

	@Override
	public Integer StrToId(String id) {
		return Integer.parseInt(id);
	}

	@Override
	public Category newEntityWithId(String strId) {
		Category entity = new Category();
		entity.setId(StrToId(strId));
		return entity;
	}


}
