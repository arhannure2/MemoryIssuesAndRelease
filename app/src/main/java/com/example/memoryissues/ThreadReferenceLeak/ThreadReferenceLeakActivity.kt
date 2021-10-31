package com.example.memoryissues.ThreadReferenceLeak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memoryissues.R
import com.example.memoryissues.ThreadReferenceLeak.ThreadReferenceLeakActivity
import android.content.Intent
import com.example.memoryissues.InnerClassReferenceLeak.SecondActivity

class ThreadReferenceLeakActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        createThread()
        redirectToNewScreen()
    }

    private fun createThread() {
        thread = LeakyThread()
        thread!!.start()
    }

    private fun redirectToNewScreen() {
        startActivity(Intent(this, SecondActivity::class.java))
    }

    /*
     * Mistake Number 2: Non-static anonymous classes hold an
     * implicit reference to their enclosing class.
     * */
    private inner class LeakyThread : Thread() {
        override fun run() {
            while (true) {
            }
        }
    }

    companion object {
        /*
     * Mistake Number 1: Do not use static variables
     * */
        private var thread: LeakyThread? = null
    }
}