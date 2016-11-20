package examples.android.md.rx.rv.com.org.simplervrx.rx;

import java.lang.reflect.Array;
import java.util.ArrayList;

import examples.android.md.rx.rv.com.org.simplervrx.model.Result;
import examples.android.md.rx.rv.com.org.simplervrx.model.Sys;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by User on 11/16/2016.
 */

public class MapSampleJSC {

    public static void main(String[] args) {
        ArrayList<String>lst = new ArrayList<>();
        lst.add("Margarita");
        lst.add("Irma");
        lst.add("Georgina");
        lst.add("Valeria");
        lst.add("Margot");
        lst.add("Roxana");

//        Observable<String> myObservable1 = Observable.from(lst)
//                .map(new Func1<String, String>() {
//                    @Override
//                    public String call(String s) {
//                        return s + "::Something..";
//                    }
//                });
//
//        Observer<String> myObserver1 = new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                System.out.println("onCompleted::");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("onError::" + e.getMessage());
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println("onNext::" + s);
//            }
//        };
//
//        myObservable1.subscribe(myObserver1);
        System.out.println("-------------------------------------");

        ArrayList<Result>lstResult = new ArrayList<>();
        Result result = new Result();
        result.setName("jona1");
        lstResult.add(result);

        result = new Result();
        result.setName("jona2");
        lstResult.add(result);

        result = new Result();
        result.setName("jona3");
        lstResult.add(result);

        result = new Result();
        result.setName("jona4");
        lstResult.add(result);

        result = new Result();
        result.setName("jona5");
        lstResult.add(result);


        ArrayList<Result>lstResult2 = new ArrayList<>();
        Result result2 = new Result();
        result2.setName("jona1_");
        lstResult2.add(result2);

        result2 = new Result();
        result2.setName("jona2_");
        lstResult2.add(result2);

        result2 = new Result();
        result2.setName("jona3_");
        lstResult2.add(result2);

        result2 = new Result();
        result2.setName("jona4_");
        lstResult2.add(result2);

        result2 = new Result();
        result2.setName("jona5_");
        lstResult2.add(result2);


        for(Result obj : lstResult){
            System.out.println(obj.getName());
        }

        System.out.println("SIZE::" + lstResult.size());

        Observable<Result>myObservable2 = Observable.from(lstResult)
                .map(new Func1<Result, Result>() {
                    @Override
                    public Result call(Result result) {
                        result.setName(result.getName() + "::HEY");
                        return result;
                    }
                });

        Observer<Result>myObserver2 = new Observer<Result>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError::" + e.getMessage());
            }

            @Override
            public void onNext(Result result) {
                System.out.println("onNext::" + result.getName());
            }
        };

        myObservable2.subscribe(myObserver2);

        System.out.println("-------------------------------------");

        Observable<Result> myObservable3 = Observable.from(lstResult2)
                .map(new Func1<Result, Result>() {
                    @Override
                    public Result call(Result result) {
                        result.setName(result.getName() + " -- ");
                        return result;
                    }
                });

        Observer<Result>myObserver3 = new Observer<Result>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError::" + e.getMessage());
            }

            @Override
            public void onNext(Result result) {
                System.out.println("onNext::" + result.getName());
            }
        };

        myObservable3.subscribe(myObserver3);

        myObservable2.concatWith(myObservable3)
                .subscribe(new Action1<Result>() {
                    @Override
                    public void call(Result result) {
                        System.out.println("Concat::" + result.getName());
                    }
                });

        System.out.println("-------------------------------------");

        ArrayList<Result>lstResult4 = new ArrayList<>();
        Result result4 = new Result();
        result4.setName("inKA1_");
        lstResult4.add(result4);

        result4 = new Result();
        result4.setName("inKA2_");
        lstResult4.add(result4);

        result4 = new Result();
        result4.setName("inKA3_");
        lstResult4.add(result4);

        result4 = new Result();
        result4.setName("inKA4_");
        lstResult4.add(result4);
        final int[] i = {0};
        Observable<Result>myObservable4 = Observable.from(lstResult4)
                .map(new Func1<Result, Result>() {
                    @Override
                    public Result call(Result result) {

                        result.setName("Darinka" + String.valueOf(i[0]));
                        result.setFullName("Fernandez Mosqueda");
                        i[0]++;
                        return result;
                    }
                });

        Observer<Result>myObserver4 = new Observer<Result>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError::" + e.getMessage());
            }

            @Override
            public void onNext(Result result) {
                System.out.println("onNext::" + result.getName() + " " + result.getFullName());
            }
        };

        myObservable4.subscribe(myObserver4);

        //--
        System.out.println("-------------------------------------");

        Observable<Result>myObservable5 = Observable
                .from(lstResult4)
                .map(new Func1<Result, Result>() {
                    @Override
                    public Result call(Result result) {
//                        String login = result.getOwner().getLogin();
                        result.setName(result.getName() + "::Login::");
                        return result;
                    }
                });

        Observer<Result>myObserver5 = new Observer<Result>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:;" + e.getMessage());
            }

            @Override
            public void onNext(Result result) {
                System.out.println("onNext::" + result.getName());
            }
        };

        myObservable5.subscribe(myObserver5);


    }
}
