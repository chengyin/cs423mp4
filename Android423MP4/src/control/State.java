package control;

import java.io.Serializable;

public class State implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 5303794796773215617L;
    private int jobQueueRemaining;
    private HardwareMonitor hwMonitor;

    public State(int jobQueueRemaining, HardwareMonitor hwMonitor) {
	super();
	this.jobQueueRemaining = jobQueueRemaining;
	this.hwMonitor = hwMonitor;
    }

    public int getJobQueueRemaining() {
	return jobQueueRemaining;
    }

    public HardwareMonitor getHwMonitor() {
	return hwMonitor;
    }

    public void setJobQueueRemaining(int jobQueueRemaining) {
        this.jobQueueRemaining = jobQueueRemaining;
    }
}
