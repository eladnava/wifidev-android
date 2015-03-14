package com.wifidev.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.wifidev.logic.adb.TCPIP;

public class BootReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		//---------------------------------
		// Set adbd tcp port
		//---------------------------------

		TCPIP.set();
	}
}