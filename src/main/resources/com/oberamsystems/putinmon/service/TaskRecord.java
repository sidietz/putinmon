package com.oberamsystems.putinmon.service;

public class TaskRecord {
	
	private int row;
	private String Ipv4Address;
	private String Ipv6Address;
	private String result;
	
	public TaskRecord(int row, String Ipv4Address, String Ipv6Address) {
		this.row = row;
		this.Ipv4Address = Ipv4Address;
		this.Ipv6Address = Ipv6Address;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getRow() {
		return row;
	}

	public String getIpv6Address() {
		return Ipv6Address;
	}

	public String getIpv4Address() {
		return Ipv4Address;
	}

	public String toString() {
		return "" + this.row + " - IPv4: " + Ipv4Address + " is " + this.result;
	}
}
