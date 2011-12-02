package task;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Queue for job. It has a static capacity.
 * 
 * @author chengyin
 *
 */
public class JobQueue {
    protected LinkedBlockingQueue<Job> jobs; // Linked-list based
    
    /**
     * Create a JobQueue with an empty queue
     */
    public JobQueue() {
	super();
	jobs = new LinkedBlockingQueue<Job>();
    }
    
    /**
     * Create a JobQueue with a collection of initial jobs
     * @param jobs
     */
    public JobQueue(Collection<Job> jobs) {
	super();
	this.jobs = new LinkedBlockingQueue<Job>(jobs);	
    }
    
    /**
     * Add a job to the tail of the queue. If the queue is full, it will block
     * @param job new job to be added to the tail of the queue
     */
    public void enqueue(Job job) {
	try {
	    this.jobs.put(job);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    /**
     * Get the job at the head of the queue and remove it from the queue.
     * 
     * @return job at the head
     */
    public Job dequeue() {
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
