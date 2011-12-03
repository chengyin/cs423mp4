package com.cs423mp4;

import java.io.IOException;

import runner.matrix.MatrixServerRunner;

import control.HardwareMonitor;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Server activity. Initialize server, wait for connection and start computation
 * 
 * @author chengyin
 *
 */
public class ServerActivity extends MonitorActivity {
    View stats;
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
	try {
	    this.serverRunner = new MatrixServerRunner(this.port, this.row, this.col);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	this.updateIP(this.serverRunner.getServerChannel().getLocalIPAddress());
	this.updatePort(this.serverRunner.getServerChannel().getLocalPort());
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
	final ServerActivity that = this;

	this.startButton.setOnClickListener(new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
		that.serverRunner.work();
		
		that.startButton.setVisibility(View.GONE);
		that.stats.setVisibility(View.VISIBLE);
	    }
	});
	
	start();
    }
}
