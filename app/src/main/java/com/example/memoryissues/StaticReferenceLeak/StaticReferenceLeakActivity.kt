package com.example.memoryissues.StaticReferenceLeak

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.memoryissues.R
import com.example.memoryissues.StaticReferenceLeak.StaticReferenceLeakActivity
import android.widget.TextView
import android.app.Activity

class StaticReferenceLeakActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        textView = findViewById(R.id.activity_text)
        textView!!.setText("Bad Idea!")
        activity = this
    }

    companion object {
        /*
    * You are declaring a TextView as static (for whatever reason).
    * If you reference an activity or view directly or indirectly from a static reference,
    * the activity would not be garbage collected after it is destroyed.
    *
    *
    * */
        /*
     * This is a bad idea!
     */
        private var textView: TextView? = null
        private var activity: Activity? = null
    }
}