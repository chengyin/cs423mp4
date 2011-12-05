package control;

import java.io.IOException;
import java.io.OptionalDataException;

import channel.Channel;
import task.Job;
import task.JobQueue;
import android.util.Log;

public class TransferManager {

    private JobQueue<Job> localJobQueue;
    private Channel channel;

    Runnable inputListener = new Runnable() {
	public void run() {
	    while (true) {
		try {
		    String message = (String) channel.getObject();
		    if (message != null)
		    {
			
			
		    }
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		} 
	    }
	}
    };

    public TransferManager(JobQueue<Job> localJobQueue, Channel channel) {
	this.localJobQueue = localJobQueue;
	this.channel = channel;
		
	new Thread(inputListener).start();
    }
    public void TransferJobs(String message) {
	if (message != null)
	    {
		Log.e("cs423 server", message);
		String[] temp = message.split(" ");
		//Decrease Jobs
		if(temp[0]=="0")
		{
		    for(int i=1;i<temp.length;i=i+2)
		    {
			Job job = new Job();
			job.x = temp[i];
			job.y = temp[i+1];
			localJobQueue.deljob(job);
		    }
		    
		}
		//Increase Jobs
		else if(temp[0]=="1")
		{
		    for(int i=1;i<temp.length;i=i+2)
		    {
			Job job = new Job();
			job.x = temp[i];
			job.y = temp[i+1];
			try {
			    localJobQueue.enqueue(job);
			} catch (InterruptedException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
		    }
		}
		
	    }
    }
    public void sendRequest(String message) {
	try {
	    channel.sendMessage(message);
	} catch (IOException e) {
	}
    }
    public String receiveRequest() {
	   try {
	    return (String) channel.getObject();
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
	return null;

    }

}
