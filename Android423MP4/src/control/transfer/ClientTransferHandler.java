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

    int handleRequest() {
	String[] message = null;
	int n = 0;
	try {
	    message = channel.getMessage().split(" ");
	    n = Integer.parseInt(message[0]);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	if (message == null)
	    return -1;

	if (message[1].equals("S")) {
	    for (int i = 0; i < n; i++) {
		Object obj = null;
		try {
		    obj = channel.getObject();
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
	    for (int i = 0; i < n; i++) {
		try {
		    if (!localJobQueue.isEmpty())
			channel.sendObject(localJobQueue.dequeue());
		    else {
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

    public ClientTransferHandler(JobQueue<MatrixJob> localJobQueue,
	    Channel channel) {
	this.localJobQueue = localJobQueue;
	this.channel = channel;

	new Thread(new Runnable() {
	    public void run() {
		while (handleRequest() == 0)
		    ;
	    }
	}).start();
    }
}
