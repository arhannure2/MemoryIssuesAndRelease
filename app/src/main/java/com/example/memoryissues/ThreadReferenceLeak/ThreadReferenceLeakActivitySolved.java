package com.example.memoryissues.ThreadReferenceLeak;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memoryissues.InnerClassReferenceLeak.SecondActivity;
import com.example.memoryissues.R;

public class ThreadReferenceLeakActivitySolved extends AppCompatActivity {

    /*
     * FIX I: make variable non static
     * */
    private LeakyThread leakyThread = new LeakyThread();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        createThread();
        redirectToNewScreen();
    }


    private void createThread() {
        leakyThread.start();
    }

    private void redirectToNewScreen() {
        startActivity(new Intent(this, SecondActivity.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // FIX II: kill the thread
        leakyThread.interrupt();
    }


    /*
     * Fix III: Make thread static
     * */
    private static class LeakyThread extends Thread {
        @Override
        public void run() {
            while (!isInterrupted()) {
            }
        }
    }
}
