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
public abstract class Worker {
    protected int id;
    protected HardwareMonitor hwMonitor;
    protected Results results;

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
    public Worker(int id, HardwareMonitor hwMonitor, Results results) {
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
    public abstract Result process(Job job);

    /**
     * Process job and store the result
     * 
     * @param job
     *            job to be processed
     * @return the result of the processing
     */
    public Result processJob(Job job) {
	Result result = this.processJob(job);

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
    public Result processJobWithThrottling(Job job) {
	long startTime = System.currentTimeMillis();
	Result result = this.processJobWithThrottling(job);

	// Sleep
	LockSupport.parkNanos((long) (Math.max(
		0,
		(System.currentTimeMillis() - startTime) * 1000000
			* hwMonitor.getThrottle())));

	return result;
    }

    public void setResults(Results results) {
	this.results = results;
    }
}
