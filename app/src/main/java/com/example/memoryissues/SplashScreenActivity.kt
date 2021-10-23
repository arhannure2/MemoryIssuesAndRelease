package com.example.memoryissues



import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.encryptedsharedpreferences.extensions.startNewActivity


/*
Created by
Hannure Abdulrahim
on 10/23/2021.
 */
class SplashScreenActivity : AppCompatActivity() {

    val SPLASH_TIME_OUT: Long = 2500L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        headerTopColor(ContextCompat.getColor(applicationContext, R.color.purple_200))


        setContentView(R.layout.activity_splash)

        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                ///////Your Code
                //go to
                startNewActivity(MainActivity::class.java)
                ///finish this activity
                finish()

            }, SPLASH_TIME_OUT)
        }

    }


    public override fun onResume() {
        super.onResume()
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    }

    public override fun onPause() {
        super.onPause()
        this.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR
    }

    fun headerTopColor(COLOR_CODE: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = COLOR_CODE // top
            //            window.setNavigationBarColor(Color.TRANSPARENT); // bottom
        }
    }

}