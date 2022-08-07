package info.umer.carhakeem.UI.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import info.umer.carhakeem.Helpers.Entities.Constants;
import info.umer.carhakeem.Helpers.sharedPrefs;
import info.umer.carhakeem.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        startNewActivity(2000);
    }


    public void startNewActivity(int time) {

        final Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {



                if(sharedPrefs.getBool(Constants.userLoggedIn))
                {
                    Intent activitySignRegister = new Intent(Splash.this, Home.class);
                    startActivity(activitySignRegister);
                    finish();

                }else
                {
                    Intent activitySignRegister = new Intent(Splash.this, JoinNConnect.class);
                    startActivity(activitySignRegister);
                    finish();
                }


            }
        };
        handler.postDelayed(r, time);
    }

}