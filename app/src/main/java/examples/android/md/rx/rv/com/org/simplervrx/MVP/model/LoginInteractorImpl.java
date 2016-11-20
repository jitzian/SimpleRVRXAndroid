package examples.android.md.rx.rv.com.org.simplervrx.MVP.model;

import android.os.Handler;
import android.util.Log;

import examples.android.md.rx.rv.com.org.simplervrx.MVP.util.OnLoginFinishListener;

/**
 * Created by User on 11/19/2016.
 */

public class LoginInteractorImpl implements LoginInteractor {
    private static final String TAG = LoginInteractorImpl.class.getSimpleName();
    @Override
    public void validateUserAndPassword(final String userName,
                                        final String password,
                                        final OnLoginFinishListener onLoginFinishListener) {
        Log.d(TAG, "validateUserAndPassword");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!userName.equals("") && !password.equals("")){
                    onLoginFinishListener.onSuccess();
                }else{
                    if(userName.equals("")){
                        onLoginFinishListener.setErrorUser();
                    }
                    if(password.equals("")){
                        onLoginFinishListener.setErrorPassword();
                    }
                }
            }
        }, 2000);
    }
}
