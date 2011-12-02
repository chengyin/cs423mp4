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

    public MatrixResult processJob(MatrixJob job) {
	return new MatrixResult(job.getRow(), job.getCol(), job.getM1() + job.getM2());
    }

    @Override
    public Result processJob(Job job) {
	return this.processJob((MatrixJob) job);
    }
}
