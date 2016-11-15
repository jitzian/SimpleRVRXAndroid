package examples.android.md.rx.rv.com.org.simplervrx.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import examples.android.md.rx.rv.com.org.simplervrx.R;
import examples.android.md.rx.rv.com.org.simplervrx.eventBus.ResultEvent;
import examples.android.md.rx.rv.com.org.simplervrx.model.Result;
import examples.android.md.rx.rv.com.org.simplervrx.singleton.FragmentSingleton;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailGithubFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailGithubFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailGithubFragment extends Fragment {
    private static final String TAG = DetailGithubFragment.class.getSimpleName();
    private View rootView;
    private EditText mEditTextId, mEditTextName, mEditTextId2, mEditTextName2;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DetailGithubFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailGithubFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailGithubFragment newInstance(String param1, String param2) {
        DetailGithubFragment fragment = new DetailGithubFragment();
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
        Log.d(TAG, "onCreateView");
        rootView = inflater.inflate(R.layout.fragment_detail_github, container, false);

//        mEditTextId = (EditText) rootView.findViewById(R.id.mEditTextId);
//        mEditTextName = (EditText) rootView.findViewById(R.id.mEditTextName);
//
//        loadUI();
//        registerBroadcast();

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mEditTextId = (EditText) rootView.findViewById(R.id.mEditTextId);
        mEditTextName = (EditText) rootView.findViewById(R.id.mEditTextName);

        mEditTextId2 = (EditText) rootView.findViewById(R.id.mEditTextId2);
        mEditTextName2 = (EditText) rootView.findViewById(R.id.mEditTextName2);

        loadUI();
    }

    public void loadUI(){
        mEditTextName.setText(FragmentSingleton.getResult().getName());
        mEditTextId.setText(String.valueOf(FragmentSingleton.getResult().getId()));
    }

    public void registerBroadcast(){
//        LocalBroadcastManager
//                .getInstance(getContext())
//                .registerReceiver(mBroadcastReceiver, new IntentFilter(CUSTOM_EVENT_KEY));
        getActivity().registerReceiver(mBroadcastReceiver, new IntentFilter("my.broad.cast.test"));
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

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        getActivity().registerReceiver(mBroadcastReceiver, new IntentFilter("my.broad.cast.test"));
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void getInfo(ResultEvent event){
        Log.d(TAG, "-- getInfo --");
        mEditTextId2.setText(event.getResult().getId().toString() + " FCK");
        mEditTextName2.setText(event.getResult().getName().toString() + " FCK");
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
//        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mBroadcastReceiver);
        getActivity().unregisterReceiver(mBroadcastReceiver);
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

    public void getMessage(Result result){
        Log.d(TAG, "getMessage::" + result.getName());
//        mEditTextName.setText(result.getName());
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "BroadcastReceiver::onReceive");
        }
    };
}
