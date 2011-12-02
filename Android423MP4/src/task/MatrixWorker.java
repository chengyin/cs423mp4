package task;

public class MatrixWorker {
    public MatrixResult processJob(MatrixJob job) {
	return new MatrixResult(job.getRow(), job.getCol(), job.getM1() + job.getM2());
    }
}
