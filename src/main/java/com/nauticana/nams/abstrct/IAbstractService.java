package com.nauticana.nams.abstrct;

import java.util.List;

import com.nauticana.manhour.exception.RecordNotFound;

public interface IAbstractService <ModelBean,ModelId> {

	ModelBean findById(ModelId id);
	ModelBean create(ModelBean entity);
	void save(ModelBean entity);
	void remove(ModelId id) throws RecordNotFound;
	List<ModelBean> findAll();
	ModelBean newEntity(String parentKey);
	ModelBean newEntityWithId(String strId);
	ModelId StrToId(String id);
	List<ModelBean> findByIds(List<String> ids);
	String[][] findAllStr();
	String tableName();
	String[]  fieldNames();
}
