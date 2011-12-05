package runner;

import java.io.IOException;
import java.net.UnknownHostException;

import android.util.Log;

import channel.Client;

public class ClientRunner extends MonitorRunner {
    Client channel;
    Client stateChannel;

    public ClientRunner(String serverIP, int serverPort) {
	try {
	    Log.e("423-client", "Starting client");
	    this.setChannel(new Client(serverIP, serverPort));
	    stateChannel = new Client(serverIP, serverPort + 1);
	    Log.e("423-client", "Getting message");
	    Log.e("", stateChannel.getMessage());
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