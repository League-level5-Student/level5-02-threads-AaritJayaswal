package _04_Thread_Pool;

import java.lang.Thread.State;
import java.util.ArrayDeque;

public class WorkQueue implements Runnable {

	private ArrayDeque<Job> jobQueue = new ArrayDeque<Job>();
	private Thread threads[];
	private volatile boolean isRunning = true;

	public WorkQueue() {
		int totalThreads = Runtime.getRuntime().availableProcessors() - 1;
		threads = new Thread[totalThreads];

		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(this);

			threads[i].start();

		}

	}

	public int getThreadCount() {
		return threads.length;
	}

	public void addJob(Job j) {
		synchronized (jobQueue) {
			jobQueue.add(j);
			jobQueue.notify();
		}

	}

	public boolean performJob() {
		Job j = null;
		synchronized (jobQueue) {
			if (!jobQueue.isEmpty()) {
				j = jobQueue.remove();
			}
		}
		if (j != null) {
			j.perform();
			return true;
		}

		else {
			return false;
		}
	}
	
	public void completeAllJobs() {
		while(!jobQueue.isEmpty()) {
			performJob();
		}
		
		for(int i = 0; i<threads.length; i++) {
			if(threads[i].getState()!= State.WAITING) {
				i =-1;
				
			}
		}
	}

	@Override
	public void run() {
		while (isRunning) {
			if(!performJob()) {
			System.out.println("Output from thread #" + Thread.currentThread().getId());
			synchronized (jobQueue) {
				try {
					jobQueue.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

	}
	}

	public void shutdown() {
		completeAllJobs();
		isRunning = false;
		synchronized (jobQueue) {
			jobQueue.notifyAll();
		}
	}

}

//continue page 47
