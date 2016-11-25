package examples.android.md.rx.rv.com.org.simplervrx.rest;

import android.util.Log;

import java.util.ArrayList;

import examples.android.md.rx.rv.com.org.simplervrx.model.Result;
import examples.android.md.rx.rv.com.org.simplervrx.model.SpotifyResult;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by User on 11/11/2016.
 */

public class RetrofitHelper {
    private static final String TAG = RetrofitHelper.class.getName();

    public static class Factory{
        public static Retrofit create(){
            Log.d(TAG, "::Factory::create");
            return new Retrofit.Builder()
                    .baseUrl("https://api.github.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build();
        }
        public static Observable<ArrayList<Result>>create(String user){
            Log.d(TAG, "Factory::create::user::" + user);
            Retrofit retrofit = create();
            RetrofitServices retrofitService = retrofit.create(RetrofitServices.class);
            return retrofitService.getGithubRepositories(user);
        }
        public static Observable<SpotifyResult>createSpotify(String qry){
            Log.d(TAG, "Factory::create::qry::" + qry);
            Retrofit retrofit = create();
            SpotifyApiService spotifyApiService = retrofit.create(SpotifyApiService.class);
            return spotifyApiService.searchArtist(qry);
        }
    }


}
