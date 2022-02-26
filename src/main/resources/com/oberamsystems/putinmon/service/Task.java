package com.oberamsystems.putinmon.service;

import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JTable;

public class Task implements Runnable {

	private JTable table;
	LinkedBlockingQueue<TaskRecord> workQueue;

	public Task(JTable table, LinkedBlockingQueue<TaskRecord> workQueue) {
		this.table = table;
		this.workQueue = workQueue;
	}

	@Override
	public void run() {

		TaskRecord tr;
		String Ipv4Address = "";
		String Ipv6Address = "";

		for (int i = 0; i < table.getRowCount(); i++) {
			Ipv4Address = (String) table.getValueAt(i, 1);
			Ipv6Address = (String) table.getValueAt(i, 2);
			tr = new TaskRecord(i, Ipv4Address, Ipv6Address);
			workQueue.add(tr);
		}
	}

}
