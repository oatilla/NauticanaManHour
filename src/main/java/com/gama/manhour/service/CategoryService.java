package com.gama.manhour.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gama.manhour.exception.RecordNotFound;
import com.gama.manhour.model.Category;
import com.gama.manhour.repository.CategoryRepository;

@Service
public class CategoryService implements IAbstractService<Category> {

	@Autowired
	private CategoryRepository r;
	
	public Category findById(int id) {
		return r.findOne(id);
	}
	
	public void remove(int id) throws RecordNotFound {
		Category l = r.findOne(id);
		if (l == null) throw new RecordNotFound();
		r.delete(id);
	}

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

	@Override
	public Category create(Category entity) {
		return r.save(entity);
	}

	@Override
	public void save(Category entity) {
		r.save(entity);
	}

	@Override
	public List<Category> findAll() {
		return r.findAll();
	}

}
