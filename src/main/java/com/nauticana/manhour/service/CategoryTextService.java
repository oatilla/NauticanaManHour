package com.nauticana.manhour.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nauticana.manhour.model.CategoryText;
import com.nauticana.manhour.model.CategoryTextId;
import com.nauticana.nams.abstrct.AbstractService;
import com.nauticana.nams.utils.Utils;

@Service
public class CategoryTextService extends AbstractService<CategoryText,CategoryTextId> {

	@Override
	public String tableName() {
		return CategoryText.tableName;
	}
	
	@Override
	public String[] fieldNames() {
		return CategoryText.fieldNames;
	}

	@Override
	@Transactional
	public CategoryText newEntity(String parentKey) {
		CategoryText entity = new CategoryText();
		if (!Utils.emptyStr(parentKey)) {
			CategoryTextId id = new CategoryTextId(parentKey);
			entity.setId(id);
		}
		return entity;
	}

	@Override
	public CategoryTextId StrToId(String id) {
		String[] s = id.split(",");
		return new CategoryTextId(Integer.parseInt(s[0]), s[1]);
	}

	@Override
	public CategoryText newEntityWithId(String strId) {
		CategoryText entity = new CategoryText();
		entity.setId(StrToId(strId));
		return entity;
	}

	@Override
	public String[][] findAllStr() {
		return null;
	}

}
