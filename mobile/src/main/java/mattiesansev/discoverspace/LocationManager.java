package mattiesansev.discoverspace;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/*
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

*/
/**
 * Created by mattiesanseverino on 4/1/17.
 *//*


public class LocationManager extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    //Instantiate the Request Que
    GoogleApiClient mGoogleApiClient;
    private double myLatitude;
    private Location myLocation;
    private double myLongitude;
    private String result;
    public LocationManager(){
        myLongitude = 0.;
        myLatitude = 0.;
        result = "";
    }
    //public void setLat(){
//
       //// myLatitude = 0;//getlocation for latitude;
    //}
   // public void setLong(){
      //  myLongitude = 0;//get Location for longitude;
    //}
    public void setLocation(){
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) this)
                    .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this)
                    .addApi(LocationServices.API)
                    .build();
        }

        onStart();}

    public void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    public void EnterApp(View v) {
        onStop();
        Intent i = new Intent(MainActivity.this, Map.class);
        startActivity(i);
    }

    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("ChrisRulesKaseyDrools", "connectin failed");
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_COARSE_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        myLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (myLocation != null) {
            myLatitude = myLocation.getLatitude();
            myLongitude = myLocation.getLongitude();
        }
        //mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
        //mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("ChrisRulesKaseyDrools", "connectionSuspended");
    }

    public double getLat() {
        if (myLocation != null) {
            return myLatitude;
        }
        else
            return 0;

    }

    public double getLong() {
        if (myLocation != null) {
            return myLongitude;
        } else
            return 0;
    }
}
*/

//IP:/data?longitude-12.12321
