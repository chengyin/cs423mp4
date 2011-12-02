package control;

import task.Worker;
import task.matrix.MatrixJobQueue;

public class Adaptor {
    private HardwareMonitor monitor;
    private StateManager stateManager;
    private MatrixJobQueue localJobQueue;
    private TransferManager transferManager;

    public Adaptor(HardwareMonitor monitor, StateManager stateManager,
	    Worker worker, TransferManager transferManager) {
	this.monitor = monitor;
	this.stateManager = stateManager;
	this.localJobQueue = localJobQueue;
	this.transferManager = transferManager;
    }
}
