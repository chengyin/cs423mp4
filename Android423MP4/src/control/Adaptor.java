package control;

import java.io.IOException;

import android.os.Handler;
import android.os.Message;

import task.JobQueue;

public class Adaptor {
    private HardwareMonitor monitor;
    private StateManager stateManager;
    private JobQueue localJobQueue;
    private TransferManager transferManager;


    public Adaptor(HardwareMonitor monitor, StateManager stateManager,
	    JobQueue localJobQueue, TransferManager transferManager) {
	this.monitor = monitor;
	this.stateManager = stateManager;
	this.localJobQueue = localJobQueue;
	this.transferManager = transferManager;
	stateManager.setAdaptor(this);
    }
    public void Compute()
    {
	
    }
    
    public Runnable inputListener = new Runnable() {
	public void run() {

	}
    };
}
