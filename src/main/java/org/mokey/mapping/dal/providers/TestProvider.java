package org.mokey.mapping.dal.providers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.mokey.mapping.dal.DataProvider;
import org.mokey.mapping.tuples.NumberTuple;
import org.mokey.mapping.tuples.StringTuple;
import org.mokey.mapping.tuples.Tuple;

public class TestProvider implements DataProvider{
	public List<List<Tuple<?>>> getTargetCollection(List<Tuple<?>> res) {
		List<List<Tuple<?>>> result = new ArrayList<List<Tuple<?>>>();
		File file = new File(Thread.currentThread().getContextClassLoader().getResource("testdata").getFile());
		if (file.isFile() && file.exists()){
			try {
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), "UTF-8");
				@SuppressWarnings("resource")
				BufferedReader reader = new BufferedReader(read);
				String line;
				List<Tuple<?>> linetos = null;
				while ((line = reader.readLine()) != null) {
					String[] tokens = line.split(",");
					if(tokens.length == 5){
						linetos = new ArrayList<Tuple<?>>();
						for (int i = 0; i < tokens.length; i++) {
							if(i == 1){
								NumberTuple ntup = new NumberTuple();
								ntup.setValue(Integer.parseInt(tokens[i]));
								linetos.add(ntup);
								continue;
							}
							Tuple<String> tup = new StringTuple();
							tup.setValue(tokens[i]);
							linetos.add(tup);
						}
						result.add(linetos);
					}				
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
