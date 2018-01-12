package org.scorelab.soundcom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class splash extends AppCompatActivity {

    // Splash screen timer
    private static int TIME_OUT = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        final TextView imageview = (TextView) findViewById(R.id.splash_screen_logo);
        Animation animation1=new TranslateAnimation(0.0f, 1000.0f, 0.0f, 0.0f);
        animation1.setDuration(2200);
        imageview.startAnimation(animation1);
        imageview.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageview.setVisibility(View.GONE);
            }
        },2200);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(splash.this,MainActivity.class);
                splash.this.startActivity(mainIntent);
                splash.this.finish();
            }
        }, TIME_OUT);
    }
}
