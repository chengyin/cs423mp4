package control;

import task.Worker;

public class Adaptor {
    private HardwareMonitor monitor;
    private StateManager stateManager;
    private Worker worker;

    public Adaptor(HardwareMonitor monitor, StateManager stateManager,
	    Worker worker) {
	this.monitor = monitor;
	this.stateManager = stateManager;
	this.worker = worker;
    }
}
