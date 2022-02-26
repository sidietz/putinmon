package com.oberamsystems.putinmon.service;

import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JTable;

public class Consumer implements Runnable {

	private JTable table;
	private LinkedBlockingQueue<TaskRecord> workQueue;
	private LinkedBlockingQueue<TaskRecord> resultQueue;

	public Consumer(JTable table, LinkedBlockingQueue<TaskRecord> workQueue,
			LinkedBlockingQueue<TaskRecord> resultQueue) {
		this.table = table;
		this.workQueue = workQueue;
		this.resultQueue = resultQueue;
	}

	public void consume() {
		Runner runner = new Runner(workQueue, resultQueue);
		runner.run();

		System.out.println("pre while loop");

		while (true) {
			try {
				TaskRecord tr = resultQueue.take();
				System.out.println(tr.toString());

				String result = tr.getResult();
				table.setValueAt(result, tr.getRow(), 3);
			} catch (InterruptedException e) {
				e.printStackTrace();
				// break;
			}
		}
	}

	@Override
	public void run() {
		consume();
	}

}
