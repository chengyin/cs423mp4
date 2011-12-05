package control;

import java.io.IOException;
import java.util.concurrent.locks.LockSupport;

import android.util.Log;

import task.matrix.MatrixJobQueue;
import channel.Channel;
import channel.Server;

public class StateManager {
    private MatrixJobQueue jobQueue;
    private HardwareMonitor hwMonitor;
    private State state;
    private Server server;
    private int sleepTime;

    Runnable socketListener = new Runnable() {
	public void sendCurrentState() {
	    state.setJobQueueRemaining(jobQueue.jobCount());
	    try {
		server.sendObject(state);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	public void run() {
	    try {
		server.listen();
	    } catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	    
	    Log.e("423-server", "State manager connected");
	    
	    try {
		server.sendMessage("Foo");
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	    // Sleep
	    LockSupport.parkNanos((long) sleepTime * 10000000);
	}

    };

    public StateManager(MatrixJobQueue jobQueue, HardwareMonitor hwMonitor,
	    Server server, int sleepTime) {
	super();
	this.jobQueue = jobQueue;
	this.hwMonitor = hwMonitor;
	this.server = server;
	this.state = new State(0, this.hwMonitor);
	this.sleepTime = sleepTime;

	new Thread(socketListener).start();
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
