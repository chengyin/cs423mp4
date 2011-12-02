package com.cs423mp4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class ServerActivity extends Activity {
    View stats;
    private View startButton;
    
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.server);
	
	this.init();
    }
    
    /**
     * Initialize view
     */
    public void initView() {
	this.stats = this.findViewById(R.id.stats);
	this.startButton = this.findViewById(R.id.startButton);
	
	this.stats.setVisibility(View.GONE);
	this.startButton.setVisibility(View.VISIBLE);
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