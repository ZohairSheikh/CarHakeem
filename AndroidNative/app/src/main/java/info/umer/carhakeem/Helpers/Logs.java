package info.umer.carhakeem.Helpers;

import android.content.Context;
import android.util.Log;



public class Logs {



    public Logs(Context _con) {

        try {



        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void writeToFile(String txt)
    {

        Log.i("AppLog", txt);

    }
}
