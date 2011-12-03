package runner.matrix;

import java.io.IOException;
import java.io.OptionalDataException;

import android.util.Log;

import matrix.Matrix;
import runner.ClientRunner;
import task.matrix.MatrixJobQueue;
import task.matrix.MatrixResults;
import task.matrix.MatrixWorker;
import channel.Client;

public class MatrixClientRunner extends ClientRunner {
    Client channel;
    MatrixWorker worker;
    MatrixResults results;
    Matrix matrix1;
    Matrix matrix2;

    public MatrixClientRunner(String serverIP, int serverPort) {
	super(serverIP, serverPort); // Including connecting to server
	Log.e("423-client", "connected to the server " + serverIP + ":" + serverPort);
	
	this.results = new MatrixResults();
	this.worker = new MatrixWorker(0, this.hwMonitor, this.results);
    }

    public void run() {
	this.getMatrixFromServer();
	Log.e("423-client", "got the matrix from server");
	this.processWork();
	Log.e("423-client", "finished working");
	this.sendResultsToServer();
	Log.e("423-client", "sent work back to the server");
    }

    private void getMatrixFromServer() {
	try {
	    this.matrix1 = (Matrix) this.channel.getObject();
	    this.matrix2 = (Matrix) this.channel.getObject();
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
    }

    public void processWork() {
	this.worker.processJobsWithThrottling(MatrixJobQueue
		.generateJobQueueWithMatrixs(matrix1, matrix2));
    }

    private void sendResultsToServer() {
	try {
	    this.channel.sendObject(this.results);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
