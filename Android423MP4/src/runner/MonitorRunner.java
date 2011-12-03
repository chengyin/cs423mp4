package runner;

import control.HardwareMonitor;

public abstract class MonitorRunner extends Runner {
    public HardwareMonitor hwMonitor = new HardwareMonitor();
}
