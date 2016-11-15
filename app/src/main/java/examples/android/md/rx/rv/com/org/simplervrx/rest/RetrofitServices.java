package examples.android.md.rx.rv.com.org.simplervrx.rest;

import java.util.ArrayList;
import java.util.List;

import examples.android.md.rx.rv.com.org.simplervrx.model.ApiConstants;
import examples.android.md.rx.rv.com.org.simplervrx.model.Example;
import examples.android.md.rx.rv.com.org.simplervrx.model.Result;
import examples.android.md.rx.rv.com.org.simplervrx.model.WeatherResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by User on 11/7/2016.
 */

public interface RetrofitServices {
//    @GET(ApiConstants.URL_HYPED_ARTISTS)
//    void getHypedArtists(@Query(ApiConstants.PARAM_API_KEY) String key, Callback<HypedArtistResponse> serverResponse);
//
//    @GET(ApiConstants.URL_TOP_ARTIST)
//    void getTopArtists(@Query(ApiConstants.PARAM_API_KEY) String key,Callback<TopArtistsResponse> serverRespones);
//
//    @GET(ApiConstants.URL_ARTIST_INFO)
//    void getArtistInfo(@Query(ApiConstants.PARAM_API_KEY) String key, @Query(ApiConstants.PARAM_ARTIST) String artistName, Callback<Artist> serverResponse);
//
//    @GET(ApiConstants.URL_HYPED_ARTISTS)
//    Observable<HypedArtistResponse> getHypedArtists(@Query(ApiConstants.PARAM_API_KEY) String key);
//
//    @GET(ApiConstants.URL_ARTIST_INFO)
//    Observable<Artist> getArtistInfo(@Query(ApiConstants.PARAM_API_KEY) String key,@Query(ApiConstants.PARAM_ARTIST) String name);
//
//    @GET(ApiConstants.URL_TOP_ARTIST)
//    Observable<TopArtistsResponse> getTopArtists(@Query(ApiConstants.PARAM_API_KEY) String key);

    @GET("./")
    Call<Example> getPost();

    @GET("users/{user}/repos/")
    Call<ArrayList<Result>> getRepositories(@Path("user")String user);

    @GET("/users/{user}/repos")
    Observable<ArrayList<Result>>getGithubRepositories(@Path("user") String user);

    //Github Endpoint
    //https://gist.githubusercontent.com/libinbensin/21efcf3e57cbda43c0df075aca1923db/raw/3e4adb89c32a8ea77210785595fdffa3a626ab54/cheese_list.json
    @GET("{user}/21efcf3e57cbda43c0df075aca1923db/raw/3e4adb89c32a8ea77210785595fdffa3a626ab54/cheese_list.json")
    Call<ArrayList<String>> getCheese(@Path("user") String username);

    @GET("?json=get_category_posts&slug=news&count=2")
    Call <List<Example>> getStackTips();
//        Call <Example[]> getStackTips();


    //http://api.openweathermap.org/data/2.5/weather?q=London?id=524901&APPID=bb96d030c601a99aa1ba714abe2f334c
//        @GET("weather?")
    @GET("?q=London?id=524901&APPID=bb96d030c601a99aa1ba714abe2f334c")
//        Observable<WeatherResult> getWeatherData(@Query("q")String city);
    Observable<WeatherResult> getWeatherData();

    @GET("users/{user}/starred")
    Observable<List<Result>> getStarredRepositories(@Path("user") String userName);
}
