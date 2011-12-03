package channel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;

import android.util.Log;

public class Server extends Channel {
    int port;
    ServerSocket serverSocket;
    
    public Server(int port) throws IOException {
	super();
	this.port = port;
	this.serverSocket = new ServerSocket(port);
	
	/*
	Runnable listener = new Runnable() {
	    public void run() {
		try { */
	//listen();
	/*	} catch (IOException e) {
		}
	    }
	};	
	new Thread(listener).start();*/
    }
    
    public void listen() throws IOException {
	this.socket = this.serverSocket.accept();
	this.inStream = new DataInputStream(this.socket.getInputStream());
	this.outStream = new DataOutputStream(this.socket.getOutputStream());
    }
    
    public int getLocalPort() {
	return this.serverSocket.getLocalPort();
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
