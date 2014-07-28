package org.mokey.mapping;

import org.mokey.mapping.tuples.StringTuple;

public class Mapper{
	public static void main(String[] args){
		String v1 = "cbpany";
		String v2 = "compbcny";
		StringTuple tuple = new StringTuple();
		tuple.setValue(v1);
		
		StringTuple tuple2 = new StringTuple();
		tuple2.setValue(v2);
		
		System.out.println(tuple.diff(tuple2));
	}
}
