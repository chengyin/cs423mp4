package control;

import java.io.IOException;
import java.util.concurrent.locks.LockSupport;

import task.matrix.MatrixJobQueue;

import channel.Channel;
import channel.Client;

import android.util.Log;

public class ClientStateHandler extends AbstractStateHandler<Client> {

    public ClientStateHandler(MatrixJobQueue jobQueue,
	    HardwareMonitor hwMonitor, Client channel) {
	super(jobQueue, hwMonitor, channel);
	new Thread(socketListener).start();
    }

    public void sendCurrentState() {
	try {
	    channel.sendObject(getState());
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    Runnable socketListener = new Runnable() {
	public void run() {
	    while (true) {
		try {
		    if (channel.getMessage().equals("R"))
			sendCurrentState();
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	}
    };
}
