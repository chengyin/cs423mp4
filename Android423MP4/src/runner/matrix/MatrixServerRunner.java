package runner.matrix;

import java.io.IOException;
import java.io.OptionalDataException;

import matrix.Matrix;
import runner.ServerRunner;
import task.matrix.MatrixJobQueue;
import task.matrix.MatrixResults;
import task.matrix.MatrixWorker;

public class MatrixServerRunner extends ServerRunner {
    int VALUE = 1;
    Matrix matrix;
    private int row;
    private int col;
    protected MatrixWorker worker;
    private Matrix matrix1Up;
    private Matrix matrix1Down;
    private Matrix matrix2Up;
    private Matrix matrix2Down;
    protected MatrixJobQueue jobQueue;
    protected MatrixResults results;
    protected Matrix resultMatrix;

    public MatrixServerRunner(int port, int row, int col) {
	super();

	this.port = port;
	this.row = row;
	this.col = col;
	this.results = new MatrixResults();

	this.worker = new MatrixWorker(0, this.hwMonitor, this.results);
	
	this.startServer();
    }

    private void output() {
	this.resultMatrix = this.results.generateResultMatrix();
    }

    private void getResults() {
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
	    this.serverChannel.sendObject(matrix2Down);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    private void createJobQueue() {
	this.jobQueue = MatrixJobQueue.generateJobQueueWithMatrixs(matrix1Up,
		matrix2Up);
    }

    public Matrix getResultMatrix() {
	return resultMatrix;
    }
    
    public void work() {
	try {
	    this.serverChannel.listen();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	generateMatrix();
	sendMatrix();
	createJobQueue();
	super.work();
    }
    
    protected void finished() {
	getResults();
	output();
    }
}
