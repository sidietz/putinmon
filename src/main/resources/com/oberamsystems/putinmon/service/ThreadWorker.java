package com.oberamsystems.putinmon.service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.oberamsystems.putinmon.loic.Loic;

public class ThreadWorker implements Runnable {
	
    private BlockingQueue<TaskRecord> workQueue;
    private BlockingQueue<TaskRecord> resultQueue;

    /**
     * Class constructor.
     *
     * @param threadName Name of the thread for identifying.
     *
     */
    public ThreadWorker(LinkedBlockingQueue<TaskRecord> workQueue, LinkedBlockingQueue<TaskRecord> resultQueue) {
        this.workQueue = workQueue;
        this.resultQueue = resultQueue;
    }

    @Override
    public void run() {

		TaskRecord item;
		
		while (true) {
			try {
				item = workQueue.take();
				Loic loic = new Loic(item.getIpv4Address(), item.getIpv6Address());
				String online = loic.checkOnline();
				item.setResult(online);
				this.resultQueue.add(item);
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}
}
