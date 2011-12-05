package control.state;

import java.io.IOException;

import android.util.Log;

import control.HardwareMonitor;

import task.matrix.MatrixJobQueue;

import channel.Client;

/**
 * Listens on requests for state and sends appropriately
 *
 */
public class ClientStateHandler extends AbstractStateHandler<Client> {

    /**
     * Initialized and starts listener thread
     * 
     * @param jobQueue
     * @param hwMonitor
     * @param channel
     */
    public ClientStateHandler(MatrixJobQueue jobQueue,
	    HardwareMonitor hwMonitor, Client channel) {
	super(jobQueue, hwMonitor, channel);
	new Thread(socketListener).start();
    }

    /**
     * Helper to send state to server
     */
    private void sendCurrentState() {
	try {
	    channel.sendObject(getState());
	} catch (IOException e) {
	    Log.e("423-client", e.toString());
	}
    }

    Runnable socketListener = new Runnable() {
	public void run() {
	    while (channel.isConnected()) {
		try {
		    // "R" Means state request
		    if (channel.getMessage().equals("R"))
			sendCurrentState();
		} catch (IOException e) {
		    Log.e("423-client", e.toString());
		}
	    }
	}
    };
}
