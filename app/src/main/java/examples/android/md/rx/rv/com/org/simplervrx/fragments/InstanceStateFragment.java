package examples.android.md.rx.rv.com.org.simplervrx.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import examples.android.md.rx.rv.com.org.simplervrx.R;
import rx.Subscription;
import rx.functions.Action1;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InstanceStateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InstanceStateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InstanceStateFragment extends Fragment {
    private static final String TAG = InstanceStateFragment.class.getSimpleName();
    private View rootView;

    @BindView(R.id.mTextView)
    TextView mTextView;

    @BindView(R.id.mEditText)
    EditText mEditText;

    @BindView(R.id.mButton)
    Button mButton;
    private Subscription mButtonSubscription;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public InstanceStateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InstanceStateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InstanceStateFragment newInstance(String param1, String param2) {
        InstanceStateFragment fragment = new InstanceStateFragment();
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
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_instance_state, container, false);
        ButterKnife.bind(this, rootView);
        if((savedInstanceState != null) && (savedInstanceState.getString("savedText") != null)){
            mEditText.setText(savedInstanceState.get("savedText").toString());
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");

        if((savedInstanceState != null) && (savedInstanceState.getString("savedText") != null)){
            mEditText.setText(savedInstanceState.get("savedText").toString());
        }

        mButtonSubscription = RxView.clicks(mButton).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Toast.makeText(getContext(), "Click", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(TAG, "onViewStateRestored");
        if((savedInstanceState != null) && (savedInstanceState.getString("savedText") != null)){
            mEditText.setText(savedInstanceState.get("savedText").toString());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        outState.putString("savedText", mEditText.getText().toString());
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
    public void onDestroyView() {
        super.onDestroyView();
        mButtonSubscription.unsubscribe();

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
