package runner;

import java.io.IOException;
import java.net.UnknownHostException;

import channel.Client;

public class ClientRunner extends MonitorRunner {
    Client channel;

    public ClientRunner(String serverIP, int serverPort) {
	try {
	    this.setChannel(new Client(serverIP, serverPort));
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