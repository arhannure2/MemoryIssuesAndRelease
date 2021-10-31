package com.example.memoryissues.InnerClassReferenceLeak;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memoryissues.R;

import java.lang.ref.WeakReference;

public class InnerClassReferenceLeakActivitySolved extends AppCompatActivity {

    /*
     * Mistake Number 1:
     * Never create a static variable of an inner class
     * Fix I:
     */
    private LeakyClass leakyClass;

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
     * How to fix the above class:
     * Fix memory leaks:
     * Option 1: The class should be set to static
     * Explanation: Instances of anonymous classes do not hold an implicit reference to their outer class
     * when they are "static".
     *
     * Option 2: Use a weakReference of the textview or any view/activity for that matter
     * Explanation: Weak References: Garbage collector can collect an object if only weak references
     * are pointing towards it.
     * */
    private static class LeakyClass {

        private WeakReference<Activity> messageViewReference;
        public LeakyClass(Activity activity) {
            //this.activity = new WeakReference<>(activity);
            this.messageViewReference = new WeakReference<>(activity);
        }

        public void redirectToSecondScreen() {
            Activity activity = messageViewReference.get();
            if(activity != null) {
                activity.startActivity(new Intent(activity, SecondActivity.class));
            }
        }
    }
}
