package channel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public abstract class Channel {
    Socket socket = null;
    DataOutputStream outStream = null;
    DataInputStream inStream = null;

    public void sendMessage(String message) throws IOException {
	this.outStream.writeUTF(message);
    }

    public String getMessage(String message) throws IOException {
	return this.inStream.readUTF();
    }

    public int getLocalPort() {
	return this.socket.getLocalPort();
    }

    public String getLocalIPAddress() {
	return this.socket.getLocalAddress().getHostAddress();
    }

    public int getRemotePort() {
	return this.socket.getPort();
    }

    public String getRemoteIPAddress() {
	return this.socket.getInetAddress().getHostAddress();
    }
}
