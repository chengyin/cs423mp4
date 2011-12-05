package control.transfer;

import java.io.IOException;
import java.io.OptionalDataException;

import android.util.Log;

import channel.Channel;
import task.JobQueue;
import task.matrix.MatrixJob;

/**
 * A server-side transfer initiator and performer
 * 
 */
public class TransferManager {

    private JobQueue<MatrixJob> localJobQueue;
    private Channel channel;

    /**
     * Send at most n objects to client
     * @param n Number of objects
     */
    public void sendJobs(int n) {
	try {
	    // Send n jobs to client
	    channel.sendMessage(n + " S");
	} catch (IOException e1) {
	    Log.e("423-server", e1.toString());
	}
	for (int i = 0; i < n; i++) {
	    try {
		if (!localJobQueue.isEmpty()) {
		    channel.sendObject(localJobQueue.dequeue());
		} else {
		    // Signal client to stop
		    channel.sendObject(new Dummy());
		    break;
		}
	    } catch (IOException e) {
		Log.e("423-server", e.toString());
	    }
	}
    }

    /**
     * Try and get as much as n object from the client
     * 
     * @param n Number of objects
     */
    public void receiveJobs(int n) {
	try {
	    channel.sendMessage(n + " R");
	} catch (IOException e1) {
	    Log.e("423-server", e1.toString());
	}
	for (int i = 0; i < n; i++)
	    try {
		Object obj = channel.getObject();
		if (obj instanceof MatrixJob)
		    localJobQueue.enqueue((MatrixJob) obj);
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
