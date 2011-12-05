package task;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A wrapper around a thread safe queue
 *
 * @param <J> Type of job
 */
public class JobQueue<J extends Job> {
    LinkedBlockingQueue<J> jobs = null;
    
    public JobQueue() {
	super();
	jobs = new LinkedBlockingQueue<J>();
    }
    
    public JobQueue(Collection<J> jobs) {
	super();
	jobs = new LinkedBlockingQueue<J>(jobs);
    }
    
    /**
     * Add a job to the tail of the queue. If the queue is full, it will block
     * 
     * @param job
     *            new job to be added to the tail of the queue
     * @throws InterruptedException
     */
    public void enqueue(J job) throws InterruptedException {
	jobs.put(job);
    }

    /**
     * Get the job at the head of the queue and remove it from the queue.
     * 
     * @return job at the head
     */
    public J dequeue() {
	return jobs.poll();
    }
    
    /**
     * Delete Jobs if we ask the remote node to do more jobs.
     */
    public void deljob(J job) {
	jobs.remove(job);
    }

    /**
     * Get the count of remaining jobs in the queue
     * @return count of the remaining jobs in the queue
     */
    public int jobCount() {
	return jobs.size();
    }
    
    /**
     * 
     * @return is queue empty?
     */
    public boolean isEmpty() {
	return jobs.isEmpty();
    }
}