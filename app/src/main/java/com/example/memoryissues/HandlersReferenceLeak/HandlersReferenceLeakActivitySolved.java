package com.example.memoryissues.HandlersReferenceLeak;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memoryissues.R;

import java.lang.ref.WeakReference;

public class HandlersReferenceLeakActivitySolved extends AppCompatActivity {

    private TextView textView;

    /*
     * Fix number I
     * */
    private final LeakyHandler leakyHandler = new LeakyHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        leakyHandler.postDelayed(leakyRunnable, 5000);
    }

    /*
     * Fix number II - define as static
     * */
    private static class LeakyHandler extends Handler {

        /*
         * Fix number III - Use WeakReferences
         * */
        private WeakReference<HandlersReferenceLeakActivitySolved> weakReference;

        public LeakyHandler(HandlersReferenceLeakActivitySolved activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            HandlersReferenceLeakActivitySolved activity = weakReference.get();
            if (activity != null) {
                activity.textView.setText(activity.getString(R.string.text_handler_2));
            }
        }
    }


    private static final Runnable leakyRunnable = new Runnable() {
        @Override
        public void run() { /* ... */ }
    };
}
