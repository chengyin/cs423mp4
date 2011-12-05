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

/**
 * A wrapper around sockets used for TCP/IP communication
 */
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
	outStream.writeUTF(message);
    }

    /**
     * Read one message through the channel. Will block if no message is in the
     * buffer.
     * 
     * @return message received
     * @throws IOException
     */
    public String getMessage() throws IOException {
	if (inStream == null) {
	    return null;
	}

	return inStream.readUTF();
    }

    /**
     * Send serialized object through the channel.
     * 
     * @param obj
     *            object that will be sent
     * @throws IOException
     */
    public void sendObject(Serializable obj) throws IOException {
	objOutStream.writeObject(obj);
    }

    public int getLocalPort() {
	return socket.getLocalPort();
    }

    /**
     * http://www.droidnova.com/get-the-ip-address-of-your-device,304.html
     * 
     * @return A string representation of IP address
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

    /**
     * Blocks till an object is received from other end
     * 
     * @return Deserialized object
     * @throws OptionalDataException
     * @throws ClassNotFoundException
     * @throws IOException
     */
    public Object getObject() throws OptionalDataException,
	    ClassNotFoundException, IOException {
	Object o = null;

	while (o == null) {
	    o = objInStream.readObject();
	}
	return o;
    }

    /**
     * Note: this does not work if socket is closed
     * 
     * @return If there has been a connection
     */
    public boolean isConnected() {
	return socket != null;
    }
}
