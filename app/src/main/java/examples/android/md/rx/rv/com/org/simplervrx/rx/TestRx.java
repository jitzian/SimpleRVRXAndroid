package examples.android.md.rx.rv.com.org.simplervrx.rx;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by User on 11/13/2016.
 */

public class TestRx {

    public static void main(String...args){

//        List<String> lstString = new ArrayList<>();
//        lstString.add("Bruce Wayne");
//        lstString.add("Peter Parker");
//        lstString.add("Tony Stark");
//        lstString.add("Nick Fury");

        //Example 1
        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello World1");
                subscriber.onNext("Hello World1*");
                subscriber.onNext("-----");
                subscriber.onCompleted();
            }
        });

        Subscriber mySubscriber = new Subscriber() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onCompleted::" + e.getMessage());
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext::" + o.toString());
            }
        };

        myObservable.subscribe(mySubscriber);

        //Example 2
        Observable<String>myObservable2 = Observable.just("Hello World2");
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        };

        myObservable2.subscribe(onNextAction);

        //Example 3
        Observable<String>myObservable3 = Observable.just("Hello World3");
        myObservable3.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        //Rx with Methods
        Observable<String>myObservable4 =getObservableString_1();
        Subscriber mySubscriber4 = new Subscriber() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError::" + e.getMessage());
            }

            @Override
            public void onNext(Object o) {
                System.out.println("onNext::" + o.toString());
            }
        };
        myObservable4.subscribe(mySubscriber4);

    }

    static Observable<String> getObservableString_1(){
        Observable<String>myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello World Method 1");
                subscriber.onCompleted();
            }
        });
        return myObservable;
    }

    static Observable<List<String>>getObservableList_2(){
        Observable<List<String>>myObservable = Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                subscriber.onNext(new ArrayList<String>());

            }
        });
        return myObservable;
    }

//    public static

}
