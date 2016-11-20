package examples.android.md.rx.rv.com.org.simplervrx.MVP.presenter;

import examples.android.md.rx.rv.com.org.simplervrx.MVP.util.OnLoginFinishListener;

/**
 * Created by User on 11/19/2016.
 */

public interface LoginPresenter {
    void validateUserAndPassword(String userName, String password);
}
