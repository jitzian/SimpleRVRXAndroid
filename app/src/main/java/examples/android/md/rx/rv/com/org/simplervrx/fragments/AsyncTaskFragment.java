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
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import examples.android.md.rx.rv.com.org.simplervrx.R;
import examples.android.md.rx.rv.com.org.simplervrx.asyncTask.AsyncResponse;
import examples.android.md.rx.rv.com.org.simplervrx.asyncTask.JsonAsyncTask;
import examples.android.md.rx.rv.com.org.simplervrx.services.GithubService;
import rx.Subscription;
import rx.functions.Action1;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AsyncTaskFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AsyncTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AsyncTaskFragment extends Fragment implements AsyncResponse{
    private static final String TAG = AsyncTaskFragment.class.getSimpleName();
    private View rootView;
    private JsonAsyncTask jsonAsyncTask = new JsonAsyncTask("https://api.github.com/users/dmnugent80/repos");
    private Button mButton, mButtonService;
    private Subscription mbuttonSubscription, mButtonServiceSubscription;
    private TextView mTextView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AsyncTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AsyncTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AsyncTaskFragment newInstance(String param1, String param2) {
        AsyncTaskFragment fragment = new AsyncTaskFragment();
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
//        jsonAsyncTask.asyncResponse = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView");
        rootView = inflater.inflate(R.layout.fragment_async_task, container, false);
        mTextView = (TextView) rootView.findViewById(R.id.mTextView);
        mButton = (Button) rootView.findViewById(R.id.mButton);
        mButtonService = (Button) rootView.findViewById(R.id.mButtonService);
        jsonAsyncTask.asyncResponse = this;

        //Its necessary to register the receiver
        getActivity().registerReceiver(broadcastReceiver, new IntentFilter("github.service.update.ui"));

        mbuttonSubscription = RxView.clicks(mButton).subscribe(new Action1<Void>() {

            @Override
            public void call(Void aVoid) {
                jsonAsyncTask.execute();
            }
        });


        mButtonService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().startService(new Intent(getActivity(), GithubService.class));
                mButtonService.setEnabled(false);
            }
        });
//        LocalBroadcastManager.getInstance(getActivity()).registerReceiver();

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
    public void processFinished(JSONArray jsonArray) {
        Log.d(TAG, "processFinished");

        if(jsonArray != null){
            try {
                for(int i = 0; i < jsonArray.length(); i ++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String projectURL = jsonObject.getString("html_url");
                    Log.d(TAG, "::-->::" + projectURL);
                }

                }catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mTextView.setText(jsonArray.getClass().toString());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mbuttonSubscription.unsubscribe();
        jsonAsyncTask.cancel(true);
        getActivity().unregisterReceiver(broadcastReceiver);
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

    class LocalAsyncTaskFragmentReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive");
//            Bundle bundle = intent.getExtras();
//            bundle.get("newText");
            mTextView.setText(intent.getExtras().get("newText").toString());
        }
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "--------------------::broadcastReceiver::onReceive::--------------------");
            //Update UI
            Bundle bundle = intent.getExtras();
            Log.d(TAG, "jsonArray::" + bundle.get("jsonArray"));
            try {
                String strJsonArray = bundle.getString("jsonArray");
                JSONArray jsonArray = new JSONArray(strJsonArray);
                Log.d(TAG, "jsonArray::" + jsonArray);
                mButtonService.setEnabled(true);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    };
}
