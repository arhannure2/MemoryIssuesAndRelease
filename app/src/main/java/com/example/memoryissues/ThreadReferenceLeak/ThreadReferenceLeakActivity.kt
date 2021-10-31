package com.example.memoryissues.ThreadReferenceLeak;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memoryissues.InnerClassReferenceLeak.SecondActivity;
import com.example.memoryissues.R;

public class ThreadReferenceLeakActivity extends AppCompatActivity {

    /*
     * Mistake Number 1: Do not use static variables
     * */
    private static LeakyThread thread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        createThread();
        redirectToNewScreen();
    }


    private void createThread() {
        thread = new LeakyThread();
        thread.start();
    }

    private void redirectToNewScreen() {
        startActivity(new Intent(this, SecondActivity.class));
    }


    /*
     * Mistake Number 2: Non-static anonymous classes hold an
     * implicit reference to their enclosing class.
     * */
    private class LeakyThread extends Thread {
        @Override
        public void run() {
            while (true) {
            }
        }
    }
}