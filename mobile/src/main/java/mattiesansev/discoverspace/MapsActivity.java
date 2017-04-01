package mattiesansev.discoverspace;

import android.location.Location;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.support.v7.app.AppCompatActivity;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i("MapWorks", "The map works");
        mMap = googleMap;
    //center camera at her stuff
        //add pins
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private GoogleApiClient client;
    Location loc;
    double myLat;
    double myLong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        Gson gson = new GsonBuilder().setLenient().create();

        // Because Kasey is lazyyyyyyy
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://10.203.114.108:5000/")
                .client(okHttpClient)
                .build();
        final Discovery disco = retrofit.create(Discovery.class);

        disco.sample().enqueue(new Callback<List<myData>>() {
            @Override
            public void onResponse(Call<List<myData>> call, Response<List<myData>> response) {
                for (myData d : response.body()) {
                    Log.i("ChrisRulesKaseyDrools", d.event);
                }
                Log.i("Chris", response.message());
            }

            @Override
            public void onFailure(Call<List<myData>> call, Throwable t) {
                Log.i("ChrisSucks", t.getMessage());
            }

        });
//        LocationManager manager = new LocationManager();
//        manager.setLocation();
//        double myLat = manager.getLat();
//        double myLong = manager.getLong();
        if (!LocationProvider.hasPermissions(this)) {
            LocationProvider.checkPermissions(this);
        } else {
            LocationProvider.onPermissionsChanged(this);
            LocationProvider locProv = LocationProvider.getInstance();
            try {
                loc = locProv.getLastLocation();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(loc != null){
                myLat = loc.getLatitude();
                myLong = loc.getLongitude();
            }
        }
        disco.search(myLat, myLong).enqueue(new Callback<List<myData>>() {
            @Override
                public void onResponse(Call<List<myData>> call, Response<List<myData>> response) {
                if(null != response.body()) {
                    for (myData d : response.body()) {
                        Log.i("ChrisRulesKaseyDrools", d.event);
                    }
                }

                Log.i("Chris", response.message());
            }

            @Override
            public void onFailure(Call<List<myData>> call, Throwable t) {
                Log.i("ChrisSucks", t.getMessage());
            }

            public void onRequestPermissionsResult(int requestCode,
                                                   String permissions[], int[] grantResults) {
                Log.i("tag", "Permissions changed");
                if (LocationProvider.hasPermissions(getParent())) {
                    Log.i("tag", "Permissions granted");
                    LocationProvider.onPermissionsChanged(getParent());
                }
                else {
                    Log.e("tag", "Permissions denied");
                }
            }

        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Map Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


}
