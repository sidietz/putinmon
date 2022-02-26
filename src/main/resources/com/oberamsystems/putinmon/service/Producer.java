package com.oberamsystems.putinmon.service;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.swing.JTable;

public class Producer implements Runnable {

	JTable table;
	LinkedBlockingQueue<TaskRecord> workQueue;

	public Producer(JTable table, LinkedBlockingQueue<TaskRecord> workQueue) {
		this.table = table;
		this.workQueue = workQueue;
	}

	private static final int NUM_OF_THREADS = 16;

	public void produce() {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(NUM_OF_THREADS);
		Task task1 = new Task(table, workQueue);

		ScheduledFuture<?> result = executor.scheduleAtFixedRate(task1, 0, 5, TimeUnit.MINUTES);

		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executor.shutdown();
	}

	@Override
	public void run() {
		produce();
	}

}
