package control;

import java.io.IOException;

import control.state.StateManager;
import control.transfer.TransferManager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import task.Job;
import task.JobQueue;

public class Adaptor {
    private StateManager stateManager;
    private TransferManager transferManager;

    public Adaptor(StateManager stateManager, TransferManager transferManager) {
	this.stateManager = stateManager;
	this.transferManager = transferManager;
	stateManager.setAdaptor(this);
    }

    public void compute() {
	/*
	 * double s1 =
	 * stateManager.getRemoteState().getHwMonitor().getCPUUsage(); double s2
	 * = monitor.getCPUUsage(); double j1 =
	 * stateManager.getRemoteState().getJobQueueRemaining(); double j2 =
	 * stateManager.getLocalState().getJobQueueRemaining();
	 * 
	 * int x = (int) ((s1*j1-s2*j2)/(s1+s2));
	 * 
	 * if(Math.abs(x/j1) > 0.25) { String message = ""+x;
	 * transferManager.sendRequest(message); String control_message =
	 * transferManager.receiveRequest(); String[] inf =
	 * control_message.split(""); if(x>0)//local need to do more jobs {
	 * for(int i=0;i<inf.length;i=i+2) { Job job = new Job(); job.x =
	 * inf[i]; job.y = inf[i+1]; try { localJobQueue.enqueue(job); } catch
	 * (InterruptedException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } } } else { for(int i=0;i<inf.length;i=i+2) {
	 * Job job = new Job(); job.x = inf[i]; job.y = inf[i+1];
	 * localJobQueue.deljob(job); } } }
	 */
    }

    public Runnable inputListener = new Runnable() {
	public void run() {

	}
    };
}
