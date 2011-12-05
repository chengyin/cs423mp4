package channel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import android.util.Log;

public abstract class Channel {
    protected Socket socket = null;
    protected DataOutputStream outStream = null;
    protected DataInputStream inStream = null;
    protected ObjectOutputStream objOutStream = null;
    protected ObjectInputStream objInStream = null;

    /**
     * Send Message through the channel.
     * 
     * @param message
     *            message to be sent
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
	this.outStream.writeUTF(message);
    }

    /**
     * Read one message through the channel. Will block if no message is in the
     * buffer.
     * 
     * @return message received
     * @throws IOException
     */
    public String getMessage() throws IOException {
	if (this.inStream == null) {
	    return null;
	}
	
	return this.inStream.readUTF();
    }

    /**
     * Send serialized object through the channel.
     * 
     * @param obj
     *            object that will be sent
     * @throws IOException
     */
    public void sendObject(Serializable obj) throws IOException {	
	this.objOutStream.writeObject(obj);
    }

    public int getLocalPort() {
	return this.socket.getLocalPort();
    }

    /**
     * http://www.droidnova.com/get-the-ip-address-of-your-device,304.html
     * 
     * @return
     */
    public String getLocalIPAddress() {
	try {
	    for (Enumeration<NetworkInterface> en = NetworkInterface
		    .getNetworkInterfaces(); en.hasMoreElements();) {
		NetworkInterface intf = en.nextElement();
		for (Enumeration<InetAddress> enumIpAddr = intf
			.getInetAddresses(); enumIpAddr.hasMoreElements();) {
		    InetAddress inetAddress = enumIpAddr.nextElement();
		    if (!inetAddress.isLoopbackAddress()) {
			return inetAddress.getHostAddress().toString();
		    }
		}
	    }
	} catch (SocketException ex) {
	    Log.e("", ex.toString());
	}
	return null;
    }

    public Object getObject() throws OptionalDataException,
	    ClassNotFoundException, IOException {
	Object o = null;
	
	while (o == null) {
	    o = this.objInStream.readObject();
	}
	return o;
    }

    public int getRemotePort() {
	return this.socket.getPort();
    }

    public String getRemoteIPAddress() {
	return this.socket.getInetAddress().getHostAddress();
    }
    
    public boolean isConnected() {
	return socket != null;
    }
}
