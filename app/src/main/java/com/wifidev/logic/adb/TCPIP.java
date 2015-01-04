package com.wifidev.logic.adb;

import android.util.Log;

import com.wifidev.config.ADB;
import com.wifidev.config.Logging;
import com.wifidev.logic.root.RootCommand;
import com.wifidev.root.RootTools;

public class TCPIP
{
    public static void set()
    {
        //--------------------------------
        // Make sure we have root
        //--------------------------------

        if (!RootTools.isRootAvailable())
        {
            return;
        }

        //--------------------------------
        // Make sure we have root
        // Also asks for root if not granted
        //--------------------------------

        if (!RootTools.isAccessGiven())
        {
            return;
        }

        //--------------------------------
        // Log it
        //--------------------------------

        Log.d(Logging.TAG_NAME, "Restarting adbd in tcpip mode..." );

        //--------------------------------
        // Set tcp port of adbd
        // And restart daemon
        //--------------------------------

        String command = "stop adbd && setprop service.adb.tcp.port " + ADB.TCPIP_PORT + " && start adbd &";

        try
        {
            //--------------------------------
            // Execute the command
            //--------------------------------

            RootCommand.execute(command);
        }
        catch( Exception exc )
        {
            //--------------------------------
            // Log exception
            //--------------------------------

            Log.e(Logging.TAG_NAME, exc.getMessage());
        }
    }
}
