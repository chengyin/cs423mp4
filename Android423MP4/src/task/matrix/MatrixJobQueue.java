package task.matrix;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;

import task.Job;
import task.JobQueue;

import matrix.Matrix;

public class MatrixJobQueue extends JobQueue {
    public static MatrixJobQueue generateJobQueueWithMatrixs(Matrix matrix1,
	    Matrix matrix2) {
	if (matrix1.getRow() != matrix2.getRow()
		|| matrix1.getCol() != matrix2.getCol()) {
	    return null;
	}

	int row = matrix1.getRow(), col = matrix1.getCol();
	MatrixJob[] jobs = new MatrixJob[row * col];

	for (int r = 0; r < row; r++) {
	    for (int c = 0; c < col; c++) {
		jobs[r * c] = new MatrixJob(r, c, matrix1.getElement(r, c), matrix2.getElement(r, c));
	    }
	}
	
	return (new MatrixJobQueue(Arrays.asList(jobs)));
    }

    public MatrixJobQueue(Collection<MatrixJob> jobs) {
	super();
	this.jobs = new LinkedBlockingQueue<Job>(jobs);
    }
    
    public MatrixJob dequeue() {
	return (MatrixJob) super.dequeue();
    }
    
    public void enqueue(MatrixJob job) {
	super.enqueue(job);
    }
    
    public int jobCount() {
	return this.jobs.size();
    }
}
