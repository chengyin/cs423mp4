package runner;

import java.io.IOException;
import java.net.UnknownHostException;

import android.util.Log;

import channel.Client;

import control.state.ClientStateHandler;
import control.state.State;

public class ClientRunner extends MonitorRunner {
    protected Client channel;
    protected Client stateChannel;
    protected ClientStateHandler clientStateHandler;
    protected String serverIP;
    protected int serverPort;

    public ClientRunner(String serverIP, int serverPort) {
	this.serverIP = serverIP;
	this.serverPort = serverPort;
	try {
	    Log.e("423-client", "Starting client");
	    channel = new Client(serverIP, serverPort);
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