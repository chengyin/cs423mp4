package control;

import task.Job;
import task.JobQueue;
import android.widget.Adapter;

public class TransferManager {

    private JobQueue localJobQueue;

    public TransferManager(Adapter adapter, JobQueue localJobQueue) {
	this.localJobQueue = localJobQueue;
    }

    public void enqueueRemoteJob(Job job) {
	localJobQueue.enqueue(job);
    }
}
