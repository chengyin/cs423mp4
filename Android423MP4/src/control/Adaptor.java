package control;

import control.state.StateManager;
import control.transfer.TransferManager;

/**
 * Adaptor responsible for running load-balancing algorithm
 * 
 */
public class Adaptor {
    private StateManager stateManager;
    private TransferManager transferManager;

    /**
     * Initialize and make state manager dispatch
     * 
     * @param stateManager
     * @param transferManager
     */
    public Adaptor(StateManager stateManager, TransferManager transferManager) {
	this.stateManager = stateManager;
	this.transferManager = transferManager;
	
	// To signal the algorithm to run
	stateManager.setAdaptor(this);
    }

    /**
     * Perform load balancing when state manager signals
     */
    public void compute() {
	HardwareMonitor remoteHwMonitor = stateManager.getRemoteState()
		.getHwMonitor();
	HardwareMonitor localHwMonitor = stateManager.getLocalState()
		.getHwMonitor();

	double remoteUsage = remoteHwMonitor.getCPUUsage();
	double localUsage = localHwMonitor.getCPUUsage();

	int remoteJobs = stateManager.getRemoteState().getJobQueueRemaining();
	int localJobs = stateManager.getLocalState().getJobQueueRemaining();

	if (localJobs != 0 && remoteJobs != 0) {
	    // weight usage w.r.t. jobs
	    double balance = (localUsage * localJobs - remoteUsage * remoteJobs);

	    if (balance > 0)
		transferManager.sendJobs((int) (balance * localJobs));
	    else
		transferManager.receiveJobs((int) (balance * remoteJobs));
	}
    }
}
