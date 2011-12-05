package runner.matrix;

import java.io.IOException;
import java.io.OptionalDataException;
import java.net.UnknownHostException;

import control.state.ClientStateHandler;
import control.transfer.ClientTransferHandler;

import channel.Client;

import matrix.Matrix;
import runner.ClientRunner;
import task.matrix.MatrixJobQueue;
import task.matrix.MatrixResults;
import task.matrix.MatrixWorker;
import android.util.Log;

/**
 * Runner specific to matrix client
 * 
 */
public class MatrixClientRunner extends ClientRunner {
    private MatrixWorker worker;
    private MatrixResults results;
    private Matrix matrix1;
    private Matrix matrix2;
    private MatrixJobQueue jobQueue;

    public MatrixClientRunner(String serverIP, int serverPort) {
	super(serverIP, serverPort); // Including connecting to server
	Log.e("423-client", "connected to the server " + serverIP + ":"
		+ serverPort);

	this.results = new MatrixResults();
	this.worker = new MatrixWorker(0, this.hwMonitor, this.results);
    }

    /**
     * Main runner for the server
     */
    public void run() {
	getMatrixFromServer();
	Log.e("423-client", "BOOTSTRAP PHASE: got the matrix from server");
	initJobQueue();
	Log.e("423-client", "initialized job queue");
	initClientStateHandler();
	Log.e("423-client", "initialized client state handler");
	initTransferHandler();
	Log.e("423-client", "initialized client transfer handler");
	processWork();
	Log.e("423-client", "finished working");
	sendResultsToServer();
	Log.e("423-client", "AGGREGATION PHASE: sent work back to the server");
    }

    /**
     * Receive bottom half of matrix
     */
    private void getMatrixFromServer() {
	try {

	    matrix1 = (Matrix) this.getChannel().getObject();
	    Log.e("423-client", "got matrix1");

	    matrix2 = (Matrix) this.getChannel().getObject();
	    Log.e("423-client", "got matrix2");

	} catch (OptionalDataException e) {
	    Log.e("423-client", e.toString());
	} catch (ClassNotFoundException e) {
	    Log.e("423-client", e.toString());
	} catch (IOException e) {
	    Log.e("423-client", e.toString());
	}
    }

    /**
     * Make job queue
     */
    public void initJobQueue() {
	jobQueue = MatrixJobQueue.generateJobQueueWithMatrixs(matrix1, matrix2);
    }

    /**
     * Initialize state hander with new channel. Make the state channel's port
     * one larger than transfer channel's port
     */
    public void initClientStateHandler() {
	try {
	    stateChannel = new Client(serverIP, serverPort + 1);
	} catch (UnknownHostException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	clientStateHandler = new ClientStateHandler(jobQueue, hwMonitor,
		stateChannel);
    }

    /**
     * Make transfer handler
     */
    public void initTransferHandler() {
	transferHandler = new ClientTransferHandler(jobQueue, channel);
    }

    /**
     * Start worker
     */
    public void processWork() {
	worker.processJobsWithThrottling(jobQueue);
    }

    /**
     * Aggregation phase
     */
    private void sendResultsToServer() {
	try {
	    this.getChannel().sendObject(this.results);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
