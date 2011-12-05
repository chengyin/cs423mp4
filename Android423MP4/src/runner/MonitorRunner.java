package runner;

import control.HardwareMonitor;

/**
 * Abstract class to link local hardware monitor
 */
public abstract class MonitorRunner implements Runner {
    public HardwareMonitor hwMonitor = new HardwareMonitor();
}
