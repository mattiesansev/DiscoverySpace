package mattiesansev.discoverspace;
import mattiesansev.discoverspace.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mattiesanseverino on 4/1/17.
 */

public class Tester {
    Gson gson = new GsonBuilder().create();
    Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("http://10.203.114.108.5000/sample")
            .build();
    final Discovery disco = retrofit.create(Discovery.class);

}
