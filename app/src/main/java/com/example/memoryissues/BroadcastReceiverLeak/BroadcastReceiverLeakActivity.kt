package com.example.memoryissues.BroadcastReceiverLeak

import androidx.appcompat.app.AppCompatActivity
import android.content.BroadcastReceiver
import android.content.Context
import android.os.Bundle
import com.example.memoryissues.R
import android.content.Intent
import android.content.IntentFilter

class BroadcastReceiverLeakActivity : AppCompatActivity() {
    private var broadcastReceiver: BroadcastReceiver? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
    }

    private fun registerBroadCastReceiver() {
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                //your receiver code goes here!
            }
        }
        registerReceiver(broadcastReceiver, IntentFilter("SmsMessage.intent.MAIN"))
    }

    override fun onStart() {
        super.onStart()
        registerBroadCastReceiver()
    }

    override fun onStop() {
        super.onStop()

        /*
         * Uncomment this line in order to avoid memory leak.
         * You need to unregister the broadcast receiver since the broadcast receiver keeps a reference of the activity.
         * Now when its time for your Activity to die, the Android framework will call onDestroy() on it
         * but the garbage collector will not be able to remove the instance from memory because the broadcastReceiver
         * is still holding a strong reference to it.
         * */if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver)
        }
    }
}