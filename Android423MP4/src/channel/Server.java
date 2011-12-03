package channel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

public class Server extends Channel {
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
	super();
	this.serverSocket = new ServerSocket(port);
    }

    public boolean isConnected() {
	return this.socket != null;
    }

    public void listen() throws IOException {
	this.socket = this.serverSocket.accept();
	this.inStream = new DataInputStream(this.socket.getInputStream());
	this.outStream = new DataOutputStream(this.socket.getOutputStream());
	this.objOutStream = new ObjectOutputStream(socket.getOutputStream());
	this.objInStream = new ObjectInputStream(socket.getInputStream());
    }

    public int getLocalPort() {
	return this.serverSocket.getLocalPort();
    }

    public void close() {
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

    protected void finalize() {
	this.close();
    }
}
