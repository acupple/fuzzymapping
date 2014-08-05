package org.mokey.mapping.unittests;

import org.junit.Assert;
import org.junit.Test;
import org.mokey.mapping.Mapper;
import org.mokey.mapping.configurations.Configuration;
import org.mokey.mapping.dal.DataAccessor;
import org.mokey.mapping.dal.providers.TestProvider;
import org.mokey.mapping.tuples.NumberTuple;
import org.mokey.mapping.tuples.StringTuple;
import org.mokey.mapping.tuples.Tuple;

public class MapperTest {

	@Test
	public void mapTest() {
		String[] test = {"campany","20","ford","rob","forest"};
		String[] expecteds = {"campany","20","ford","rot","forest"};
		Tuple<?>[] tt = new Tuple<?>[test.length];
		for (int i = 0; i < test.length; i++) {
			if(i == 1){
				NumberTuple ntup = new NumberTuple();
				ntup.setValue(Integer.parseInt(test[i]));
				tt[i] = ntup;
				continue;
			}
			Tuple<String> tup = new StringTuple();
			tup.setValue(test[i]);
			tt[i] = tup;
		}
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Configuration conf = new Configuration(loader.getResource("config.xml").getFile());
		DataAccessor dao = new DataAccessor(new TestProvider());
		Mapper mapper = new Mapper(conf, dao);
		Tuple<?>[] result = mapper.map(tt);
		String[] actual = new String[result.length];
		for (int i = 0; i < actual.length; i++) {
			actual[i] = result[i].getValue().toString();
		}
		Assert.assertArrayEquals(actual, expecteds);
	}

}
