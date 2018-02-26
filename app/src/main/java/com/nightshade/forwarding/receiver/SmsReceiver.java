package com.nightshade.forwarding.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.nightshade.forwarding.listener.SmsListener;
import com.nightshade.forwarding.model.Sms;

/**
 * Created by sega4 on 21/02/2018.
 */

public class SmsReceiver extends BroadcastReceiver {

    //interface
    private static SmsListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();
        System.out.println("da nhan");
        Object[] pdus = (Object[]) data.get("pdus");

        for (int i = 0; i < pdus.length; i++) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

            String sender = smsMessage.getDisplayOriginatingAddress();
            //Check the sender to filter messages which we require to read


            String messageBody = smsMessage.getMessageBody();
            Sms sms = new Sms();
            sms.setSender(sender);
            sms.setText(messageBody);
            //Pass the message text to interface

            mListener.messageReceived(sms);


        }

    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }


}

