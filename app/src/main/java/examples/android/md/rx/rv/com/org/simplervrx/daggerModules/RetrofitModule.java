package examples.android.md.rx.rv.com.org.simplervrx.daggerModules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by User on 11/24/2016.
 */

@Module
public class RetrofitModule {
    String baseURL;

    public RetrofitModule(String baseURL) {
        this.baseURL = baseURL;
    }

    @Provides
    Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
   }

}
