package control;

import task.JobQueue;

public class Adaptor {
    private HardwareMonitor monitor;
    private StateManager stateManager;
    private JobQueue localJobQueue;
    private TransferManager transferManager;

    public Adaptor(HardwareMonitor monitor, StateManager stateManager,
	    JobQueue localJobQueue, TransferManager transferManager) {
	this.monitor = monitor;
	this.stateManager = stateManager;
	this.localJobQueue = localJobQueue;
	this.transferManager = transferManager;
    }
}
