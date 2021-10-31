package com.example.memoryissues.SingletonLeak

import androidx.appcompat.app.AppCompatActivity
import com.example.memoryissues.SingletonLeak.SingletonSampleClass
import android.os.Bundle

class SingletonLeakExampleActivity : AppCompatActivity() {
    private var singletonSampleClass: SingletonSampleClass? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
         * Option 1: Do not pass activity context to the Singleton class. Instead pass application Context
         */singletonSampleClass = SingletonSampleClass.getInstance(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        /*
         * Option 2: Unregister the singleton class here i.e. if you pass activity context to the Singleton class,
         * then ensure that when the activity is destroyed, the context in the singleton class is set to null.
         */singletonSampleClass!!.onDestroy()
    }
}