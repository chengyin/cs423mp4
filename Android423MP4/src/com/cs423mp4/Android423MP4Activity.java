/**
 * TODO: http://stackoverflow.com/questions/5054076/how-to-write-an-android-socketserver-to-listen-on-wifi
 */

package com.cs423mp4;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import control.HardwareMonitor;

public class Android423MP4Activity extends Activity {

    private HardwareMonitor monitor = new HardwareMonitor();
    RadioButton serverRadio;
    RadioButton clientRadio;
    Button startButton;
    View serverSettings;
    View clientSettings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);

	serverRadio = (RadioButton) findViewById(R.id.serverRadio);
	clientRadio = (RadioButton) findViewById(R.id.clientRadio);
	startButton = (Button) findViewById(R.id.startButton);
	serverSettings = findViewById(R.id.serverSettings);
	clientSettings = findViewById(R.id.clientSettings);

	handleThrottleInput();
	handleStartTypeRadio();
	handleStart();
	// displayUsage();
    }

    private void handleStartTypeRadio() {
	serverRadio.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
		serverSettings.setVisibility(View.VISIBLE);
		clientSettings.setVisibility(View.GONE);
	    }
	});

	clientRadio.setOnClickListener(new OnClickListener() {
	    public void onClick(View v) {
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

    private void startServer() {
	Intent i = new Intent(getApplicationContext(), ServerActivity.class);
	final EditText port = (EditText) findViewById(R.id.serverPortText);
	final EditText row = (EditText) findViewById(R.id.rowText);
	final EditText col = (EditText) findViewById(R.id.colText);

	i.putExtra("port", Integer.parseInt(port.getText().toString()));
	i.putExtra("row", Integer.parseInt(row.getText().toString()));
	i.putExtra("col", Integer.parseInt(col.getText().toString()));

	startActivity(i);
    }

    private void startClient() {
	// Intent i = new Intent(getApplicationContext());
	//
	// startAcitivty(i);
    }

    private void handleStart() {
	startButton.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		if (serverRadio.isChecked()) {
		    startServer();
		} else {
		    startClient();
		}
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
