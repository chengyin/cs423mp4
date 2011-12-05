package control;

import java.io.IOException;
import java.io.OptionalDataException;

import channel.Channel;
import task.Job;
import task.JobQueue;
import task.matrix.MatrixJob;
import android.util.Log;

public class TransferManager {

    private JobQueue<MatrixJob> localJobQueue;
    private Channel channel;

    public void sendJobs(int n) {
	try {
	    channel.sendMessage("S" + n);
	} catch (IOException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	for (int i = 0; i < n; i++)
	    try {
		channel.sendObject(localJobQueue.dequeue());
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
    }

    public void receiveJobs(int n) {
	try {
	    channel.sendMessage("R" + n);
	} catch (IOException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	for (int i = 0; i < n; i++)
	    try {
		localJobQueue.enqueue((MatrixJob)channel.getObject());
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
