package info.umer.carhakeem.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import info.umer.carhakeem.Helpers.Entities.App;

public class sharedPrefs {

    private static SharedPreferences Prefs(Context context) {
        return context.getSharedPreferences("CarHakeem", context.MODE_PRIVATE);
    }

    public static SharedPreferences getPref() {
        return Prefs(App.get().getApplicationContext());
    }

    public static SharedPreferences.Editor setPref() {
        // perform validation etc..
        return Prefs(App.get().getApplicationContext()).edit();
    }

    public static void putString(String key, String value)
    {
        try {

            setPref().putString(key,value).commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void putLong(String key, long value)
    {
        setPref().putLong(key,value).commit();
    }


    public static void putBool(String key, boolean value)
    {
        setPref().putBoolean(key,value).commit();
    }

    public static String getString(String key)
    {
        try {
            return getPref().getString(key,"--");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Boolean getBool(String key)
    {
        return getPref().getBoolean(key,false);
    }



}