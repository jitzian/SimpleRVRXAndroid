package examples.android.md.rx.rv.com.org.simplervrx.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import examples.android.md.rx.rv.com.org.simplervrx.R;
import examples.android.md.rx.rv.com.org.simplervrx.adapter.BehaviorAdapter;
import examples.android.md.rx.rv.com.org.simplervrx.eventBus.ResultEvent;
import examples.android.md.rx.rv.com.org.simplervrx.listeners.ClickListener;
import examples.android.md.rx.rv.com.org.simplervrx.listeners.RecyclerTouchListener;
import examples.android.md.rx.rv.com.org.simplervrx.model.Result;
import examples.android.md.rx.rv.com.org.simplervrx.rest.RetrofitHelper;
import examples.android.md.rx.rv.com.org.simplervrx.singleton.FragmentSingleton;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BehaviorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BehaviorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BehaviorFragment extends Fragment {
    public static final String TAG = BehaviorFragment.class.getSimpleName();
    private View rootView;
    private FloatingActionButton mFloatingActionButton, mFloatingActionButton2;
    private Subscription mFloatingActionButtonSubscription;
    private RecyclerView mRecyclerViewBehavior;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private ArrayList<Result> lstRes;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BehaviorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BehaviorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BehaviorFragment newInstance(String param1, String param2) {
        BehaviorFragment fragment = new BehaviorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView");
        rootView = inflater.inflate(R.layout.fragment_behavior, container, false);

        if(savedInstanceState == null){
            setCreateView();
            savedInstanceState = new Bundle();
            savedInstanceState.putParcelableArrayList("lstRes", (ArrayList)lstRes);
        }

        return rootView;
    }


    public void setCreateView(){
        Log.d(TAG, "setCreateView");
        mFloatingActionButton2 = (FloatingActionButton)rootView.findViewById(R.id.mFloatingActionButton2);

        mFloatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "sendBroadcast");
                getActivity().sendBroadcast(new Intent().setAction("my.broad.cast.test"));
            }
        });

        mFloatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.mFloatingActionButton);
        mFloatingActionButton.setVisibility(View.VISIBLE);
        mRecyclerViewBehavior = (RecyclerView) rootView.findViewById(R.id.mRecyclerViewBehavior);

        mFloatingActionButtonSubscription = RxView.clicks(mFloatingActionButton).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Snackbar.make(rootView, "Getting results from REST", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();

                Observable<ArrayList<Result>> resultObservable = RetrofitHelper.Factory
                        .create("jitzian");

                resultObservable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ArrayList<Result>>() {
                            @Override
                            public void onCompleted() {
                                Log.d(TAG, "onCompleted");
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d(TAG, "onError::" + e.getMessage());
                            }

                            @Override
                            public void onNext(ArrayList<Result> results) {
                                Log.d(TAG, "onNext");
                                lstRes = results;

                                layoutManager = new LinearLayoutManager(rootView.getContext());
                                mRecyclerViewBehavior.setLayoutManager(layoutManager);
                                layoutManager.setAutoMeasureEnabled(true);

                                adapter = new BehaviorAdapter(rootView.getContext(), results);
                                mRecyclerViewBehavior.setAdapter(adapter);
                                mFloatingActionButton.setVisibility(View.INVISIBLE);
                                setClickEventOnRecyclerView(mRecyclerViewBehavior);

//                                getActivity().sendBroadcast(new Intent().setAction("my.broad.cast.test"));

                            }
                        });
            }
        });
    }



    public void setClickEventOnRecyclerView(RecyclerView mRecyclerViewBehavior) {

        mRecyclerViewBehavior.addOnItemTouchListener(new RecyclerTouchListener(rootView.getContext(),
                mRecyclerViewBehavior, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                Log.d(TAG, "addOnItemTouchListener::onClick");
                mListener.onFragmentInteraction(lstRes.get(position));


                Toast.makeText(rootView.getContext(),
                        "Single Click on position: " + position + " -- "
                                + lstRes.get(position).getOwner().getLogin(),
                        Toast.LENGTH_SHORT).show();

//                BehaviorFragment fragment = new BehaviorFragment()
                FragmentSingleton.setResult(lstRes.get(position));

//                String CUSTOM_EVENT_KEY = "CUSTOM_ACTION_BROADCAST";
//                getActivity().sendBroadcast(new Intent().setAction("my.broad.cast.test"));

                Fragment fragment = getFragmentManager()
                        .findFragmentByTag("BehaviorFragment");
                getFragmentManager().beginTransaction().hide(fragment);

//                //EventBus
//                EventBus.getDefault().post(new ResultEvent(lstRes.get(position)));

                getFragmentManager()
                        .beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .addToBackStack(TAG)
//                        .replace(R.id.container_body, new DetailGithubFragment())
                        .remove(fragment)
                        .add(R.id.container_body, new DetailGithubFragment(), "DetailGithubFragment")
                        .commit();

                getActivity().sendBroadcast(new Intent().setAction("my.broad.cast.test"));
            }

            @Override
            public void onLongClick(View view, int position) {
                Log.d(TAG, "addOnItemTouchListener::onLongClick");
                Toast.makeText(rootView.getContext(), "Long press on position :" + position, Toast.LENGTH_LONG).show();
            }
        }));

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        setCreateView();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach");
        mListener = (OnFragmentInteractionListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach");
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        mFloatingActionButtonSubscription.unsubscribe();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("value", "value");
        Log.d(TAG, "onSaveInstanceState");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG, "onActivityCreated::savedInstanceState::" + savedInstanceState);
        if(savedInstanceState == null){
            savedInstanceState = new Bundle();

        }

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(TAG, "onViewStateRestored::savedInstanceState::" + savedInstanceState);

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
        void onFragmentInteraction(Result result);
    }

}
