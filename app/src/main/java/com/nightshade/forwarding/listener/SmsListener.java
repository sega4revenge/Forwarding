package com.nightshade.forwarding.listener;

import com.nightshade.forwarding.model.Sms;

public interface SmsListener {
     public void messageReceived(Sms messageText);
}