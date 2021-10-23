package com.example.memoryissues.HandlersReferenceLeak;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memoryissues.R;



public class HandlersReferenceLeakActivity extends AppCompatActivity {

    private TextView textView;

    /*
     * Mistake Number 1
     * */
    private Handler leakyHandler = new Handler();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        /*
         * Mistake Number 2
         * */
        leakyHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText(getString(R.string.text_handler_1));
            }
        }, 5000);
    }
}
