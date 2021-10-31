package com.example.memoryissues.InnerClassReferenceLeak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memoryissues.R;

public class InnerClassReferenceLeakActivity extends AppCompatActivity {

    /*
     * Mistake Number 1:
     * Never create a static variable of an inner class
     * Fix I:
     * private LeakyClass leakyClass;
     */
    private static LeakyClass leakyClass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        new LeakyClass(this).redirectToSecondScreen();

        /*
         * Inner class is defined here
         * */
        leakyClass = new LeakyClass(this);
        leakyClass.redirectToSecondScreen();
    }

    /*
     * Mistake Number 2:InnerClassReferenceLeakActivity
     * 1. Never create a inner variable of an inner class
     * 2. Never pass an instance of the activity to the inner class
     */
    private class LeakyClass {

        private Activity activity;
        public LeakyClass(InnerClassReferenceLeakActivity activity) {
            this.activity = activity;
        }

        public void redirectToSecondScreen() {
            this.activity.startActivity(new Intent(activity, SecondActivity.class));
        }
    }
}
