package com.cs423mp4;

import java.io.IOException;

import runner.matrix.MatrixServerRunner;

import control.HardwareMonitor;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Server activity. Initialize server, wait for connection and start computation
 * 
 * @author chengyin
 * 
 */
public class ServerActivity extends MonitorActivity {
    private View stats;
    private View startButton;
    private int port;
    private int row;
    private int col;
    private TextView usageView;
    private TextView portView;
    private TextView ipView;
    private MatrixServerRunner serverRunner;

    private HardwareMonitor monitor = new HardwareMonitor();

    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.server);

	// Get settings
	Bundle extras = getIntent().getExtras();
	this.port = extras.getInt("port");
	this.row = extras.getInt("row");
	this.col = extras.getInt("col");

	this.init();
    }

    /**
     * Initialize view
     */
    public void initView() {
	this.stats = this.findViewById(R.id.stats);
	this.startButton = this.findViewById(R.id.startButton);
	this.usageView = (TextView) findViewById(R.id.usageView);

	this.portView = (TextView) this.findViewById(R.id.portView);
	this.ipView = (TextView) this.findViewById(R.id.ipView);

	this.displayCPUUsage(this.usageView, this.monitor);
    }

    public void start() {
	// Start Server
	// new Thread(new Runnable() {
	// public void run() {
	// try {
	// serverRunner = new MatrixServerRunner(port, row, col);
	// Log.e("423-server", "Server Initialized at "
	// + serverRunner.getServerChannel()
	// .getLocalIPAddress() + ":"
	// + serverRunner.getServerChannel().getLocalPort());
	//
	// updateIP(serverRunner.getServerChannel()
	// .getLocalIPAddress());
	// updatePort(serverRunner.getServerChannel().getLocalPort());
	//
	// Log.e("423-server", "Ready to run server");
	// serverRunner.run();
	// serverRunner.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }

	// }
	//
	// }).start();

	try {
	    serverRunner = new MatrixServerRunner(port, row, col);

	    Log.e("423-server", "Server Initialized at "
		    + serverRunner.getServerChannel().getLocalIPAddress() + ":"
		    + serverRunner.getServerChannel().getLocalPort());

	    updateIP(serverRunner.getServerChannel().getLocalIPAddress());
	    updatePort(serverRunner.getServerChannel().getLocalPort());

	    new ServerTask().execute(serverRunner);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    private void updatePort(int localPort) {
	this.portView.setText(Integer.toString(localPort));
    }

    private void updateIP(String localIPAddress) {
	this.ipView.setText(localIPAddress);
    }

    /**
     * Initialize
     */
    public void init() {
	this.initView();
	this.start();
    }
}
