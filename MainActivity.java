package com.example.prasad.firstweatyahoo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);

        new Handler().postDelayed(new Runnable(){
            public void run(){
                Intent homeIntent =new Intent(MainActivity.this,Main2Activity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);

       // MediaPlayer player=MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
      //player.setLooping(false);



        //player.start();

    }
}
