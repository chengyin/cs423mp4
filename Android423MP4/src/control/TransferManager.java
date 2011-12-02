package control;

import task.matrix.MatrixJob;
import task.matrix.MatrixJobQueue;
import android.widget.Adapter;

public class TransferManager {

    private MatrixJobQueue localJobQueue;

    public TransferManager(Adapter adapter, MatrixJobQueue localJobQueue) {
	this.localJobQueue = localJobQueue;
    }

    public void enqueueRemoteJob(MatrixJob job) {
	localJobQueue.enqueue(job);
    }
}
