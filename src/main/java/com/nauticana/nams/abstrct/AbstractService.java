package com.nauticana.nams.abstrct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractService<ModelBean, ModelId extends Serializable> implements IAbstractService<ModelBean, ModelId> {
	
	@Autowired
	protected JpaRepository<ModelBean, ModelId> r;

	@Override
	public ModelBean findById(ModelId id) {
		return r.findOne(id);
	}

	@Override
	public ModelBean create(ModelBean entity) throws Exception {
		try {
			return r.save(entity);
		} catch(DataAccessException e) {
			throw new Exception(e.getRootCause().getMessage());
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void save(ModelBean entity) throws Exception {
		try {
			r.save(entity);
		} catch(DataAccessException e) {
			throw new Exception(e.getRootCause().getMessage());
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public void remove(ModelId id) throws Exception {
		try {
			r.delete(id);
		} catch(DataAccessException e) {
			throw new Exception(e.getRootCause().getMessage());
		} catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public List<ModelBean> findAll() {
		return r.findAll();
	}

	@Override
	public List<ModelBean> findByIds(List<String> ids) {
		List<ModelBean> entities = new ArrayList<ModelBean>();
		for (String s : ids) {
			if (s.equals("*"))
				return r.findAll();
			ModelId id = StrToId(s);
			ModelBean entity = r.findOne(id);
			entities.add(entity);
		}
		return entities;
	}
	
}
