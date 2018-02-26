package com.nightshade.forwarding.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.nightshade.forwarding.service.MyService;

public class bootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"I am boot start service",Toast.LENGTH_SHORT).show();
        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            Intent i=new Intent(context,MyService.class);
            i.setAction("start");
            context.startService(i);

        }
    }
}