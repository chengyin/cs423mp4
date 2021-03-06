package control;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

/**
 * Receiver of all hardware state
 * 
 */
public class HardwareMonitor {
    private double throttle;
    private int level;

    public HardwareMonitor() {

    }

    /**
     * http://stackoverflow.com/questions/3118234/how-to-get-memory-usage-and-
     * cpu-usage-in-android
     * 
     * @return Fraction of total CPU usage of system
     */
    public double getCPUUsage() {
	try {
	    RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");
	    String load = reader.readLine();

	    String[] toks = load.split(" ");

	    long idle1 = Long.parseLong(toks[5]);
	    long cpu1 = Long.parseLong(toks[2]) + Long.parseLong(toks[3])
		    + Long.parseLong(toks[4]) + Long.parseLong(toks[6])
		    + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

	    try {
		Thread.sleep(360);
	    } catch (Exception e) {
	    }

	    reader.seek(0);
	    load = reader.readLine();
	    reader.close();

	    toks = load.split(" ");

	    long idle2 = Long.parseLong(toks[5]);
	    long cpu2 = Long.parseLong(toks[2]) + Long.parseLong(toks[3])
		    + Long.parseLong(toks[4]) + Long.parseLong(toks[6])
		    + Long.parseLong(toks[7]) + Long.parseLong(toks[8]);

	    return (double) (cpu2 - cpu1) / ((cpu2 + idle2) - (cpu1 + idle1));

	} catch (IOException ex) {
	    ex.printStackTrace();
	}

	return 0;
    }

    public void setBattery(int level) {
	this.level = level;
    }

    public int getBattery() {
	return this.level;
    }

    /**
     * @return Throttle value
     */
    public double getThrottle() {
	return throttle;
    }

    /**
     * Set throttle value
     * 
     * @param throttle
     */
    public void setThrottle(double throttle) {
	this.throttle = throttle;
    }
}
