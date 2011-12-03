package com.cs423mp4;

import runner.matrix.MatrixClientRunner;
import control.HardwareMonitor;

import android.os.Bundle;
import android.widget.TextView;

/**
 * Client activity. Initialize client, connect to the server and display resouce usages.
 * 
 * @author chengyin
 *
 */
public class ClientActivity extends MonitorActivity {
    private String serverIP;
    private int serverPort;

    private HardwareMonitor monitor = new HardwareMonitor();
    private TextView cpuUsageView;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	this.setContentView(R.layout.client);

	Bundle extras = getIntent().getExtras();

	this.serverIP = extras.getString("ip");
	this.serverPort = extras.getInt("port");

	this.cpuUsageView = (TextView) findViewById(R.id.usageView);

	this.init();
    }

    /**
     * Initialize view
     */
    private void initView() {
	this.displayCPUUsage(this.cpuUsageView, this.monitor);
    }
    
    /**
     * Initialize
     */
    private void init() {
	this.initView();
	this.start();
    }

    /**
     * Start logic, generate client
     */
    private void start() {
	// Start client
	MatrixClientRunner mcr = new MatrixClientRunner(this.serverIP, this.serverPort);
	mcr.run();
    }
}