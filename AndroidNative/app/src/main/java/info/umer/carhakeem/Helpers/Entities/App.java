package info.umer.carhakeem.Helpers.Entities;

import android.app.Application;
import android.os.Build;
import android.os.Handler;

import java.util.HashMap;
import java.util.Set;

import info.umer.carhakeem.Helpers.Logs;
import info.umer.carhakeem.Helpers.MapHelper;


public class App extends Application {

    public Logs _logs;
   // private Handler mHandler = new Handler();
    private static App sInstance;



    public String deviceType;

    public MapHelper  _mapHelper;
    Handler handler = new Handler();
    int delay = 60000;





    @Override
    public void onCreate() {
        super.onCreate();

        try {
            _logs = new Logs(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            deviceType = Build.DEVICE;
        } catch (Exception e) {
            deviceType = "--";
        }

        _mapHelper = new MapHelper(getApplicationContext());


        handler.postDelayed(new Runnable() {
            public void run() {
                _mapHelper.startLocationUpdate();
                handler.postDelayed(this, delay);
            }
        }, delay);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    public App() {
        sInstance = this;
    }

    public static App get() {
        return sInstance;
    }


}
