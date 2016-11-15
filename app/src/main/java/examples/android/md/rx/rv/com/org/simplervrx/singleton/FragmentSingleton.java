package examples.android.md.rx.rv.com.org.simplervrx.singleton;

import android.content.Context;

import java.util.List;

import examples.android.md.rx.rv.com.org.simplervrx.model.Result;

/**
 * Created by User on 11/13/2016.
 */

public class FragmentSingleton {
    private static FragmentSingleton instance = null;

    public static FragmentSingleton getInstance(Context context){
        if(instance == null){
            synchronized (FragmentSingleton.class){
                instance = new FragmentSingleton();
            }
        }
        return instance;
    }

    private static Result result;

    public static FragmentSingleton getInstance() {
        return instance;
    }

    public static void setInstance(FragmentSingleton instance) {
        FragmentSingleton.instance = instance;
    }

    public static Result getResult() {
        return result;
    }

    public static void setResult(Result result) {
        FragmentSingleton.result = result;
    }
}
