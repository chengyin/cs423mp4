package task;

<<<<<<< HEAD
public class Worker {
    public Worker() {
	
=======
import java.util.concurrent.locks.LockSupport;

public abstract class Worker {
    int id;
    float throttling_ratio;
    long startTime;

    public Worker(int id) {
	super();
	this.id = id;
    }

    public abstract Result processJob(Job job);

    /**
     * http://stackoverflow.com/questions/1202184/throttling-cpu-memory-usage-of
     * -a-thread-in-java
     * 
     * @param job
     * @return
     */
    public Result processJobWithThrottling(Job job) {
	long startTime = System.currentTimeMillis();
	Result result = this.processJob(job);

	// Sleep
	LockSupport.parkNanos((long) (Math.max(0,
		(System.currentTimeMillis() - startTime) * 1000000
			* this.throttling_ratio)));
	
	return result;
>>>>>>> c81df3beac8ee5d8670ad745690df8997dee04eb
    }
}
