package control.transfer;

import java.io.IOException;
import java.io.OptionalDataException;

import android.util.Log;

import channel.Channel;
import task.JobQueue;
import task.matrix.MatrixJob;

public class TransferManager {

    private JobQueue<MatrixJob> localJobQueue;
    private Channel channel;

    public void sendJobs(int n) {
	try {
	    channel.sendMessage(n + " S");
	} catch (IOException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	for (int i = 0; i < n; i++) {
	    try {
		if (!localJobQueue.isEmpty()) {
		    channel.sendObject(localJobQueue.dequeue());
		}
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

    public void receiveJobs(int n) {
	try {
	    channel.sendMessage(n + " R");
	} catch (IOException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	for (int i = 0; i < n; i++)
	    try {
		Object obj = channel.getObject();
		if (obj instanceof MatrixJob)
		    localJobQueue.enqueue((MatrixJob)obj);
		else
		    break;
	    } catch (OptionalDataException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
    }
    
    public TransferManager(JobQueue<MatrixJob> localJobQueue, Channel channel) {
	this.localJobQueue = localJobQueue;
	this.channel = channel;
    }
}
