package org.scorelab.soundcom;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ImageView startBtn;
    TextView textView4;
    private int seconds=0;
    private boolean startRun;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            startRun=savedInstanceState.getBoolean("startRun");
        }
        animations();
        startBtn = (ImageView)findViewById(R.id.button1);
        startBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent svc=new Intent(getBaseContext(), RecordingService.class);
                startService(svc);

                Timer();
                startRun=true;


              //  finish(); //because I don't want to close the UI after service started
            }
        });
    }
    private void animations(){
        final TextView soundcom = (TextView) findViewById(R.id.textView);
        final TextView recTime = (TextView) findViewById(R.id.textView3);
        final TextView recTimeLive = (TextView) findViewById(R.id.textView4);
        final TextView tapMicToStart = (TextView) findViewById(R.id.textView2);
        final ImageView micButton = (ImageView) findViewById(R.id.button1);
        final ImageView stop = (ImageView) findViewById(R.id.imageButtonStop);
        final ImageView play = (ImageView) findViewById(R.id.imageButtonPlay);
        Animation animation1=new TranslateAnimation(0.0f, 0.0f, -100.0f, 0.0f);
        Animation animation2=new TranslateAnimation(-600.0f, 0.0f, 0.0f, 0.0f);
        Animation animation3=new TranslateAnimation(600.0f, 0.0f, 0.0f, 0.0f);
        Animation animation4=new TranslateAnimation(0.0f, 0.0f, 100.0f, 0.0f);
        animation1.setDuration(1000);
        animation2.setDuration(1200);
        animation3.setDuration(1200);
        animation4.setDuration(1000);
        soundcom.startAnimation(animation1);
        recTime.startAnimation(animation2);
        recTimeLive.startAnimation(animation2);
        tapMicToStart.startAnimation(animation3);
        micButton.startAnimation(animation3);
        stop.startAnimation(animation4);
        play.startAnimation(animation4);

    }
    public void onSaveInstanceState(Bundle saveInstanceState){
        saveInstanceState.putInt("seconds", seconds);
        saveInstanceState.putBoolean("startRun", startRun);
    }
    private void Timer(){
        final TextView timeView = (TextView)findViewById(R.id.textView4);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                String time = String.format("%d:%02d:%02d", hours, minutes, secs);

                timeView.setText(time);

                if(startRun){
                    seconds++;
                }

                handler.postDelayed(this, 100);
            }
        });

    }
}
