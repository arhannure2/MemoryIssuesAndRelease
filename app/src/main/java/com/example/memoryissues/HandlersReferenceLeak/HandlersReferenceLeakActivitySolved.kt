package com.example.memoryissues.HandlersReferenceLeak

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import com.example.memoryissues.HandlersReferenceLeak.HandlersReferenceLeakActivitySolved.LeakyHandler
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.example.memoryissues.R
import com.example.memoryissues.HandlersReferenceLeak.HandlersReferenceLeakActivitySolved
import java.lang.ref.WeakReference

class HandlersReferenceLeakActivitySolved : AppCompatActivity() {
    private val textView: TextView? = null

    /*
     * Fix number I
     * */
    private val leakyHandler = LeakyHandler(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        leakyHandler.postDelayed(leakyRunnable, 5000)
    }

    /*
     * Fix number II - define as static
     * */
    private class LeakyHandler(activity: HandlersReferenceLeakActivitySolved) : Handler() {
        /*
         * Fix number III - Use WeakReferences
         * */
        private val weakReference: WeakReference<HandlersReferenceLeakActivitySolved>
        override fun handleMessage(msg: Message) {
            val activity = weakReference.get()
            if (activity != null) {
                activity.textView!!.text = activity.getString(R.string.text_handler_2)
            }
        }

        init {
            weakReference = WeakReference(activity)
        }
    }

    companion object {
        private val leakyRunnable = Runnable { /* ... */ }
    }
}