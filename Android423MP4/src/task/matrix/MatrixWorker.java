package task.matrix;

import task.Worker;
import control.HardwareMonitor;

/**
 * Worker to perform matrix addition
 * 
 * @author chengyin
 * 
 */
public class MatrixWorker extends Worker<MatrixJob, MatrixResult> {
    protected MatrixResults results;

    public MatrixWorker(int id, HardwareMonitor hwMonitor) {
	super(id, hwMonitor);
    }

    public MatrixWorker(int id, HardwareMonitor hwMonitor, MatrixResults results) {
	super(id, hwMonitor, results);
    }

    /**
     * Add two numbers in the matrix
     * @param job
     * @return result of the addition
     */
    public MatrixResult process(MatrixJob job) {
	return new MatrixResult(job.getRow(), job.getCol(), job.getM1()
		+ job.getM2());
    }
    
    /**
     * Process job and store the result
     * 
     * @param job
     *            job to be processed
     * @return the result of the processing
     */
    public MatrixResult processJob(MatrixJob job) {
	MatrixResult result = this.process(job);

	if (this.results != null) {
	    this.results.addResult(result);
	}

	return result;
    }
}
