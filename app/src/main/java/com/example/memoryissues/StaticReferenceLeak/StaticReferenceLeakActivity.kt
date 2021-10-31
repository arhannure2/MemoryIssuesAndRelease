package com.example.memoryissues.StaticReferenceLeak;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memoryissues.R;

public class StaticReferenceLeakActivity extends AppCompatActivity {


    /*
    * You are declaring a TextView as static (for whatever reason).
    * If you reference an activity or view directly or indirectly from a static reference,
    * the activity would not be garbage collected after it is destroyed.
    *
    *
    * */

    /*
     * This is a bad idea!
     */
    private static TextView textView;
    private static Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        textView = findViewById(R.id.activity_text);
        textView.setText("Bad Idea!");

        activity = this;
    }
}
