package channel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

/**
 * Channel for servers
 */
public class Server extends Channel {
    private ServerSocket serverSocket;

    /**
     * Constructor that initializes channel and creates serverSocket
     * 
     * @param port
     *            Port to listen to
     * @throws IOException
     */
    public Server(int port) throws IOException {
	super();
	serverSocket = new ServerSocket(port);
    }

    /**
     * A blocking call to accept and then initialize
     * streams
     * 
     * @throws IOException
     */
    public void listen() throws IOException {
	this.socket = this.serverSocket.accept();
	this.inStream = new DataInputStream(this.socket.getInputStream());
	this.outStream = new DataOutputStream(this.socket.getOutputStream());
	this.objOutStream = new ObjectOutputStream(socket.getOutputStream());
	this.objInStream = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * @return Port of server
     */
    public int getLocalPort() {
	return this.serverSocket.getLocalPort();
    }

    /**
     * Close the socket
     */
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

    /**
     * Let GC close the socket
     */
    protected void finalize() {
	this.close();
    }
}
