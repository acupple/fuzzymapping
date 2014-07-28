package org.mokey.mapping.tuples;

public class NumberTuple extends Tuple<Number> {
	public double diff(Tuple<?> tuple) {
		if(this.value == null || tuple.getValue() == null ||
				!(tuple instanceof NumberTuple))
			return Double.MAX_VALUE;
		if(this.getValue().equals(tuple.getValue()))
			return 0;
		NumberTuple that = (NumberTuple)tuple;
		double x = Math.abs(this.getValue().doubleValue() - that.getValue().doubleValue());
		return 1.0 / (x + 1) + 1;
	}
}
