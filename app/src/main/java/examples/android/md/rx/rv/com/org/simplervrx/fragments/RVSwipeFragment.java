package examples.android.md.rx.rv.com.org.simplervrx.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import examples.android.md.rx.rv.com.org.simplervrx.R;
import examples.android.md.rx.rv.com.org.simplervrx.adapter.RVSwipeAdapter;
import examples.android.md.rx.rv.com.org.simplervrx.listeners.SwipeRVHelper;
import examples.android.md.rx.rv.com.org.simplervrx.model.Result;
import examples.android.md.rx.rv.com.org.simplervrx.rest.RetrofitHelper;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RVSwipeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RVSwipeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RVSwipeFragment extends Fragment {
    private static final String TAG = RVSwipeFragment.class.getSimpleName();
    private RecyclerView mRecyclerViewSwipe;
    private RecyclerView.LayoutManager layoutManager;
    private RVSwipeAdapter rvSwipeAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RVSwipeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RVSwipeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RVSwipeFragment newInstance(String param1, String param2) {
        RVSwipeFragment fragment = new RVSwipeFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rvswipe, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");

        mRecyclerViewSwipe = (RecyclerView) view.findViewById(R.id.mRecyclerViewSwipe);
        initRV();

    }

    public void initRV(){
        Log.d(TAG, "initRV");
        //These lines are for configure the swiping
        SwipeRVHelper swipeRVHelper = new SwipeRVHelper( mRecyclerViewSwipe, getContext());
        swipeRVHelper.setUpItemTouchHelper();
        swipeRVHelper.setUpAnimationDecoratorHelper();
        //--

        Observable<ArrayList<Result>> resultObservable = RetrofitHelper.Factory
                .create("jitzian");

        resultObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<Result>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "initRV::onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "initRV::onError::" + e.getMessage());
                    }

                    @Override
                    public void onNext(ArrayList<Result> results) {
                        Log.d(TAG, "initRV::onNext::");
                        layoutManager = new LinearLayoutManager(getContext());
                        mRecyclerViewSwipe.setLayoutManager(layoutManager);
                        rvSwipeAdapter = new RVSwipeAdapter(results, getContext());
                        mRecyclerViewSwipe.setAdapter(rvSwipeAdapter);

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
