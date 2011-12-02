package channel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Channel {
    int port;
    ServerSocket serverSocket;
    
    public Server(int port) throws IOException {
	super();
	this.port = port;
	this.serverSocket = new ServerSocket(port);
	this.listen();
    }
    
    private void listen() throws IOException {
	this.socket = this.serverSocket.accept();
	this.inStream = new DataInputStream(this.socket.getInputStream());
	this.outStream = new DataOutputStream(this.socket.getOutputStream());
    }
    
    protected void finalize() {
	if (this.socket != null) {
	    try {
		this.socket.close();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	
	if (this.inStream != null) {
	    try {
		this.inStream.close();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	
	if (this.outStream != null) {
	    try {
		this.outStream.close();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }
}
