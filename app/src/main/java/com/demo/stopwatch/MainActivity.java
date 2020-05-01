package com.demo.stopwatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private boolean isRunning;
    private boolean tmpRunning;
    private int seconds=0;
    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.textViewTime);

        if (savedInstanceState!=null) {
            isRunning = savedInstanceState.getBoolean("isRunning");
            seconds = savedInstanceState.getInt("seconds");
        }

        runing();
    }

    @Override
    protected void onPause() {
        super.onPause();
        tmpRunning = isRunning;
        isRunning=false;
    }


    @Override
    protected void onResume() {
        super.onResume();
        isRunning=tmpRunning;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("isRunning",isRunning);
        outState.putInt("seconds",seconds);
    }

    public void start(View view) {
        isRunning=true;
    }

    public void stop(View view) {
        isRunning=false;
    }

    public void reset(View view) {
        isRunning=false;
        seconds=0;
    }


    private void runing(){
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours=seconds/3200;
                int minute=(seconds%3200)/60;
                int second = seconds%60;
                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minute,second);
                textView.setText(time);
                if (isRunning){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });





    }



}
