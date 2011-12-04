package control;

import java.io.IOException;

import task.matrix.MatrixJobQueue;
import channel.Channel;

public class StateManager {
    private MatrixJobQueue jobQueue;
    private HardwareMonitor hwMonitor;
    private State state;
    private Channel channel;
    
    Runnable socketListener = new Runnable() {
	public void sendCurrentState() {
	    state.setJobQueueRemaining(jobQueue.jobCount());
	    try {
		channel.sendObject(state);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	public void run() {
	    
	}

    };

    public StateManager(MatrixJobQueue jobQueue, HardwareMonitor hwMonitor,
	    Channel channel) {
	super();
	this.jobQueue = jobQueue;
	this.hwMonitor = hwMonitor;
	this.channel = channel;
	this.state = new State(0, this.hwMonitor);
    }

    public int getRemoteQueueSize() {
	return 0;
    }

    public double getRemoteThrottlingValue() {
	return 0.0;
    }

    public int getRemoteCPUUsage() {
	return 0;
    }
}
