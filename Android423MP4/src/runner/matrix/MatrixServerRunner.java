package runner.matrix;

import java.io.IOException;

import runner.ServerRunner;
import task.Results;
import task.matrix.MatrixJobQueue;
import task.matrix.MatrixWorker;
import matrix.Matrix;

public class MatrixServerRunner extends ServerRunner {
    int VALUE = 1;
    Matrix matrix;
    private int row;
    private int col;
    private MatrixWorker worker;
    private Matrix matrix1Up;
    private Matrix matrix1Down;
    private Matrix matrix2Up;
    private Matrix matrix2Down;
    private MatrixJobQueue jobQueue;
    private Results results;

    public MatrixServerRunner(int port, int row, int col) {
	super();

	this.port = port;
	this.row = row;
	this.col = col;
	this.results = new Results();
	
	this.worker = new MatrixWorker(0, this.hwMonitor, this.results);

	generateMatrix();
	startServer();
	sendMatrix();
	createJobQueue();
	work();
	getResults();
	output();
    }

    private void output() {
	
    }

    private void getResults() {
	
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

    private void work() {
	while (this.jobQueue.jobCount() > 0) {
	    this.worker.processJobWithThrottling(this.jobQueue.dequeue());
	}
    }
}
