package task.matrix;

import task.Worker;
import control.HardwareMonitor;

/**
 * Worker to perform matrix addition
 * 
 */
public class MatrixWorker extends Worker<MatrixJob, MatrixResult> {

    private static final int TIMES = 100000;

    public MatrixWorker(int id, HardwareMonitor hwMonitor) {
	super(id, hwMonitor);
    }

    public MatrixWorker(int id, HardwareMonitor hwMonitor, MatrixResults results) {
	super(id, hwMonitor, results);
    }

    /**
     * Add two numbers in the matrix
     * 
     * @param job
     * @return result of the addition
     */
    public MatrixResult process(MatrixJob job) {
	int sum = job.getM1();
	for (int i = 0; i < TIMES; i++)
	    sum += job.getM2();
	return new MatrixResult(job.getRow(), job.getCol(), sum);
    }

    /**
     * Process job and store the result
     * 
     * @param job
     *            job to be processed
     * @return the result of the processing
     */
    public MatrixResult processJob(MatrixJob job) {
	MatrixResult result = process(job);

	if (getResults() != null) {
	    getResults().addResult(result);
	}

	return result;
    }
}
