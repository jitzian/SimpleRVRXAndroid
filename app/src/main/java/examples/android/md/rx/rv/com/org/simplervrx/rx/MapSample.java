package examples.android.md.rx.rv.com.org.simplervrx.rx;

import java.util.ArrayList;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

/**
 * Created by User on 11/16/2016.
 */

public class MapSample {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);


        Observable<Integer> hello = Observable
                .from(integers)
                .map(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer * integer;
                    }
                });


        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }
        };

        hello.subscribe(observer);

    }
}
