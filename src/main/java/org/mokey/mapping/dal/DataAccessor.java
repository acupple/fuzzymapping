package org.mokey.mapping.dal;

import java.util.List;

import org.mokey.mapping.tuples.Tuple;

public class DataAccessor {
	
	private DataProvider provider;
	
	public DataAccessor(DataProvider provider){
		this.provider = provider;
	}
	
	public List<List<Tuple<?>>> getTargetCollection(List<Tuple<?>> res){
		return this.provider.getTargetCollection(res);
	}
}
