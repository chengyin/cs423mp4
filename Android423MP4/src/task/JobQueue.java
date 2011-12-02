package task;

import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;

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
    
    public void enqueue(Job job) {
	try {
	    this.jobs.put(job);
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    public Job dequeue() {
	return this.jobs.poll();
    }
}
