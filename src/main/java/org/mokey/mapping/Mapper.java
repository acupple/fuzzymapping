package org.mokey.mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.mokey.mapping.configurations.Configuration;
import org.mokey.mapping.dal.DataAccessor;
import org.mokey.mapping.tuples.Tuple;

public class Mapper{
	private static Logger log = Logger.getLogger("Mapper");
	private Configuration config = null;
	private DataAccessor dao = null;
	
	public Mapper(Configuration config, DataAccessor dao){
		this.config = config;
		this.dao = dao;
	}
	
	public List<Tuple<?>> map(List<Tuple<?>> tuples) {
		if(tuples.size() != this.config.tupleCount()){
			log.error(String.format("Count[%s, %s] of input tuples is not correct", 
					tuples.size(), this.config.tupleCount()));
			return null;
		}
		log.debug("Input tuples is :" + StringUtils.join(tuples, ","));
		
		List<Tuple<?>> result = null;
		double minValue = Double.MAX_VALUE;
		List<List<Tuple<?>>> lines = this.dao.getTargetCollection(tuples);
		
		log.debug("Target collectiion size is: " + lines.size());
		
		Map<List<Tuple<?>>, Double> candiate = new HashMap<List<Tuple<?>>, Double>();
		for (List<Tuple<?>> line : lines) {
			if(line.size() != this.config.tupleCount()){
				log.error(String.format("Invalid target truples, cause: tuples count[%s, %s] not matched to the config",
						line.size(), this.config.tupleCount()));
				continue;
			}
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
			log.debug(String.format("Caculate the value of tuple[%s] is : %s", StringUtils.join(line, ","), value));
		}
		
		log.debug(String.format("Final value of mapped tuple[%s] is : %s", StringUtils.join(result, ","), minValue));
		return result;
	}
}
