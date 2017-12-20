package com.nauticana.nams.abstrct;

import java.util.List;

public interface IAbstractService <ModelBean,ModelId> {

	ModelBean findById(ModelId id);
	ModelBean create(ModelBean entity) throws Exception;
	void save(ModelBean entity) throws Exception;
	void remove(ModelId id) throws Exception;
	List<ModelBean> findAll();
	ModelBean newEntity(String parentKey);
	ModelBean newEntityWithId(String strId);
	ModelId StrToId(String id);
	List<ModelBean> findByIds(List<String> ids);
	String[][] findAllStr();
	String tableName();
	String[]  fieldNames();
}
