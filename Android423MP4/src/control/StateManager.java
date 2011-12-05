package control;

import java.io.IOException;
import java.io.OptionalDataException;
import java.util.concurrent.locks.LockSupport;

import android.util.Log;

import task.matrix.MatrixJobQueue;
import channel.Channel;
import channel.Server;

public class StateManager extends AbstractStateHandler<Server> {
    private int sleepTime;
    private State remoteState;

    Runnable socketListener = new Runnable() {
	public void run() {
	    Log.e("423-server", "State manager connected");
	    try {
		channel.listen();
	    } catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }

	    while (true) {
		// Request for information
		try {
		    channel.sendMessage("R");
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}

		try {
		    remoteState = (State) channel.getObject();		
		} catch (OptionalDataException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (ClassNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}

		// Sleep
		LockSupport.parkNanos((long) sleepTime * 10000000);
	    }
	}
    };

    public StateManager(MatrixJobQueue jobQueue, HardwareMonitor hwMonitor,
	    Server server, int sleepTime) {
	super(jobQueue, hwMonitor, server);
	this.sleepTime = sleepTime;

	new Thread(socketListener).start();
    }

    public State getLocalState() {
	return getState();
    }

    public State getRemoteState() {
	return remoteState;
    }
}
