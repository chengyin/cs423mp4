package channel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import android.util.Log;

public abstract class Channel {
    Socket socket = null;
    DataOutputStream outStream = null;
    DataInputStream inStream = null;

    public void sendMessage(String message) throws IOException {
	this.outStream.writeUTF(message);
    }

    public String getMessage() throws IOException {
	if (this.inStream == null)
	    return null;
	return this.inStream.readUTF();
    }

    public int getLocalPort() {
	return this.socket.getLocalPort();
    }

    /**
     * http://www.droidnova.com/get-the-ip-address-of-your-device,304.html
     * @return
     */
    public String getLocalIPAddress() {
	    try {
	        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
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

    public int getRemotePort() {
	return this.socket.getPort();
    }

    public String getRemoteIPAddress() {
	return this.socket.getInetAddress().getHostAddress();
    }
}
