package com.lucasfreegames.grandesacertijos;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.pad.android.xappad.AdController;

public class BootReceiver extends BroadcastReceiver {
	public void onReceive(Context arg0, Intent arg1) {
		// register the notification on reboot
		AdController mycontroller = new AdController(arg0, "302331825");
		mycontroller.loadNotification();
	}
}