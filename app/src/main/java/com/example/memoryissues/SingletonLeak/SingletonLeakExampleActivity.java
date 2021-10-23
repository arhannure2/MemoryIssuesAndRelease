package com.example.memoryissues.SingletonLeak;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SingletonLeakExampleActivity extends AppCompatActivity {

    private SingletonSampleClass singletonSampleClass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
         * Option 1: Do not pass activity context to the Singleton class. Instead pass application Context
         */
        singletonSampleClass = SingletonSampleClass.getInstance(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        /*
         * Option 2: Unregister the singleton class here i.e. if you pass activity context to the Singleton class,
         * then ensure that when the activity is destroyed, the context in the singleton class is set to null.
         */
        singletonSampleClass.onDestroy();
    }
}