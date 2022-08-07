package info.umer.carhakeem.Helpers;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import info.umer.carhakeem.Interfaces.iMapHelper;


public class MapHelper implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;

    iMapHelper _iMapHelper;
    public boolean allSet;
    private LatLng mLatLng = null;
    private Context context;
    public Activity activity;
    private LocationManager lm;
    public LatLng ltlng = new LatLng(24.868513, 67.078557);
    private FusedLocationProviderClient mFusedLocationClient;

    public MapHelper(Context con) {
        allSet = false;

        context = con;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);


        this.mGoogleApiClient = new GoogleApiClient.Builder(con)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .addOnConnectionFailedListener(this)
                .build();
        mLocationRequest = createLocationRequest(0, 0);
        apiConnect();


    }




    public void apiConnect() {
        mGoogleApiClient.connect();


    }

    public void stopLocationUpdates() {


        try {

            Log.i("Location Update", "Stop");
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
        catch (SecurityException e) {
            Log.i("mapHelper", "Exception " + e.getMessage());
        } catch (Exception e) {
            Log.i("Excpt", "startloc");

        }

        // }
    }

    public void startLocationUpdate() {
        try {

            if (mGoogleApiClient.isConnected()) {

                Log.i("mapHelper", "locUpdateOn");


                mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());


            }
        } catch (SecurityException e) {
            Log.i("mapHelper", "Exception " + e.getMessage());
        }
    }

    LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            for (Location location : locationResult.getLocations()) {
                Log.i("MapsActivity", "Location: " + location.getLatitude() + " " + location.getLongitude());





                ltlng = new LatLng(location.getLatitude(), location.getLongitude());

                stopLocationUpdates();

            }
        }

        ;

    };


    private LocationRequest createLocationRequest(int Interval, int fastestInterval) {
        LocationRequest mLocationRequest = LocationRequest.create();
        mLocationRequest.setInterval(Interval);
        mLocationRequest.setFastestInterval(fastestInterval);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return mLocationRequest;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i("mapHelper", "googleApiConnected");

    }


    @Override
    public void onConnectionSuspended(int i) {
        Log.i("mapHelper", "googleApiSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("mapHelper", "googleApiFailed");
    }


}


