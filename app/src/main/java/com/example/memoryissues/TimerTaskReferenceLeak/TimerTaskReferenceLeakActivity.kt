package com.example.memoryissues.TimerTaskReferenceLeak

import android.app.Activity
import android.os.CountDownTimer
import android.os.Bundle
import com.example.memoryissues.R

class TimerTaskReferenceLeakActivity : Activity() {
    private var countDownTimer: CountDownTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        startTimer()
    }

    /*
     * Mistake 1: Cancel Timer is never called
     * even though activity might be completed
     * */
    fun cancelTimer() {
        if (countDownTimer != null) countDownTimer!!.cancel()
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = (millisUntilFinished / 1000).toInt()
                //update UI
            }

            override fun onFinish() {
                //handle onFinish
            }
        }
        countDownTimer!!.start()
    }
}