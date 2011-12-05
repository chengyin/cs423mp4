package runner.matrix;

import java.io.IOException;
import java.io.OptionalDataException;

import channel.Server;

import control.Adaptor;
import control.state.StateManager;
import control.transfer.TransferManager;

import matrix.Matrix;
import runner.ServerRunner;
import task.matrix.MatrixJobQueue;
import task.matrix.MatrixResults;
import task.matrix.MatrixWorker;
import android.util.Log;

public class MatrixServerRunner extends ServerRunner {
    int VALUE = 1;
    private int row;
    private int col;
    protected MatrixWorker worker;
    private Matrix matrix1Up;
    private Matrix matrix1Down;
    private Matrix matrix2Up;
    private Matrix matrix2Down;
    private MatrixJobQueue jobQueue;
    private MatrixResults results;
    private Matrix resultMatrix;

    public MatrixServerRunner(int port, int row, int col) throws IOException {
	super(port);

	this.row = row;
	this.col = col;

	this.results = new MatrixResults();
	this.worker = new MatrixWorker(0, this.hwMonitor, this.results);
    }

    public void run() {
	generateMatrix();
	Log.e("423-server", "Matrix generated");
	initJobQueue();
	Log.e("423-server", "Job queue initialized");
	startStateManager();
	Log.e("423-server", "Initialized state manager");
	startServer();
	Log.e("423-server", "Client connected");
	sendMatrix();
	Log.e("423-server", "Matrix sent to remote");
	initTransferManager();
	Log.e("423-server", "Transfer manager initialized");
	initAdaptor();
	Log.e("423-server", "Adaptor initiialized");
	processJobs();
	Log.e("423-server", "Do my work");
	this.getRemoteResults();
	Log.e("423-server", "Got result from remote");
	this.generateResultMatrix();
	Log.e("423-server", "Final matrix generated");
    }

    private void generateMatrix() {
	this.matrix1Up = new Matrix((int) Math.floor((double) row / 2), col,
		this.VALUE);
	this.matrix1Down = new Matrix((int) Math.floor((double) row / 2), col,
		this.VALUE);
	this.matrix2Up = new Matrix((int) Math.ceil((double) row / 2), col,
		this.VALUE);
	this.matrix2Down = new Matrix((int) Math.ceil((double) row / 2), col,
		this.VALUE);
    }
    
    private void initJobQueue() {
	jobQueue = MatrixJobQueue.generateJobQueueWithMatrixs(matrix1Up, matrix2Up);
    }

    private void sendMatrix() {
	try {
	    this.serverChannel.sendObject(this.matrix1Down);
	    this.serverChannel.sendObject(this.matrix2Down);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
    
    public void initTransferManager() {
	transferManager = new TransferManager(jobQueue, serverChannel);
    }
    
    public void initAdaptor() {
	adaptor = new Adaptor(stateManager, transferManager);
    }

    public void processJobs() {
	worker.processJobsWithThrottling(jobQueue);
    }
    
    private void startStateManager() {
	Server stateServerChannel = null;
	try {
	    stateServerChannel = new Server(port + 1);
	} catch (IOException e) {
	    Log.e("423-server ERROR", ""+ + serverChannel.getLocalPort());
	}
   	stateManager = new StateManager(jobQueue, hwMonitor, stateServerChannel);
    }

    private void getRemoteResults() {
	try {
	    MatrixResults remote_result = (MatrixResults) this.serverChannel
		    .getObject();
	    this.results.addResults(remote_result);
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

    private void generateResultMatrix() {
	this.resultMatrix = this.results.generateResultMatrix();
    }

    public Matrix getResultMatrix() {
	return resultMatrix;
    }

    public void close() {
	this.serverChannel.close();
    }
}
