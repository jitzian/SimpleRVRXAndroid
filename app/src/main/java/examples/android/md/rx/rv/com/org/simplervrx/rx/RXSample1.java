package examples.android.md.rx.rv.com.org.simplervrx.rx;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

/**
 * Created by User on 11/13/2016.
 */

public class RXSample1 {

    public static void main(String[] args) {

        String stringObservable = "My String";

        Observable<String>myObservableString1 = Observable.from(new String[]{stringObservable})
                .map(new Func1<String, String>() {
                    @Override
                    public String call(String s) {
                        return s + "..";
                    }
                });

        Observer<String> myObserverString1 = new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError::" + e.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext::" + s);
            }
        };

        myObservableString1.subscribe(myObserverString1);

    }

}
