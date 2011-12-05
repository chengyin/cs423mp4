package control.transfer;

import java.io.IOException;
import java.io.OptionalDataException;

import android.util.Log;

import task.JobQueue;
import task.matrix.MatrixJob;
import channel.Channel;

public class ClientTransferHandler {

    private JobQueue<MatrixJob> localJobQueue;
    private Channel channel;

    /**
     * Listens and responds to transfer request
     * 
     * @return 0 if success -1 if stop
     */
    private int handleRequest() {
	String[] message = null;
	int n = 0;
	try {
	    message = channel.getMessage().split(" ");
	    n = Integer.parseInt(message[0]);
	} catch (IOException e) {
	    Log.e("423-server", e.toString());
	}

	if (message == null)
	    return -1;

	// Repond to server sending data
	if (message[1].equals("S")) {
	    for (int i = 0; i < n; i++) {
		Object obj = null;
		try {
		    obj = channel.getObject();
		} catch (OptionalDataException e) {
		    Log.e("423-server", e.toString());
		} catch (ClassNotFoundException e) {
		    Log.e("423-server", e.toString());
		} catch (IOException e) {
		    Log.e("423-server", e.toString());
		}
		// Break if dummy element is sent
		if (!(obj instanceof MatrixJob))
		    break;
		else
		    try {
			localJobQueue.enqueue((MatrixJob) obj);
		    } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
	    }
	} else if (message[1].equals("R")) {
	    // Receiving is symmetric
	    for (int i = 0; i < n; i++) {
		try {
		    if (!localJobQueue.isEmpty())
			channel.sendObject(localJobQueue.dequeue());
		    else {
			// Send dummy and break if empty
			channel.sendObject(new Dummy());
			break;
		    }
		} catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
	    }
	}

	return 0;
    }

    /**
     * Initialize fields and run main thread
     * 
     * @param localJobQueue
     * @param channel
     */
    public ClientTransferHandler(JobQueue<MatrixJob> localJobQueue,
	    Channel channel) {
	this.localJobQueue = localJobQueue;
	this.channel = channel;

	new Thread(new Runnable() {
	    public void run() {
		// Handle requests till no longer necessary
		while (handleRequest() == 0)
		    ;
	    }
	}).start();
    }
}
