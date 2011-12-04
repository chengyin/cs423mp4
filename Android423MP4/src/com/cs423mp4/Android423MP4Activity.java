/**
 * TODO: http://stackoverflow.com/questions/5054076/how-to-write-an-android-socketserver-to-listen-on-wifi
 */

package com.cs423mp4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Main activity as the home screen for the app. Including basic settings for the next step.
 * 
 * @author chengyin
 *
 */
public class Android423MP4Activity extends Activity {
    private RadioButton serverRadio;
    private RadioButton clientRadio;
    private Button startButton;
    private View serverSettings;
    private View clientSettings;

    private EditText serverPortText;
    private EditText rowText;
    private EditText colText;
    private EditText ipText;
    private EditText portText;

    /**
     * Create the activity
     * 
     * @param savedInstanceState
     * @see init
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);

	this.init();
    }

    /**
     * Set default values for UI elements
     */
    private void setDefaultValues() {
	this.serverPortText.setText(Integer.toString((int) (4000 + 4000 * Math.random())));
	this.rowText.setText("100");
	this.colText.setText("100");

	this.portText.setText("");
    }
    
    /**
     * Initialize the view.
     */
    private void initView() {
	serverRadio = (RadioButton) findViewById(R.id.serverRadio);
	clientRadio = (RadioButton) findViewById(R.id.clientRadio);
	startButton = (Button) findViewById(R.id.startButton);
	serverSettings = findViewById(R.id.serverSettings);
	clientSettings = findViewById(R.id.clientSettings);

	serverPortText = (EditText) findViewById(R.id.serverPortText);
	rowText = (EditText) findViewById(R.id.rowText);
	colText = (EditText) findViewById(R.id.colText);
	ipText = (EditText) findViewById(R.id.ipText);
	portText = (EditText) findViewById(R.id.portText);

	this.setDefaultValues();
	// handleThrottleInput();
	handleStartTypeRadio();
	handleStart();
    }

    /**
     * Initialize the activity
     */
    private void init() {
	this.initView();
    }

    /**
     * Switch between start type radio buttons, display corresponded settings
     */
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

    // private void handleThrottleInput() {
    // final EditText throttleText = (EditText) findViewById(R.id.throttleText);
    //
    // throttleText.setOnKeyListener(new OnKeyListener() {
    // public boolean onKey(View v, int keyCode, KeyEvent event) {
    // if ((event.getAction() == KeyEvent.ACTION_DOWN)
    // && (keyCode == KeyEvent.KEYCODE_ENTER)) {
    // monitor.setThrottle(Double.parseDouble(""
    // + throttleText.getText()));
    // return true;
    // }
    // return false;
    // }
    // });
    // }

    /**
     * Start the server activity
     */
    private void startServer() {
	Intent i = new Intent(getApplicationContext(), ServerActivity.class);

	// Pass settings
	i.putExtra("port",
		Integer.parseInt(serverPortText.getText().toString()));
	i.putExtra("row", Integer.parseInt(rowText.getText().toString()));
	i.putExtra("col", Integer.parseInt(colText.getText().toString()));

	startActivity(i);
    }

    /**
     * Start the client activity 
     */
    private void startClient() {
	Intent i = new Intent(getApplicationContext(), ClientActivity.class);
	
	// pass settings
	i.putExtra("ip", ipText.getText().toString());
	i.putExtra("port", Integer.parseInt(portText.getText().toString()));

	startActivity(i);
    }

    /**
     * Handler for startButton. Start server or client
     */
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
}
