package runner;

import task.JobQueue;
import task.Worker;
import control.HardwareMonitor;

public abstract class Runner {
    protected HardwareMonitor hwMonitor = new HardwareMonitor();
    protected JobQueue jobQueue;
    protected Worker worker;
    
    protected void work() {
	while (this.jobQueue.jobCount() > 0) {
	    this.worker.processJobWithThrottling(this.jobQueue.dequeue());
	}
	
	finished();
    }
    
    protected void finished() {
	
    }
}
