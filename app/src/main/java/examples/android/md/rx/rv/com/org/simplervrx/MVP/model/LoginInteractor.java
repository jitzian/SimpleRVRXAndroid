package examples.android.md.rx.rv.com.org.simplervrx.MVP.model;

import examples.android.md.rx.rv.com.org.simplervrx.MVP.util.OnLoginFinishListener;

/**
 * Created by User on 11/19/2016.
 */

public interface LoginInteractor {
    void validateUserAndPassword(String userName, String password, OnLoginFinishListener onLoginFinishListener);
}
