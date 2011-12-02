package com.cs423mp4;

import java.util.Timer;
import java.util.TimerTask;

import control.HardwareMonitor;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class ServerActivity extends MonitorActivity {
    View stats;
    private View startButton;
    private int port;
    private int row;
    private int col;
    private TextView usageView;

    private HardwareMonitor monitor = new HardwareMonitor();

    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.server);

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

	this.stats.setVisibility(View.GONE);
	this.startButton.setVisibility(View.VISIBLE);

	this.displayCPUUsage(this.usageView, this.monitor);
    }

    public void start() {
	this.startButton.setVisibility(View.GONE);
	this.stats.setVisibility(View.VISIBLE);
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
		that.start();
	    }
	});
    }
}
