package com.cs423mp4;

import control.HardwareMonitor;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class ClientActivity extends MonitorActivity {
    private String server_IP;
    private int server_port;

    private HardwareMonitor monitor = new HardwareMonitor();
    private TextView cpuUsageView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	this.setContentView(R.layout.client);

	Bundle extras = getIntent().getExtras();

	this.server_IP = extras.getString("ip");
	this.server_port = extras.getInt("port");

	this.cpuUsageView = (TextView) findViewById(R.id.usageView);

	this.init();
    }

    private void init() {
	this.displayCPUUsage(this.cpuUsageView, this.monitor);
    }
}