package com.app.wms.engine.db.dao;

import java.util.List;

import com.app.wms.engine.db.dto.Province;
import com.app.wms.engine.db.exceptions.ProvinceDaoException;

public interface ProvinceDao {
	
	public List<Province> findAll() throws ProvinceDaoException;

}
