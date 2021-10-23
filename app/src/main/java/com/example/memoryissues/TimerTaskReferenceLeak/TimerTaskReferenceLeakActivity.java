package com.example.memoryissues.TimerTaskReferenceLeak;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.annotation.Nullable;

import com.example.memoryissues.R;

public class TimerTaskReferenceLeakActivity extends Activity {

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        startTimer();
    }

    /*
     * Mistake 1: Cancel Timer is never called
     * even though activity might be completed
     * */
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
}
