package control.state;

import java.io.IOException;
import java.io.OptionalDataException;
import java.util.concurrent.locks.LockSupport;

import android.util.Log;

import task.matrix.MatrixJobQueue;
import channel.Server;
import control.Adaptor;
import control.HardwareMonitor;

/**
 * State manager; receives state every second
 * 
 */
public class StateManager extends AbstractStateHandler<Server> {
    private static final int SLEEPTIME = 10;
    private State remoteState;
    private Adaptor adaptor;

    Runnable socketListener = new Runnable() {
	public void run() {
	    try {
		channel.listen();
	    } catch (IOException e1) {
		Log.e("423-server", e1.toString());
	    }

	    while (channel.isConnected()) {
		// Request for information
		try {
		    channel.sendMessage("R");
		} catch (IOException e) {
		    Log.e("423-server", e.toString());
		}

		try {
		    remoteState = (State) channel.getObject();
		} catch (OptionalDataException e) {
		    Log.e("423-server", e.toString());
		} catch (ClassNotFoundException e) {
		    Log.e("423-server", e.toString());
		} catch (IOException e) {
		    Log.e("423-server", e.toString());
		}

		// Run load balancer
		if (adaptor != null) {
		    adaptor.compute();
		}

		// Sleep
		LockSupport.parkNanos((long) SLEEPTIME * 10000000);
	    }
	}
    };

    /**
     * Initialize fields and run main thread
     * 
     * @param jobQueue
     * @param hwMonitor
     * @param server
     */
    public StateManager(MatrixJobQueue jobQueue, HardwareMonitor hwMonitor,
	    Server server) {
	super(jobQueue, hwMonitor, server);

	new Thread(socketListener).start();
    }

    public State getLocalState() {
	return getState();
    }

    public State getRemoteState() {
	return remoteState;
    }

    /**
     * To run load balancer
     * 
     * @param adaptor
     */
    public void setAdaptor(Adaptor adaptor) {
	this.adaptor = adaptor;
    }
}
