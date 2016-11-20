package examples.android.md.rx.rv.com.org.simplervrx.MVP.presenter;

import android.util.Log;

import examples.android.md.rx.rv.com.org.simplervrx.MVP.model.LoginInteractor;
import examples.android.md.rx.rv.com.org.simplervrx.MVP.model.LoginInteractorImpl;
import examples.android.md.rx.rv.com.org.simplervrx.MVP.util.OnLoginFinishListener;
import examples.android.md.rx.rv.com.org.simplervrx.MVP.view.LoginView;

/**
 * Created by User on 11/20/2016.
 */

public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishListener {
    private static final String TAG = LoginPresenterImpl.class.getSimpleName();
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void validateUserAndPassword(String userName, String password) {
        Log.d(TAG, "validateUserAndPassword");
        if(loginView != null){
            loginView.showProgress();
        }
        loginInteractor.validateUserAndPassword(userName, password, this);

    }

    @Override
    public void setErrorUser() {
        Log.d(TAG, "setErrorUser");
        if(loginView != null){
            loginView.hideProgress();
            loginView.setErrorUser();
        }

    }

    @Override
    public void setErrorPassword() {
        Log.d(TAG, "setErrorPassword");
        if(loginView != null){
            loginView.setErrorPassword();
        }

    }

    @Override
    public void onSuccess() {
        Log.d(TAG, "onSuccess");
        if(loginView != null){
            loginView.navigateToHome();
        }

    }
}

