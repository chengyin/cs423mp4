package control;

import java.io.IOException;

import channel.Channel;
import task.Job;
import task.JobQueue;
import android.util.Log;
import android.widget.Adapter;

public class TransferManager {

    private JobQueue<Job> localJobQueue;
    private Channel channel;

    Runnable inputListener = new Runnable() {
	public void run() {
	    while (true) {
		try {
		    String message = channel.getMessage();
		    if (message != null)
			Log.e("", message);
		} catch (IOException e) {
		}
	    }
	}
    };

    public TransferManager(JobQueue<Job> localJobQueue, Channel channel) {
	this.localJobQueue = localJobQueue;
	this.channel = channel;
		
	new Thread(inputListener).start();
    }
    
    public void sendRequest(String message) {
	try {
	    channel.sendMessage(message);
	} catch (IOException e) {
	}
    }

}
