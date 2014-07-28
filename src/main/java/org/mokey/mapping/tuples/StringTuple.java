package org.mokey.mapping.tuples;

public class StringTuple extends Tuple<String> {

	@Override
	public double diff(Tuple<?> tuple) {
		if(this.value == null || tuple.getValue() == null ||
				!(tuple instanceof StringTuple))
			return Double.MAX_VALUE;
		StringTuple that = (StringTuple) tuple;
		return this.compare(this.getValue(), that.getValue());
	}
	
	private double compare(String a, String b){
		int sames = 0;
		if(a.length() > b.length()){
			for (int i = 0; i < b.length(); i++) {
				if(b.charAt(i) != a.charAt(i))
					sames ++;
			}
		}else{
			for (int i = 0; i < a.length(); i++) {
				if(b.charAt(i) != a.charAt(i))
					sames ++;
			}
		}
		
		return -1.0 / (sames + 1) + 1;
	}
}
