/**
 * TODO: http://stackoverflow.com/questions/5054076/how-to-write-an-android-socketserver-to-listen-on-wifi
 */

package com.cs423mp4;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import control.HardwareMonitor;

public class Android423MP4Activity extends Activity {
    
    private HardwareMonitor monitor = new HardwareMonitor();

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);

	handleThrottleInput();
	handleStartTypeRadio();
	displayUsage();
    }

    private void handleStartTypeRadio() {
	final RadioButton server = (RadioButton) findViewById(R.id.serverRadio);
	final RadioButton client = (RadioButton) findViewById(R.id.clientRadio);
	
	server.setOnClickListener(new OnClickListener() {
	   public void onClick(View v) {
	       final View serverSettings = findViewById(R.id.serverSettings);
	       final View clientSettings = findViewById(R.id.clientSettings);
	       
	       serverSettings.setVisibility(View.VISIBLE);
	       clientSettings.setVisibility(View.GONE);
	   }
	});
	
	client.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		final View serverSettings = findViewById(R.id.serverSettings);
		final View clientSettings = findViewById(R.id.clientSettings);

		serverSettings.setVisibility(View.GONE);
		clientSettings.setVisibility(View.VISIBLE);
	    }
	});
    }

    private void handleThrottleInput() {
	final EditText throttleText = (EditText) findViewById(R.id.throttleText);

	throttleText.setOnKeyListener(new OnKeyListener() {
	    public boolean onKey(View v, int keyCode, KeyEvent event) {
		if ((event.getAction() == KeyEvent.ACTION_DOWN)
			&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
		    monitor.setThrottle(Double.parseDouble(""
			    + throttleText.getText()));
		    return true;
		}
		return false;
	    }
	});
    }

    /**
     * http://stackoverflow.com/questions/6700802/android-timer-updating-a-
     * textview-ui
     */
    private void displayUsage() {
	final TextView usageView = (TextView) findViewById(R.id.usageView);

	final Handler mHandler = new Handler() {
	    public void handleMessage(Message msg) {
		usageView.setText(String.format("CPU Usage: %.2f%%",
			monitor.getCPUUsage() * 100));
	    }
	};

	Timer timer = new Timer();

	timer.scheduleAtFixedRate(new TimerTask() {
	    public void run() {
		mHandler.obtainMessage(1).sendToTarget();
	    }
	}, 0, 1000);
    }
}
