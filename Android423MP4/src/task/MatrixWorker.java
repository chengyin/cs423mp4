package task;

import control.HardwareMonitor;

public class MatrixWorker extends Worker{
    protected MatrixResults results;

    public MatrixWorker(int id, HardwareMonitor hwMonitor) {
	super(id, hwMonitor);
    }
    
    public MatrixWorker(int id, HardwareMonitor hwMonitor, Results results) {
	super(id, hwMonitor, results);
    }

    public MatrixResult process(MatrixJob job) {
	return new MatrixResult(job.getRow(), job.getCol(), job.getM1() + job.getM2());
    }

    @Override
    public Result process(Job job) {
	return this.process((MatrixJob) job);
    }
}
