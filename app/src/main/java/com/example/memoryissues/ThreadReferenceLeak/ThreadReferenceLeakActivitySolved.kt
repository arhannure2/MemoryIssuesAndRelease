package com.example.memoryissues.ThreadReferenceLeak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memoryissues.R
import android.content.Intent
import com.example.memoryissues.InnerClassReferenceLeak.SecondActivity

class ThreadReferenceLeakActivitySolved : AppCompatActivity() {
    /*
     * FIX I: make variable non static
     * */
    private val leakyThread = LeakyThread()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        createThread()
        redirectToNewScreen()
    }

    private fun createThread() {
        leakyThread.start()
    }

    private fun redirectToNewScreen() {
        startActivity(Intent(this, SecondActivity::class.java))
    }

    override fun onDestroy() {
        super.onDestroy()
        // FIX II: kill the thread
        leakyThread.interrupt()
    }

    /*
     * Fix III: Make thread static
     * */
    private class LeakyThread : Thread() {
        override fun run() {
            while (!isInterrupted) {
            }
        }
    }
}