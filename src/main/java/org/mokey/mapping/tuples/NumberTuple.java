package org.mokey.mapping.tuples;

import org.mokey.mapping.utils.Helper;

public class NumberTuple extends Tuple<Number> {
	public double diff(Tuple<?> tuple) {
		if(this.value == null || tuple.getValue() == null ||
				!(tuple instanceof NumberTuple))
			return Double.MAX_VALUE;
		NumberTuple that = (NumberTuple)tuple;
		double x = Math.abs(this.getValue().doubleValue() - that.getValue().doubleValue());
		return Helper.y(x);
	}
	
	public static void main(String[] args){
		NumberTuple p1 = new NumberTuple();
		p1.setValue(10);
		
		NumberTuple p2 = new NumberTuple();
		p2.setValue(11);
		
		NumberTuple p3 = new NumberTuple();
		p3.setValue(100);
		
		NumberTuple p4 = new NumberTuple();
		p4.setValue(10);
		
		System.out.println(p1.diff(p2));
		System.out.println(p1.diff(p3));
		System.out.println(p1.diff(p4));
	}
}
