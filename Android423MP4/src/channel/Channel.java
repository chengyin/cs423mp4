package channel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public abstract class Channel {
    Socket socket = null;
    DataOutputStream outStream = null;
    DataInputStream inStream = null;

    /**
     * Send Message through the channel.
     * @param message message to be sent
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
	this.outStream.writeUTF(message);
    }

    /**
     * Read one message through the channel. Will block if no message is in the buffer.
     * 
     * @return message received
     * @throws IOException
     */
    public String getMessage() throws IOException {
	return this.inStream.readUTF();
    }

    /**
     * @return local port used by the socket 
     */
    public int getLocalPort() {
	return this.socket.getLocalPort();
    }

    /**
     * @return local IP address
     */
    public String getLocalIPAddress() {
	return this.socket.getLocalAddress().getHostAddress();
    }

    /**
     * @return remote port used by the socket
     */
    public int getRemotePort() {
	return this.socket.getPort();
    }

    /**
     * @return remote IP address
     */
    public String getRemoteIPAddress() {
	return this.socket.getInetAddress().getHostAddress();
    }
    
    /* (non-Javadoc)
     * Release all the recourse before being GC'ed
     * @see java.lang.Object#finalize()
     */
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
