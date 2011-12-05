package control.state;

import java.io.Serializable;

import control.HardwareMonitor;

/**
 * Representation of state that includes hardware stats
 * and number of elements in job queue
 * 
 */
public class State implements Serializable {

    private static final long serialVersionUID = 5303794796773215617L;
    private int jobQueueRemaining;
    private double cpuUsage;
    private double throttle;
    private transient HardwareMonitor hwMonitor;

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

    public double getCpuUsage() {
	return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
	this.cpuUsage = cpuUsage;
    }

    public double getThrottle() {
	return throttle;
    }

    public void setThrottle(double throttle) {
	this.throttle = throttle;
    }
}
