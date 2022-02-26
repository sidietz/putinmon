package com.oberamsystems.putinmon.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Runner {

	private static final int NUM_OF_THREADS = 16;
	private static final Map<Integer, ThreadWorker> objectMap = new HashMap<>();

	private LinkedBlockingQueue<TaskRecord> workQueue;
	private LinkedBlockingQueue<TaskRecord> resultQueue;
	private ExecutorService executor;

	public Runner(LinkedBlockingQueue<TaskRecord> workQueue, LinkedBlockingQueue<TaskRecord> resultQueue) {
		this.resultQueue = resultQueue;
		this.workQueue = workQueue;
		this.executor = Executors.newFixedThreadPool(NUM_OF_THREADS);
	}

	public void run() {
		for (int i = 0; i < NUM_OF_THREADS; i++) {
			ThreadWorker thread = new ThreadWorker(workQueue, resultQueue);
			objectMap.put(i, thread);
			this.executor.execute(thread);
		}
	}
}
