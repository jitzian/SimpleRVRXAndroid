package examples.android.md.rx.rv.com.org.simplervrx.MVP.view;

import android.content.Context;
import android.content.Intent;
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
import android.widget.ProgressBar;

import examples.android.md.rx.rv.com.org.simplervrx.MVP.presenter.LoginPresenter;
import examples.android.md.rx.rv.com.org.simplervrx.MVP.presenter.LoginPresenterImpl;
import examples.android.md.rx.rv.com.org.simplervrx.R;

import static android.R.attr.password;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SimpleMVPFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SimpleMVPFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimpleMVPFragment extends Fragment implements LoginView{
    private static final String TAG = SimpleMVPFragment.class.getSimpleName();
    private EditText mEditTextMVPUsername, mEditTextMVPPassword;
    private Button mButtonMVPLogin;
    private ProgressBar mProgressBarMVP;
    private LoginPresenter loginPresenter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SimpleMVPFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SimpleMVPFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SimpleMVPFragment newInstance(String param1, String param2) {
        SimpleMVPFragment fragment = new SimpleMVPFragment();
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
        return inflater.inflate(R.layout.fragment_simple_mvp, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        private EditText mEditTextMVPUsername, mEditTextMVPPassword;
//        private Button mButtonMVPLogin;
//        ProgressBar mProgressBarMVP;
        Log.d(TAG, "onViewCreated");
        mEditTextMVPUsername = (EditText) view.findViewById(R.id.mEditTextMVPUsername);
        mEditTextMVPPassword = (EditText) view.findViewById(R.id.mEditTextMVPPassword);
        mButtonMVPLogin = (Button) view.findViewById(R.id.mButtonMVPLogin);
        mProgressBarMVP = (ProgressBar) view.findViewById(R.id.mProgressBarMVP);
        loginPresenter = new LoginPresenterImpl(this);

        mButtonMVPLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.validateUserAndPassword(mEditTextMVPUsername.getText().toString(), mEditTextMVPPassword.getText().toString());
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

    @Override
    public void showProgress() {
        mProgressBarMVP.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBarMVP.setVisibility(View.GONE);
    }

    @Override
    public void setErrorUser() {
        mEditTextMVPUsername.setError("Required field");
    }

    @Override
    public void setErrorPassword() {
        mEditTextMVPPassword.setError("Required field");
    }

    @Override
    public void navigateToHome() {
//        startActivity(new Intent(PrincipalMenu.class, this));
//        startActivity(new Intent(this, PrincipalMenu.class));
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
