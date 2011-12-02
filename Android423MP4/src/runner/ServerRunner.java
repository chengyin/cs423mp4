package runner;

import java.io.IOException;

import channel.Server;

public class ServerRunner extends Runner {
    protected int port;
    protected Server serverChannel;
    
    protected void startServer() {
	try {
	    this.serverChannel = new Server(this.port);
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    public int getPort() {
        return port;
    }

    public Server getServerChannel() {
        return serverChannel;
    }
}
