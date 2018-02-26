package com.nightshade.forwarding.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.nightshade.forwarding.presenter.EmailPresenter
import com.nightshade.forwarding.receiver.SmsReceiver


class MyService : Service(), EmailPresenter.emailDetail {
    var mEmail: EmailPresenter? = null
    override fun isSuccessful(isSuccessful: Boolean) {
        if(isSuccessful)
            println("send ok")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent != null && intent.action == "start") {
            SmsReceiver.bindListener { messageText ->
                Log.e("Sender", messageText.sender)
                Log.e("Message", messageText.text)

            }
        } else
            stopMyService()
        return Service.START_STICKY
    }


    override fun onDestroy() {
        isServiceRunning = false
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder? {

        return null
    }


    private fun stopMyService() {
        stopForeground(true)
        stopSelf()
        isServiceRunning = false
    }

    companion object {


        var isServiceRunning = false
    }
}