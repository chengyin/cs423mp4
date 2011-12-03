package channel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Channel for clients
 * @author chengyin
 *
 */
public class Client extends Channel {
    /**
     * Create a channel connecting to a server
     * @param ip server IP address
     * @param port server port
     * @throws UnknownHostException
     * @throws IOException
     */
    public Client(String ip, int port) throws UnknownHostException, IOException {
	this.socket = new Socket(ip, port);
	this.outStream = new DataOutputStream(socket.getOutputStream());
	this.inStream = new DataInputStream(socket.getInputStream());
	this.objOutStream = new ObjectOutputStream(socket.getOutputStream());
	this.objInStream = new ObjectInputStream(socket.getInputStream());
    }
}
