package task;

import java.util.concurrent.locks.LockSupport;

import control.HardwareMonitor;

/**
 * Computation unit. Handles job and returns (or stores) result. It can be
 * throttled with HardwareMonitor
 * 
 * @author chengyin
 * 
 */
public abstract class Worker<J extends Job, R extends Result> {
    int id;
    HardwareMonitor hwMonitor;
    Results<R> results;

    /**
     * Generate a worker with id and a monitor for throttling. No storage for
     * results
     * 
     * @param id
     *            id of the worker
     * @param hwMonitor
     *            monitor for throttling
     */
    public Worker(int id, HardwareMonitor hwMonitor) {
	super();
	this.id = id;
	this.hwMonitor = hwMonitor;
	this.results = null;
    }

    /**
     * Generate a worker with id and a monitor for throttling and the storage
     * for results
     * 
     * @param id
     *            id of the worker
     * @param hwMonitor
     *            monitor for throttling
     * @param results
     *            storage for the processed results
     */
    public Worker(int id, HardwareMonitor hwMonitor, Results<R> results) {
	super();
	this.id = id;
	this.hwMonitor = hwMonitor;
	this.results = results;
    }

    /**
     * Need to be implemented, actually processing the job and return result
     * 
     * @param job
     *            job to be processed
     * @return the result of the processing
     */
    public abstract R process(J job);

    /**
     * Process job and store the result
     * 
     * @param job
     *            job to be processed
     * @return the result of the processing
     */
    public R processJob(J job) {
	R result = this.process(job);

	if (this.results != null) {
	    this.results.addResult(result);
	}

	return result;
    }

    /**
     * Process a job. Then sleep according to the throttling.
     * 
     * Reference:
     * http://stackoverflow.com/questions/1202184/throttling-cpu-memory-usage-of
     * -a-thread-in-java
     * 
     * @param job
     *            the job to be processed
     * @return result of the processing
     */
    public R processJobWithThrottling(J job) {
	long startTime = System.currentTimeMillis();
	R result = this.processJob(job);

	// Sleep
	LockSupport.parkNanos((long) (Math.max(
		0,
		(System.currentTimeMillis() - startTime) * 1000000
			* hwMonitor.getThrottle())));

	return result;
    }

    public int processJobsWithThrottling(JobQueue<J> jobQueue) {
	J job = jobQueue.dequeue();
	int count = 0;
	
	while (job != null) {
	    count++;
	    this.processJobWithThrottling(job);
	    job = jobQueue.dequeue();
	}
	
	return count;
    }

    public void setResults(Results<R> results) {
	this.results = results;
    }
}
