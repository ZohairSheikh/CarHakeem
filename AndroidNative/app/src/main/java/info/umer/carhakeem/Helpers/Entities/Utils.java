package info.umer.carhakeem.Helpers.Entities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Base64;
import android.widget.EditText;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import info.umer.carhakeem.Models.ApiCalls.apiCallAppointmentStatusResponse;
import info.umer.carhakeem.Models.ApiCalls.apiCallCreateProviderResponse;
import info.umer.carhakeem.Models.ApiCalls.apiCallErrorResponse;
import info.umer.carhakeem.Models.ApiCalls.apiCallLoginResponse;
import info.umer.carhakeem.Models.ApiCalls.apiCallMakeAppointmentResponse;
import info.umer.carhakeem.Models.ApiCalls.apiCallMyAppointmentResponse;
import info.umer.carhakeem.Models.ApiCalls.apiCallMyServicesResponse;
import info.umer.carhakeem.Models.ApiCalls.apiCallProvidersResponse;
import info.umer.carhakeem.Models.ApiCalls.apiCallSignUpResponse;
import info.umer.carhakeem.Models.ApiCalls.apiCallProviderTypesResponse;


public class Utils {

    static Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    static Pattern VALID_MOBILE_ONLY_ZERO_STARTING_REGEX = Pattern.compile("^03[0-9]{9}$");





    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static boolean validateMobileNo(String mobileNo) {
        Matcher matcher = VALID_MOBILE_ONLY_ZERO_STARTING_REGEX.matcher(mobileNo);
        return matcher.find();
    }


    public static Class getResponseClass(String type) {
        if (type.equals(Constants.apiCallLogin)) {
            return apiCallLoginResponse
                    .class;
        } else if (type.equals(Constants.apiCallSignUp)) {
            return apiCallSignUpResponse
                    .class;
        } else if (type.equals(Constants.apiError)) {
            return apiCallErrorResponse.class;
        } else if (type.equals(Constants.apiCallProviders)) {
            return apiCallProvidersResponse.class;
        } else if (type.equals(Constants.apiCallProviderType)) {
            return apiCallProviderTypesResponse.class;
        } else if (type.equals(Constants.apiCallMakeAppointment)) {
            return apiCallMakeAppointmentResponse.class;
        } else if (type.equals(Constants.apiCallMyAppointment)) {
            return apiCallMyAppointmentResponse.class;
        } else if (type.equals(Constants.apiCallMyServices)) {
            return apiCallMyServicesResponse.class;
        } else if (type.equals(Constants.apiCallAppointmentStatus)) {
            return apiCallAppointmentStatusResponse.class;
        } else if (type.equals(Constants.apiCallCreateProvider)) {
            return apiCallCreateProviderResponse.class;
        }


        return null;
    }


    public static String getCurrentDateTime(int type) {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = null;
        if (type == 1) {
            df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        } else if (type == 2) {
            df = new SimpleDateFormat("ddMMyyyyHHmmss");
        } else if (type == 3) {
            df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        } else if (type == 4) {
            df = new SimpleDateFormat("yyyy-MM-dd");
        } else if (type == 5) {
            df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        }
        String formattedDate = df.format(c);
        return formattedDate;
    }


}