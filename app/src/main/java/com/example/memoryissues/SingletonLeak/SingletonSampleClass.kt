package com.example.memoryissues.SingletonLeak

import android.content.Context
import com.example.memoryissues.SingletonLeak.SingletonSampleClass
import kotlin.jvm.Synchronized

class SingletonSampleClass private constructor(context: Context) {
    private var context: Context?
    fun onDestroy() {
        if (context != null) {
            context = null
        }
    }

    companion object {
        private var instance: SingletonSampleClass? = null
        @Synchronized
        fun getInstance(context: Context): SingletonSampleClass? {
            if (instance == null) instance = SingletonSampleClass(context)
            return instance
        }
    }

    init {
        this.context = context
    }
}