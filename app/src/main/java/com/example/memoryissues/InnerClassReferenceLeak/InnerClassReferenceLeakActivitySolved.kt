package com.example.memoryissues.InnerClassReferenceLeak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memoryissues.R
import android.app.Activity
import android.content.Intent
import com.example.memoryissues.InnerClassReferenceLeak.SecondActivity
import java.lang.ref.WeakReference

class InnerClassReferenceLeakActivitySolved : AppCompatActivity() {
    /*
     * Mistake Number 1:
     * Never create a static variable of an inner class
     * Fix I:
     */
    private var leakyClass: LeakyClass? = null
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
     * How to fix the above class:
     * Fix memory leaks:
     * Option 1: The class should be set to static
     * Explanation: Instances of anonymous classes do not hold an implicit reference to their outer class
     * when they are "static".
     *
     * Option 2: Use a weakReference of the textview or any view/activity for that matter
     * Explanation: Weak References: Garbage collector can collect an object if only weak references
     * are pointing towards it.
     * */
    private class LeakyClass(activity: Activity) {
        private val messageViewReference: WeakReference<Activity>
        fun redirectToSecondScreen() {
            val activity = messageViewReference.get()
            activity?.startActivity(Intent(activity, SecondActivity::class.java))
        }

        init {
            //this.activity = new WeakReference<>(activity);
            messageViewReference = WeakReference(activity)
        }
    }
}