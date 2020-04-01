package com.androiddeveloper.sheikhameen.projectideaspredictor;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView splash_logo=findViewById(R.id.SplashLogo);
        TextView splash_text=findViewById(R.id.splash_text);
        Animation anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim);
        splash_logo.startAnimation(anim);
        splash_text.startAnimation(anim);
        Thread timer=new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent intent=new Intent(getApplicationContext(),Categories.class);
                    startActivity(intent);
                    finish();
                    super.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        timer.start();
    }
}
