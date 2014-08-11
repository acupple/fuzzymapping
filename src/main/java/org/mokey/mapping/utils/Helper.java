package org.mokey.mapping.utils;

public class Helper {
	
	private static double n = Math.PI / 180;
	
	public static double y(double x){
		return 1.0 / (1 + Math.pow(Math.E, -x));
	}
	
	public static void main(String[] args){
		for(int i = 0 ; i < 100; i++){
			System.out.println(y(i));
		}
		
		System.out.println(y(100000));
	}
}
