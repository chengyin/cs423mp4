package task;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;

public class JobQueue<J extends Job> {
    LinkedBlockingQueue<J> jobs = null;
    
    public JobQueue() {
	super();
	this.jobs = new LinkedBlockingQueue<J>();
    }
    
    public JobQueue(Collection<J> jobs) {
	super();
	this.jobs = new LinkedBlockingQueue<J>(jobs);
    }
    
    /**
     * Add a job to the tail of the queue. If the queue is full, it will block
     * 
     * @param job
     *            new job to be added to the tail of the queue
     * @throws InterruptedException
     */
    public void enqueue(J job) throws InterruptedException {
	this.jobs.put(job);
    }

    /**
     * Get the job at the head of the queue and remove it from the queue.
     * 
     * @return job at the head
     */
    public J dequeue() {
	return this.jobs.poll();
    }

    /**
     * Get the count of remaining jobs in the queue
     * @return count of the remaining jobs in the queue
     */
    public int jobCount() {
	return this.jobs.size();
    }

}