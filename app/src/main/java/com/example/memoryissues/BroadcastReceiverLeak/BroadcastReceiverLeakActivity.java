package com.example.memoryissues.BroadcastReceiverLeak;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memoryissues.R;

public class BroadcastReceiverLeakActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
    }

    private void registerBroadCastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //your receiver code goes here!
            }
        };
        registerReceiver(broadcastReceiver, new IntentFilter("SmsMessage.intent.MAIN"));
    }


    @Override
    protected void onStart() {
        super.onStart();
        registerBroadCastReceiver();
    }


    @Override
    protected void onStop() {
        super.onStop();

        /*
         * Uncomment this line in order to avoid memory leak.
         * You need to unregister the broadcast receiver since the broadcast receiver keeps a reference of the activity.
         * Now when its time for your Activity to die, the Android framework will call onDestroy() on it
         * but the garbage collector will not be able to remove the instance from memory because the broadcastReceiver
         * is still holding a strong reference to it.
         * */

        if (broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver);
        }
    }
}
