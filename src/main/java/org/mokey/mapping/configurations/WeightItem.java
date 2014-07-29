package org.mokey.mapping.configurations;

import org.mokey.mapping.enums.TupleType;

public class WeightItem {
	private String name;
	private TupleType type;
	private double weight;
	
	public WeightItem(){}
	public WeightItem(String name, String type, String weight){
		this.name = name;
		if(type.equalsIgnoreCase("number"))
			this.type = TupleType.NUMBER;
		else
			this.type = TupleType.STRING;
		this.weight = Double.parseDouble(weight);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public TupleType getType() {
		return type;
	}
	public void setType(TupleType type) {
		this.type = type;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return this.name + ":" + this.type + ":" + this.weight;
	}
}
