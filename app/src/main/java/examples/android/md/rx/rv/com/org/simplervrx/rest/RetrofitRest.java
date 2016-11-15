package examples.android.md.rx.rv.com.org.simplervrx.rest;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import examples.android.md.rx.rv.com.org.simplervrx.model.Example;
import examples.android.md.rx.rv.com.org.simplervrx.model.Post;
import examples.android.md.rx.rv.com.org.simplervrx.model.Result;
import examples.android.md.rx.rv.com.org.simplervrx.model.WeatherResult;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by User on 11/4/2016.
 */


public class RetrofitRest {
    private Context context;
    public RetrofitRest(Context context) {
        this.context = context;
    }

    public interface RetrofitService{
        @GET("./")
        Call <Example> getPost();

        @GET("users/{user}/repos/")
        Call<ArrayList<Result>> getRepositories(@Path("user")String user);

        @GET("users/{user}/repos/")
        Observable<ArrayList<Result>>getGithubRepositories(@Path("user")String user);

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

}
