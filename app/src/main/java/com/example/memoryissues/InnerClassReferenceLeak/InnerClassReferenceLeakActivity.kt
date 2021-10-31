package com.example.memoryissues.InnerClassReferenceLeak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memoryissues.R
import com.example.memoryissues.InnerClassReferenceLeak.InnerClassReferenceLeakActivity
import android.app.Activity
import android.content.Intent
import com.example.memoryissues.InnerClassReferenceLeak.SecondActivity

class InnerClassReferenceLeakActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        LeakyClass(this).redirectToSecondScreen()

        /*
         * Inner class is defined here
         * */leakyClass = LeakyClass(this)
        leakyClass!!.redirectToSecondScreen()
    }

    /*
     * Mistake Number 2:InnerClassReferenceLeakActivity
     * 1. Never create a inner variable of an inner class
     * 2. Never pass an instance of the activity to the inner class
     */
    private inner class LeakyClass(activity: InnerClassReferenceLeakActivity) {
        private val activity: Activity
        fun redirectToSecondScreen() {
            activity.startActivity(Intent(activity, SecondActivity::class.java))
        }

        init {
            this.activity = activity
        }
    }

    companion object {
        /*
     * Mistake Number 1:
     * Never create a static variable of an inner class
     * Fix I:
     * private LeakyClass leakyClass;
     */
        private var leakyClass: LeakyClass? = null
    }
}