package com.wifidev.logic.wifi;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.wifidev.config.Logging;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteOrder;

public class WifiConnection {
	public static String getInternalIP(Context context) {
		//--------------------------------
		// Get wifi manager
		//--------------------------------

		WifiManager manager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

		//--------------------------------
		// Get IP
		//--------------------------------

		int ipAddress = manager.getConnectionInfo().getIpAddress();

		//--------------------------------
		// Convert little-endian to
		// big-endian if needed
		//--------------------------------

		if (ByteOrder.nativeOrder().equals(ByteOrder.LITTLE_ENDIAN)) {
			ipAddress = Integer.reverseBytes(ipAddress);
		}

		//--------------------------------
		// Convert to byte array
		//--------------------------------

		byte[] ipByteArray = BigInteger.valueOf(ipAddress).toByteArray();

		try {
			//--------------------------------
			// Convert to an actual IP string
			//--------------------------------

			return InetAddress.getByAddress(ipByteArray).getHostAddress();
		} catch (UnknownHostException ex) {
			//--------------------------------
			// Log the error
			//--------------------------------

			Log.e(Logging.TAG_NAME, "Unable to get IP address.");
		}

		//--------------------------------
		// No IP
		//--------------------------------

		return null;
	}
}
