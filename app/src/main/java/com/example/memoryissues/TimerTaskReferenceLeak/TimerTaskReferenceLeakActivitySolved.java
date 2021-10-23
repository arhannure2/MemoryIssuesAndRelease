package com.example.memoryissues.TimerTaskReferenceLeak;


import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.annotation.Nullable;

import com.example.memoryissues.R;

public class TimerTaskReferenceLeakActivitySolved extends Activity {

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        startTimer();
    }


    public void cancelTimer() {
        if(countDownTimer != null) countDownTimer.cancel();
    }


    private void startTimer() {
        countDownTimer = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                final int secondsRemaining = (int) (millisUntilFinished / 1000);
                //update UI
            }

            @Override
            public void onFinish() {
                //handle onFinish
            }
        };
        countDownTimer.start();
    }


    /*
     * Fix 1: Cancel Timer when
     * activity might be completed
     * */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        cancelTimer();
    }
}
