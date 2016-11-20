package examples.android.md.rx.rv.com.org.simplervrx.MVP.view;

/**
 * Created by User on 11/20/2016.
 */

public interface LoginView {
    void showProgress();
    void hideProgress();
    void setErrorUser();
    void setErrorPassword();
    void navigateToHome();
}
