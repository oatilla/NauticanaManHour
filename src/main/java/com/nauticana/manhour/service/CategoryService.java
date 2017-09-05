package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.manhour.exception.RecordNotFound;
import com.nauticana.manhour.model.Category;

@Service
public class CategoryService extends AbstractService<Category,Integer> {

	
	@Override
	@Transactional
	public Category findStrId(String id) {
		return findById(Integer.parseInt(id));
	}

	@Override
	public String[] getFieldNames() {
		return Category.fieldNames;
	}

	@Override
	@Transactional
	public Category newEntity() {
		return new Category();
	}

	@Override
	@Transactional
	public void removeStrId(String id) throws NumberFormatException, RecordNotFound {
		remove(Integer.parseInt(id));
	}


}
