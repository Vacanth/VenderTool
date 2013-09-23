package com.vendertool.common.dal.dao;

import java.util.Map;

import com.mysema.query.Tuple;
import com.mysema.query.sql.RelationalPath;
import com.mysema.query.sql.dml.Mapper;
import com.mysema.query.types.Path;

public interface DALMapper<T> extends Mapper<T>{
	/**
	 * Will populate the whole bean, null values will be null in the DB, for specific updates to DB use createMap method
	 * 
	 * @param t
	 * @return
	 */
	public Object populateBean(T t);
	
	/**
	 * Converts tuple to Object for specified paths
	 * 
	 * @param row
	 * @param paths
	 * @return
	 */
	public T convert(Tuple row, Path<?>[] paths);
	
	 
	
}
