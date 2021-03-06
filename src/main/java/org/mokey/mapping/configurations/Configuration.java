package org.mokey.mapping.configurations;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Configuration {
	
	private static Logger log = Logger.getLogger("Configuration");
	
	private Weight[] weights;
	private double maxValue;
	private String filePath = "";
	
	public Configuration(String filePath){
		this.filePath = filePath;
		try {
			this.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void load() throws Exception{
		File file = new File(this.filePath);
		if(!file.exists() || file.isDirectory())
			log.error(String.format(
					"The configuration path[%s] is not correct", this.filePath));
		SAXReader reader = new SAXReader();
		Document doc = reader.read(this.filePath);
		Node node = doc.selectSingleNode("configuration/maxvalue");
		this.maxValue = Double.parseDouble(node.getText());
		
		List<?> cweights = doc.selectNodes("configuration/weights/weight");
		
		this.weights = new Weight[cweights.size()];
		log.info("The current max value is: " + this.maxValue);
		
		Iterator<?> it = cweights.iterator();
		String index, name, type, value;
		int i = 0;
		while(it.hasNext()){
			Element elm=(Element)it.next();
			index = elm.attributeValue("index");
			i = Integer.parseInt(index);
			if(i < 0 || i >= this.weights.length)
				throw new Exception("The index setting is not correct");
			name = elm.attributeValue("name").trim();
			type = elm.attributeValue("type").trim();
			value = elm.attributeValue("value").trim();
			this.weights[i] = new Weight(name, type, value);
			
			log.info(String.format("The weight[%s:%s:%s:%s] has bean added", index, name, type, value));
		}
	}
	
	public double getMaxValue(){
		return this.maxValue;
	}
	
	//TODO: Fresh the new data to configuration file
	public void setMaxValue(double val){
		this.maxValue = val;
	}
	
	public Weight getWeight(int index){
		return this.weights[index];
	}
	
	//TODO: Fresh the new data to configuration file
	public void setWeights(Weight[] weights){
		this.weights = weights;
	}
	
	public int tupleCount(){
		return this.weights.length;
	}
}
