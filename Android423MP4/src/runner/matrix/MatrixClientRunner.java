package runner.matrix;

import java.io.IOException;
import java.io.OptionalDataException;
import java.net.UnknownHostException;

import matrix.Matrix;

import channel.Client;
import runner.ClientRunner;
import task.matrix.MatrixJobQueue;
import task.matrix.MatrixResults;
import task.matrix.MatrixWorker;

public class MatrixClientRunner extends ClientRunner {
    private Client channel;
    protected MatrixWorker worker;
    private MatrixResults results;
    private Matrix matrix1;
    private Matrix matrix2;
    protected MatrixJobQueue jobQueue;

    public MatrixClientRunner(String serverIP, int serverPort) {
	try {
	    this.channel = new Client(serverIP, serverPort);
	} catch (UnknownHostException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	this.results = new MatrixResults();
	this.worker = new MatrixWorker(0, this.hwMonitor, this.results);
    }

    private void getMatrix() {
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

    private void createJobQueue() {
	this.jobQueue = MatrixJobQueue.generateJobQueueWithMatrixs(matrix1,
		matrix2);
    }

    public void work() {
	getMatrix();
	createJobQueue();
	super.work();
    }
    protected void finished() {
	//sendEndOfWorkSignal();
	sendResult();
    }

    private void sendResult() {
	try {
	    this.channel.sendObject(this.results);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
