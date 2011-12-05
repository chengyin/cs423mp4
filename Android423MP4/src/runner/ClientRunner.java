package runner;

import java.io.IOException;
import java.net.UnknownHostException;

import android.util.Log;

import channel.Client;

import control.State;

public class ClientRunner extends MonitorRunner {
    protected Client channel;
    protected Client stateChannel;

    public ClientRunner(String serverIP, int serverPort) {
	try {
	    Log.e("423-client", "Starting client");
	    this.setChannel(new Client(serverIP, serverPort));
	    Log.e("423-client", "Connecting state channel");
	    stateChannel = new Client(serverIP, serverPort + 1);
	    Log.e("423-client", "Getting message");
	    try {
		Log.e("423-client", "Server CPU usage: "+((State)stateChannel.getObject()).getHwMonitor().getCPUUsage());
	    } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	} catch (UnknownHostException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public Client getChannel() {
	return channel;
    }

    public void setChannel(Client channel) {
	this.channel = channel;
    }
}