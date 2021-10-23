package com.example.memoryissues.InnerClassReferenceLeak

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.memoryissues.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}