package com.oberamsystems.putinmon.loic;

import java.net.InetAddress;

public class Loic {
	
	private String IpAddress = "";
	
	public Loic(String Ipv4Address, String Ipv6Address) {
		
		if (Ipv6Address.isEmpty()) {
			this.setIpAddress(Ipv4Address);
		} else {
			this.setIpAddress(Ipv6Address);
		}
	}

	public String getIpAddress() {
		return this.IpAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.IpAddress = ipAddress;
	}
	
	public boolean simpleCheckOnline() {
		
		boolean reachable = false;
		
		try{
        	InetAddress address = InetAddress.getByName(IpAddress);
            reachable = address.isReachable(3000);
        } catch (Exception e){
            e.printStackTrace();
        }
		
		return reachable;
	}
	
public boolean checkOnlineBool() {
		
		boolean reachable = false;
		
		try{
        	InetAddress address = InetAddress.getByName(IpAddress);
            reachable = address.isReachable(3000);
        } catch (Exception e){
           System.out.println("Invalid address!");
        }

		return reachable;
	}

	public String checkOnline() {
		boolean reachable = checkOnlineBool();
		return reachable ? "online" : "offline";
	}
}
