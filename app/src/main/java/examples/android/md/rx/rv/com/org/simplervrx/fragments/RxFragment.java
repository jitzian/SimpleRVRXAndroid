package examples.android.md.rx.rv.com.org.simplervrx.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import examples.android.md.rx.rv.com.org.simplervrx.R;
import examples.android.md.rx.rv.com.org.simplervrx.model.Result;
import examples.android.md.rx.rv.com.org.simplervrx.model.WeatherResult;
import examples.android.md.rx.rv.com.org.simplervrx.rest.RetrofitRest;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RxFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RxFragment extends Fragment {
    private static final String TAG = RxFragment.class.getSimpleName();
    private View rootView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Button mButton;

    public RxFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RxFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RxFragment newInstance(String param1, String param2) {
        RxFragment fragment = new RxFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_rx, container, false);
        mButton = (Button) rootView.findViewById(R.id.mButton);
        //Not a best practice
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //-------------------

//        mButton = (Button) rootView.findViewById(R.id.mButton);
//        Subscription mButtonSubscription = RxView.clicks(mButton).subscribe(new Action1<Void>() {
//            @Override
//            public void call(Void aVoid) {
//                Log.d(TAG, "After a clicking");
//            }
//        });

//        Retrofit retrofit = new Retrofit
//                .Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("http://api.openweathermap.org/data/2.5/weather/")
//                .build();
//
//        RetrofitRest.RetrofitService retrofitService = retrofit.create(RetrofitRest.RetrofitService.class);
//        Observable<WeatherResult> city = retrofitService.getWeatherData();
//
//        city.subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(weatherResult -> {
//                    Log.e("Current Weather", weatherResult.getWeather()
//                            .get(0)
//                            .getDescription());
//                });
//        Gson gson =
//                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
//
//        Retrofit retrofit = new Retrofit
//                .Builder()
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("https://api.github.com/")
//                .build();
//
//        RetrofitRest.RetrofitService retrofitService = retrofit.create(RetrofitRest.RetrofitService.class);

        testRX();
        return rootView;
    }

    public void testRX(){
        Log.d(TAG, "testRX");

        Observable<Integer> observableInteger = Observable.create(new Observable.OnSubscribe<Integer>(){
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                Log.d(TAG, "call");
                subscriber.onNext(1);
                subscriber.onNext(2);
                subscriber.onNext(3);
                subscriber.onCompleted();
            }
        });

        Observable<Integer> observable1 = observableInteger.just(1,2,3);
        observable1.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError");
            }

            @Override
            public void onNext(Integer integer) {
                Log.d(TAG, "onNext::"+integer);
            }
        });


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
