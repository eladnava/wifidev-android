package com.wifidev.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.wifidev.R;
import com.wifidev.config.ADB;
import com.wifidev.logic.adb.TCPIP;
import com.wifidev.logic.wifi.WifiConnection;

public class Main extends Activity
{
    TextView connectCommand;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //--------------------------------
        // Call super
        //--------------------------------

        super.onCreate(savedInstanceState);

        //--------------------------------
        // Set up UI
        //--------------------------------

        initializeUI();

        //--------------------------------
        // Ask for root and set tcp port
        //--------------------------------

        TCPIP.set();
    }

    void initializeUI()
    {
        //--------------------------------
        // Load layout
        //--------------------------------

        setContentView(R.layout.main);

        //--------------------------------
        // Load layout
        //--------------------------------

        connectCommand = (TextView)findViewById(R.id.connectCommand);
    }

    @Override
    protected void onResume()
    {
        //--------------------------------
        // Call super
        //--------------------------------

        super.onResume();

        //--------------------------------
        // Reload connect command
        //--------------------------------

        showConnectCommand();
    }

    void showConnectCommand()
    {
        //--------------------------------
        // Get internal Wifi connection IP
        //--------------------------------

        String ip = WifiConnection.getInternalIP(this);

        //--------------------------------
        // No IP? Not on Wifi...
        //--------------------------------

        if ( ip == null )
        {
            //--------------------------------
            // Show error
            //--------------------------------

            connectCommand.setText(getString(R.string.noWifiConnection));
        }
        else
        {
            //--------------------------------
            // Get adbd port
            //--------------------------------

            int port = ADB.TCPIP_PORT;

            //--------------------------------
            // Prepare command
            //--------------------------------

            String cmd = getString(R.string.connectCommand) + " " + ip;

            //--------------------------------
            // Add port number
            //--------------------------------

            // cmd += ":" + port;

            //--------------------------------
            // Display connect command
            //--------------------------------

            connectCommand.setText(cmd);
        }
    }
}
