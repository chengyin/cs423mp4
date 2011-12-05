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
	int sum = job.getM1();
	for (int i = 0; i < 10000; i++)
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
	MatrixResult result = this.process(job);

	if (this.getResults() != null) {
	    this.getResults().addResult(result);
	}

	return result;
    }
}
