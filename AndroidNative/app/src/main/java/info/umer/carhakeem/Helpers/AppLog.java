package info.umer.carhakeem.Helpers;


import android.util.Log;

public class AppLog {

    private static final String CONSTANT_TAG = "APP:";


    public static int d(String tag, String msg) {
        tag = CONSTANT_TAG + tag;
        return Log.d(tag, msg);
    }

    public static int d(String tag, String msg, Throwable tr) {
        tag = CONSTANT_TAG + tag;
        return Log.d(tag, msg, tr);
    }

    public static int i(String tag, String msg) {
        tag = CONSTANT_TAG + tag;
        return Log.i(tag, msg);
    }

    public static int i(String tag, String msg, Throwable tr) {
        tag = CONSTANT_TAG + tag;
        return Log.i(tag, msg, tr);
    }


    public static int e(String tag, String msg) {
        tag = CONSTANT_TAG + tag;
        return Log.e(tag, msg);
    }

    public static int e(String tag, String msg, Throwable tr) {
        tag = CONSTANT_TAG + tag;
        return Log.e(tag, msg, tr);
    }
}
