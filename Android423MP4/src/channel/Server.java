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
}
