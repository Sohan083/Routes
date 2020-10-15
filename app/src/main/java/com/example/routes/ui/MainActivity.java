package com.example.routes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.routes.R;
import com.example.routes.Splash.SplashFragment;
import com.example.routes.appcomponents.NavigatorsTags;
import com.example.routes.appcomponents.RoutesFragmentManager;
import com.example.routes.architecture.MainViewModel;
import com.example.routes.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {


    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.iv);
        /*Glide.with(this)
                .load(R.drawable.logo)
                .into(iv);


         */

        Animation animation = new AlphaAnimation(1, (float) 0.70); //to change visibility from visible to invisible
        animation.setDuration(2000); //1 second duration for each animation cycle
        animation.setInterpolator(new LinearInterpolator());
        iv.startAnimation(animation); //to start animation


        final Intent i = new Intent(this,LoginActivity.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}