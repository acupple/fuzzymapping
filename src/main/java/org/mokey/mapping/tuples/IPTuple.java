package org.mokey.mapping.tuples;

import java.net.InetAddress;

import org.mokey.mapping.utils.Helper;

public class IPTuple extends Tuple<String> {

	@Override
	public double diff(Tuple<?> tuple) {
		if(this.value == null || tuple.getValue() == null ||
				!(tuple instanceof IPTuple)){
			return Double.MAX_VALUE;
		}
		IPTuple that = (IPTuple) tuple;
		double x = Math.abs(this.ipToInt(this.getValue()) - this.ipToInt(that.getValue()));
		return Helper.y(x);
	}

	public int ipToInt(String ipAddr) {
		try {
			return bytesToInt(ipToBytesByInet(ipAddr));
		} catch (Exception e) {
			throw new IllegalArgumentException(ipAddr + " is invalid IP");
		}
	}

	public int bytesToInt(byte[] bytes) {
		int addr = bytes[3] & 0xFF;
		addr |= ((bytes[2] << 8) & 0xFF00);
		addr |= ((bytes[1] << 16) & 0xFF0000);
		addr |= ((bytes[0] << 24) & 0xFF000000);
		return addr;
	}

	public byte[] ipToBytesByInet(String ipAddr) {
		try {
			return InetAddress.getByName(ipAddr).getAddress();
		} catch (Exception e) {
			throw new IllegalArgumentException(ipAddr + " is invalid IP");
		}
	}
	
	public static void main(String[] args){
		IPTuple p1 = new IPTuple();
		p1.setValue("192.168.1.255");
		
		IPTuple p2 = new IPTuple();
		p2.setValue("192.168.1.1");
		
		IPTuple p3 = new IPTuple();
		p3.setValue("192.167.1.1");
		
		System.out.println(p1.diff(p3));
		System.out.println(p1.diff(p2));
	}
}
