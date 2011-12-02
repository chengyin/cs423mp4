package channel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * Channel for server
 * @author chengyin
 *
 */
public class Server extends Channel {
    int port;
    ServerSocket serverSocket;
    
    /**
     * Create a server on a port.
     * @param port
     * @throws IOException
     */
    public Server(int port) throws IOException {
	super();
	this.port = port;
	this.serverSocket = new ServerSocket(port);
	this.listen();
    }
    
    /**
     * Wait for connection, will be blocked till there is a connection
     * @throws IOException
     */
    private void listen() throws IOException {
	this.socket = this.serverSocket.accept();
	this.inStream = new DataInputStream(this.socket.getInputStream());
	this.outStream = new DataOutputStream(this.socket.getOutputStream());
    }
}
