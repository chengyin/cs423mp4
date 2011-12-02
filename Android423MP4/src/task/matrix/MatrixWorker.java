package task.matrix;

import task.Job;
import task.Result;
import task.Results;
import task.Worker;
import control.HardwareMonitor;

/**
 * Worker to perform matrix addition
 * 
 * @author chengyin
 * 
 */
public class MatrixWorker extends Worker {
    protected MatrixResults results;

    public MatrixWorker(int id, HardwareMonitor hwMonitor) {
	super(id, hwMonitor);
    }

    public MatrixWorker(int id, HardwareMonitor hwMonitor, Results results) {
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

    @Override
    public Result process(Job job) {
	return this.process((MatrixJob) job);
    }
}
