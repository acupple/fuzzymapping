package org.mokey.mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mokey.mapping.configurations.Configuration;
import org.mokey.mapping.dal.DataAccessor;
import org.mokey.mapping.tuples.Tuple;

public class Mapper{
	private Configuration config = null;
	private DataAccessor dao = null;
	
	public Mapper(Configuration config, DataAccessor dao){
		this.config = config;
		this.dao = dao;
	}
	
	public List<Tuple<?>> map(List<Tuple<?>> tuples) {
		List<Tuple<?>> result = null;
		double minValue = Double.MAX_VALUE;
		List<List<Tuple<?>>> lines = this.dao.getTarget();
		Map<List<Tuple<?>>, Double> candiate = new HashMap<List<Tuple<?>>, Double>();
		for (List<Tuple<?>> line : lines) {
			double value = 0.0;
			for (int i = 0; i < line.size(); i++) {
				value += line.get(i).diff(tuples.get(i)) * this.config.getWeight(i).getWeight();
			}
			if(value < minValue)
			{
				minValue = value;
				result = line;
			}
			if(value <= this.config.getMaxValue() && !candiate.containsKey(line))
				candiate.put(line, value);
		}
		return result;
	}
}
