package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.manhour.model.Category;
import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.utils.Utils;

@Service
public class CategoryService extends AbstractService<Category,Integer> {

	@Override
	public String tableName() {
		return Category.tableName;
	}
	
	@Override
	public String[] fieldNames() {
		return Category.fieldNames;
	}

	@Override
	@Transactional
	public Category newEntity(String parentKey) {
		Category entity = new Category();
		entity.setMainFlag('-');
		if (!Utils.emptyStr(parentKey)) {
			Category parent = r.getOne(Integer.parseInt(parentKey));
			if (parent != null) {
				entity.setParentId(parent.getId());
				entity.setCatLevel((byte) (parent.getCatLevel()+1));
				entity.setTreeCode(parent.getTreeCode()+"-");
				entity.setUnit(parent.getUnit());
			}
		} else {
			entity.setCatLevel((byte) 1);
		}
		return entity;
	}

	public Category create(Category entity) {
		if (entity.getParentId() != null) {
			Category parent = r.getOne(entity.getParentId());
			entity.setCatLevel((byte) (parent.getCatLevel()+1));
			entity.setTreeCode(parent.getTreeCode()+"-"+entity.getCatIndex());
		}
		return super.create(entity);
	}
	
	public void save(Category entity) {
		if (entity.getParentId() != null) {
			Category parent = r.getOne(entity.getParentId());
			entity.setTreeCode(parent.getTreeCode()+"-"+entity.getCatIndex());
		}
		super.create(entity);
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

	@Override
	public String[][] findAllStr() {
		return null;
	}

}
