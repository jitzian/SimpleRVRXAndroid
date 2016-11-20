package examples.android.md.rx.rv.com.org.simplervrx.MVP.util;

/**
 * Created by User on 9/29/2016.
 */

public interface OnLoginFinishListener {

    void setErrorUser();
    void setErrorPassword();

    void onSuccess();
}
