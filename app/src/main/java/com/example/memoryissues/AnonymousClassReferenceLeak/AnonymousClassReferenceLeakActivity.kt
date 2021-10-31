package com.example.memoryissues.AnonymousClassReferenceLeak

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.view.View
import com.example.memoryissues.R
import com.example.memoryissues.AnonymousClassReferenceLeak.AnonymousClassReferenceLeakActivity.LeakyRunnable
import java.lang.ref.WeakReference

/*
*
* This follows the same theory as before. Sample implementation for fixing memory leak is given below:
* */
class AnonymousClassReferenceLeakActivity : AppCompatActivity() {
    private var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anonymous_class_reference_leak)
        textView = findViewById(R.id.activity_text)
        textView!!.setText(getString(R.string.text_inner_class_1))
        findViewById<View>(R.id.activity_dialog_btn).visibility = View.INVISIBLE

        /*
         * Runnable class is defined here
         * */Thread(LeakyRunnable(textView)).start()
    }

    private class LeakyRunnable(textView: TextView?) : Runnable {
        private val messageViewReference: WeakReference<TextView?>
        override fun run() {
            try {
                Thread.sleep(5000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            val textView = messageViewReference.get()
            if (textView != null) {
                textView.text = "Runnable class has completed its work"
            }
        }

        init {
            messageViewReference = WeakReference(textView)
        }
    }
}