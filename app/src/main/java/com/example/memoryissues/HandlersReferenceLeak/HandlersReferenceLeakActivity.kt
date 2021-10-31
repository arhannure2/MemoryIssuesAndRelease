package com.example.memoryissues.HandlersReferenceLeak

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.os.Handler
import com.example.memoryissues.R

class HandlersReferenceLeakActivity : AppCompatActivity() {
    private val textView: TextView? = null

    /*
     * Mistake Number 1
     * */
    private val leakyHandler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        /*
         * Mistake Number 2
         * */leakyHandler.postDelayed({ textView!!.text = getString(R.string.text_handler_1) }, 5000)
    }
}