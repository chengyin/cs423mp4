package task.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.LinkedBlockingQueue;

import matrix.Matrix;
import task.JobQueue;

/**
 * JobQueue for Matrix addition
 * 
 * 
 */
public class MatrixJobQueue extends JobQueue<MatrixJob> {
    /**
     * Generate all the jobs for a matrix addition task
     * 
     * @param matrix1
     * @param matrix2
     * @return the job queue for adding the two given matrix
     */
    public static MatrixJobQueue generateJobQueueWithMatrixs(Matrix matrix1,
	    Matrix matrix2) {
	if (matrix1.getRow() != matrix2.getRow()
		|| matrix1.getCol() != matrix2.getCol()) {
	    // Check if the dimension is correct
	    return null;
	}

	int row = matrix1.getRow(), col = matrix1.getCol();
	MatrixJob[] jobs = new MatrixJob[row * col];

	for (int r = 0; r < row; r++) {
	    for (int c = 0; c < col; c++) {
		// Generate jobs
		jobs[r * col + c] = new MatrixJob(r, c,
			matrix1.getElement(r, c), matrix2.getElement(r, c));
	    }
	}

	return (new MatrixJobQueue(
		new ArrayList<MatrixJob>(Arrays.asList(jobs))));
    }

    protected LinkedBlockingQueue<MatrixJob> jobs;

    /**
     * Generate JobQueue with an collection of jobs
     * 
     * @param jobs
     *            Collection of jobs
     */
    public MatrixJobQueue(Collection<MatrixJob> jobs) {
	super();
	this.jobs = new LinkedBlockingQueue<MatrixJob>(jobs);
    }

    /*
     * (non-Javadoc)
     * 
     * @see task.JobQueue#dequeue()
     */
    public MatrixJob dequeue() {
	return (MatrixJob) this.jobs.poll();
    }

    /**
     * Add job to the tail of the queue
     * 
     * @param job
     */
    @Override
    public void enqueue(MatrixJob job) {
	if (job instanceof MatrixJob) {
	    jobs.add((MatrixJob) job);
	}
    }

    /**
     * Get the count of remaining jobs in the queue
     * 
     * @return count of the remaining jobs in the queue
     */
    public int jobCount() {
	return jobs.size();
    }
}
