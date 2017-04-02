package mattiesansev.discoverspace;

import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Map extends AppCompatActivity {

    private GoogleApiClient client;
    Location loc;
    double myLat;
    double myLong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://192.168.0.101:5000/")
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
//        disco.search(myLat, myLong).enqueue(new Callback<List<myData>>() {
//            @Override
//                public void onResponse(Call<List<myData>> call, Response<List<myData>> response) {
//                    for (myData d : response.body()) {
//                        Log.i("ChrisRulesKaseyDrools", d.event);
//                    }
//                Log.i("Chris", response.message());
//            }
//
//            @Override
//            public void onFailure(Call<List<myData>> call, Throwable t) {
//                Log.i("ChrisSucks", t.getMessage());
//            }
//
//            public void onRequestPermissionsResult(int requestCode,
//                                                   String permissions[], int[] grantResults) {
//                Log.i("tag", "Permissions changed");
//                if (LocationProvider.hasPermissions(getParent())) {
//                    Log.i("tag", "Permissions granted");
//                    LocationProvider.onPermissionsChanged(getParent());
//                }
//                else {
//                    Log.e("tag", "Permissions denied");
//                }
//            }
//
//        });
        client = new GoogleApiClient.Builder(this).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        client.connect();
    }

    @Override
    public void onStop() {
        super.onStop();

        client.disconnect();
    }
}
