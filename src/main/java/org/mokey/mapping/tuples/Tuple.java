package org.mokey.mapping.tuples;

public abstract class Tuple<T> {
	
	protected T value;
	
	/**
	 * Calculate the difference between two tuples
	 * @param tuple
	 * 	The compared tuple 
	 * @return
	 * 	value(0~1) indicate the difference.
	 */
	public abstract double diff(Tuple<?> tuple);
	
	public T getValue(){
		return this.value;
	}
	
	public void setValue(T value){
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value.toString();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object obj) {
		Tuple<T> that = (Tuple<T>)obj;
		return this.getValue().equals(that.getValue());
	}
}
