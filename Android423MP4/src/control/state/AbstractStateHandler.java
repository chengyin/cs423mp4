package control.state;

import java.io.IOException;
import java.io.OptionalDataException;

import control.HardwareMonitor;

import task.matrix.MatrixJobQueue;
import channel.Channel;
import channel.Server;

public abstract class AbstractStateHandler<C extends Channel> {
    protected MatrixJobQueue jobQueue;
    protected HardwareMonitor hwMonitor;
    protected C channel;
    protected State state;
    
    public State getState() {
	state.setJobQueueRemaining(jobQueue.jobCount());
	return state;
    }

    public AbstractStateHandler(MatrixJobQueue jobQueue, HardwareMonitor hwMonitor,
	    C channel) {
	this.jobQueue = jobQueue;
	this.hwMonitor = hwMonitor;
	this.channel = channel;
	this.state = new State(0, this.hwMonitor);
    }
}