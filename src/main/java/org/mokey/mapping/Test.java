package org.mokey.mapping;

import java.util.ArrayList;
import java.util.List;

import org.mokey.mapping.configurations.Configuration;
import org.mokey.mapping.dal.DataAccessor;
import org.mokey.mapping.dal.providers.TestProvider;
import org.mokey.mapping.tuples.NumberTuple;
import org.mokey.mapping.tuples.StringTuple;
import org.mokey.mapping.tuples.Tuple;

public class Test {
	public static void main(String[] args){
		
		String[] test = {"campany","20","ford","rob","forest"};
		List<Tuple<?>> tt = new ArrayList<Tuple<?>>();
		for (int i = 0; i < test.length; i++) {
			if(i == 1){
				NumberTuple ntup = new NumberTuple();
				ntup.setValue(Integer.parseInt(test[i]));
				tt.add(ntup);
				continue;
			}
			Tuple<String> tup = new StringTuple();
			tup.setValue(test[i]);
			tt.add(tup);
		}
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Configuration conf = new Configuration(loader.getResource("config.xml").getFile());
		DataAccessor dao = new DataAccessor(new TestProvider());
		Mapper mapper = new Mapper(conf, dao);
		List<Tuple<?>> result = mapper.map(tt);
		
		for (Tuple<?> tuple : result) {
			System.out.print(tuple.getValue() + ": ");
		}
	}
}
