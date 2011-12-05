package com.cs423mp4;

import java.io.IOException;

import runner.matrix.MatrixServerRunner;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import control.HardwareMonitor;

/**
 * Server activity. Initialize server, wait for connection and start computation
 * 
 */
public class ServerActivity extends MonitorActivity {
    private int port;
    private int row;
    private int col;
    private TextView usageView;
    private TextView portView;
    private TextView ipView;
    private MatrixServerRunner serverRunner;

    private HardwareMonitor monitor = new HardwareMonitor();

    /**
     * Start of activity
     */
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
	this.usageView = (TextView) findViewById(R.id.usageView);

	this.portView = (TextView) this.findViewById(R.id.portView);
	this.ipView = (TextView) this.findViewById(R.id.ipView);

	this.displayCPUUsage(this.usageView, this.monitor);
    }

    public void start() {
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

    /**
     * Updates the text field for port
     * 
     * @param localPort Port of server
     */
    private void updatePort(int localPort) {
	this.portView.setText(Integer.toString(localPort));
    }

    /**
     * Updates the text field for server IP
     * 
     * @param localIPAddress IP of server
     */
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
