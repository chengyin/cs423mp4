package com.cs423mp4;

import java.util.Timer;
import java.util.TimerTask;

import control.HardwareMonitor;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/**
 * An abstract class for activities that needs to monitor hardware resources.
 * 
 */
public abstract class MonitorActivity extends Activity {

    /**
     * http://stackoverflow.com/questions/6700802/android-timer-updating-a-
     * textview-ui
     *
     * @param usageView UI element displaying view
     * @param monitor Get usage via monitor
     */
    protected void displayCPUUsage(final TextView usageView,
	    final HardwareMonitor monitor) {
	final Handler mHandler = new Handler() {
	    public void handleMessage(Message msg) {
		usageView.setText(String.format("%.2f%%",
			monitor.getCPUUsage() * 100));
	    }
	};

	Timer timer = new Timer();

	timer.scheduleAtFixedRate(new TimerTask() {
	    public void run() {
		mHandler.obtainMessage(1).sendToTarget();
	    }
	}, 0, 500);
    }
}
