package runner.matrix;

import java.io.IOException;
import java.io.OptionalDataException;

import android.util.Log;

import matrix.Matrix;
import runner.ServerRunner;
import task.matrix.MatrixJobQueue;
import task.matrix.MatrixResults;
import task.matrix.MatrixWorker;

public class MatrixServerRunner extends ServerRunner {
    int VALUE = 1;
    private Matrix matrix;
    private int row;
    private int col;
    protected MatrixWorker worker;
    private Matrix matrix1Up;
    private Matrix matrix1Down;
    private Matrix matrix2Up;
    private Matrix matrix2Down;
    MatrixJobQueue jobQueue;
    MatrixResults results;
    Matrix resultMatrix;

    public MatrixServerRunner(int port, int row, int col) throws IOException {
	super(port);

	this.row = row;
	this.col = col;

	this.results = new MatrixResults();
	this.worker = new MatrixWorker(0, this.hwMonitor, this.results);
    }

    public void run() {
	this.startServer();
	Log.e("423-server", "Client connected");
	this.generateMatrix();
	Log.e("423-server", "Matrix generated");
	this.sendMatrix();
	Log.e("423-server", "Matrix sent to remote");
	this.processJobs();
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

    private void sendMatrix() {
	try {
	    this.serverChannel.sendObject(this.matrix1Down);
	    this.serverChannel.sendObject(this.matrix2Down);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public void processJobs() {
	this.worker.processJobsWithThrottling(MatrixJobQueue
		.generateJobQueueWithMatrixs(matrix1Up, matrix2Up));
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
