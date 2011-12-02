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

    public String getMessage() throws IOException {
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
