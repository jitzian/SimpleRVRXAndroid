package examples.android.md.rx.rv.com.org.simplervrx.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import examples.android.md.rx.rv.com.org.simplervrx.R;
import examples.android.md.rx.rv.com.org.simplervrx.adapter.RVBehaviorAdapter;
import examples.android.md.rx.rv.com.org.simplervrx.listeners.ClickListener;
import examples.android.md.rx.rv.com.org.simplervrx.listeners.RecyclerTouchListener;
import examples.android.md.rx.rv.com.org.simplervrx.model.Result;
import examples.android.md.rx.rv.com.org.simplervrx.rest.RetrofitHelper;
import examples.android.md.rx.rv.com.org.simplervrx.singleton.FragmentSingleton;
import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RVBehaviorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RVBehaviorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RVBehaviorFragment extends Fragment {
    private static final String TAG = RVBehaviorFragment.class.getSimpleName();
    private View rootView;
    private RecyclerView mRVFragmentBehavior;
    private RecyclerView.LayoutManager layoutManager;
    private RVBehaviorAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RVBehaviorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RVBehaviorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RVBehaviorFragment newInstance(String param1, String param2) {
        RVBehaviorFragment fragment = new RVBehaviorFragment();
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
        rootView = inflater.inflate(R.layout.fragment_rvbehavior, container, false);
        return rootView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");

        initRV();

    }


    private ArrayList<Result> lstRes;
    public void initRV(){
        mRVFragmentBehavior = (RecyclerView) rootView.findViewById(R.id.mRVFragmentBehavior);

        Observable<ArrayList<Result>>lstResObservable = RetrofitHelper.Factory.create("jitzian");

        lstResObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ArrayList<Result>>() {
                    @Override
                    public void call(ArrayList<Result> results) {
                        Log.d(TAG, "call");
                        lstRes = results;
                        layoutManager = new LinearLayoutManager(getContext());
                        mRVFragmentBehavior.setLayoutManager(layoutManager);

                        adapter = new RVBehaviorAdapter(getContext(), results);
                        mRVFragmentBehavior.setAdapter(adapter);
                        setClickEventOnRecyclerView(mRVFragmentBehavior);
                    }
                });

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

    public void setClickEventOnRecyclerView(RecyclerView mRecyclerViewBehavior) {

        mRecyclerViewBehavior.addOnItemTouchListener(new RecyclerTouchListener(rootView.getContext(),
                mRecyclerViewBehavior, new ClickListener() {

            @Override
            public void onClick(View view, int position) {
                Log.d(TAG, "addOnItemTouchListener::onClick");

                Toast.makeText(rootView.getContext(),
                        "Single Click on position: " + position + " -- "
                                + lstRes.get(position).getOwner().getLogin(),
                        Toast.LENGTH_SHORT).show();

                Fragment fragment = new RVBehaviorDetailFragment();
                getFragmentManager()
                        .beginTransaction()
                        .addToBackStack(TAG)
//                        .remove(getFragmentManager().findFragmentByTag(TAG))
                        .replace(R.id.container_body, fragment, fragment.getClass().getSimpleName())
                        .commit();




//                FragmentSingleton.setResult(lstRes.get(position));
//
//                Fragment fragment = getFragmentManager()
//                        .findFragmentByTag("BehaviorFragment");
//                getFragmentManager().beginTransaction().hide(fragment);
//
//                getFragmentManager()
//                        .beginTransaction()
//                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
//                        .addToBackStack(TAG)
//                        .remove(fragment)
//                        .add(R.id.container_body, new DetailGithubFragment(), "DetailGithubFragment")
//                        .commit();
//
//                getActivity().sendBroadcast(new Intent().setAction("my.broad.cast.test"));
            }

            @Override
            public void onLongClick(View view, int position) {
                Log.d(TAG, "addOnItemTouchListener::onLongClick");
                Toast.makeText(rootView.getContext(), "Long press on position :" + position, Toast.LENGTH_LONG).show();
            }
        }));

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
