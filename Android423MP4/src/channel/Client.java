package channel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Channel {
    public Client(String ip, int port) throws UnknownHostException, IOException {
	this.socket = new Socket(ip, port);
	this.outStream = new DataOutputStream(socket.getOutputStream());
	this.inStream = new DataInputStream(socket.getInputStream());
    }
}
