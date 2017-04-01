package mattiesansev.discoverspace;

/**
 * Created by mattiesanseverino on 4/1/17.
 */
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import mattiesansev.discoverspace.myData;
import retrofit2.http.Query;

public interface Discovery {
    @GET("/search")
    Call<List<myData>> search(@Query("lat") double latitude, @Query("long") double longitude);

    @GET("/sample")
    Call<List<myData>> sample();
}
/*
@GET("mobile/report/{id}")
Call<List<Report>> report(@Path("id") int id);
*/
