package com.example.memoryissues.AnonymousClassReferenceLeak;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memoryissues.R;

import java.lang.ref.WeakReference;

/*
*
* This follows the same theory as before. Sample implementation for fixing memory leak is given below:
* */

public class AnonymousClassReferenceLeakActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anonymous_class_reference_leak);


        textView = findViewById(R.id.activity_text);
        textView.setText(getString(R.string.text_inner_class_1));
        findViewById(R.id.activity_dialog_btn).setVisibility(View.INVISIBLE);

        /*
         * Runnable class is defined here
         * */
        new Thread(new LeakyRunnable(textView)).start();
    }



    private static class LeakyRunnable implements Runnable {

        private final WeakReference<TextView> messageViewReference;
        private LeakyRunnable(TextView textView) {
            this.messageViewReference = new WeakReference<>(textView);
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TextView textView = messageViewReference.get();
            if(textView != null) {
                textView.setText("Runnable class has completed its work");
            }
        }
    }
}