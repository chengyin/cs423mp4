package task;

import java.util.concurrent.locks.LockSupport;

import control.HardwareMonitor;

public abstract class Worker {
    protected int id;
    protected HardwareMonitor hwMonitor;
    protected Results results;

    public Worker(int id, HardwareMonitor hwMonitor) {
	super();
	this.id = id;
	this.hwMonitor = hwMonitor;
	this.results = null;
    }

    public Worker(int id, HardwareMonitor hwMonitor, Results results) {
	super();
	this.id = id;
	this.hwMonitor = hwMonitor;
	this.results = results;
    }
    
    public abstract Result process(Job job);
    
    public Result processJob(Job job) {
	Result result = this.processJob(job);
	
	if (this.results != null) {
	    this.results.addResult(result);
	}
	
	return result;
    }

    /**
     * http://stackoverflow.com/questions/1202184/throttling-cpu-memory-usage-of
     * -a-thread-in-java
     * 
     * @param job
     * @return
     */
    public Result processJobWithThrottling(Job job) {
	long startTime = System.currentTimeMillis();
	Result result = this.processJobWithThrottling(job);
	
	// Sleep
	LockSupport.parkNanos((long) (Math.max(0,
		(System.currentTimeMillis() - startTime) * 1000000
		* hwMonitor.getThrottle())));
	
	return result;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
