package control.state;

import java.io.IOException;
import java.io.OptionalDataException;
import java.util.concurrent.locks.LockSupport;

import android.os.Message;
import android.util.Log;

import task.matrix.MatrixJobQueue;
import channel.Channel;
import channel.Server;
import control.Adaptor;
import control.HardwareMonitor;

public class StateManager extends AbstractStateHandler<Server> {
    private static final int SLEEPTIME = 10;
    private State remoteState;
    private Adaptor adaptor;

    Runnable socketListener = new Runnable() {
	public void run() {
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

		if (adaptor != null) {
		    adaptor.compute();
		}

		// Sleep
		LockSupport.parkNanos((long) SLEEPTIME * 10000000);
	    }
	}
    };

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

    public void setAdaptor(Adaptor adaptor) {
	this.adaptor = adaptor;
    }
}
