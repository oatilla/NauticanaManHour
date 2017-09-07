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
	public Category newEntity() {
		return new Category();
	}

	@Override
	public Integer StrToId(String id) {
		return Integer.parseInt(id);
	}


}
