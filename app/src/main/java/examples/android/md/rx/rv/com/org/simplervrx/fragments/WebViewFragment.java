package examples.android.md.rx.rv.com.org.simplervrx.fragments;

import android.content.Context;
import android.database.Observable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import examples.android.md.rx.rv.com.org.simplervrx.R;
import rx.Subscription;
import rx.functions.Action1;
import rx.observers.Observers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WebViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WebViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebViewFragment extends Fragment {
    private static final String TAG = WebViewFragment.class.getSimpleName();
    private View rootView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private WebView mWebView;
    private Button mButton;
    private TextView textView;

    //Subscription
    Subscription mButtonSubscription = null;

    public WebViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WebViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WebViewFragment newInstance(String param1, String param2) {
        WebViewFragment fragment = new WebViewFragment();
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
        rootView = inflater.inflate(R.layout.fragment_web_view, container, false);
        mWebView = (WebView) rootView.findViewById(R.id.mWebView);
        mButton = (Button) rootView.findViewById(R.id.mButton);
        textView = (TextView) rootView.findViewById(R.id.textView);

        mButtonSubscription = RxView.clicks(mButton).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                Log.d(TAG, "mButtonSubscription");
                mWebView.getSettings().setLoadsImagesAutomatically(true);
                mWebView.getSettings().setJavaScriptEnabled(true);
                mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                mWebView.loadUrl("https://hipertextual.com/");
//                mWebView.loadData();

                //Keep the navigation into the webview
                mWebView.setWebViewClient(new WebViewClient());

                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("Button clicked");
                    }
                });
            }
        });

        rootView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                Log.d(TAG, "onKey");
                if((i == KeyEvent.KEYCODE_BACK) && (mWebView.canGoBack()) ){
                    mWebView.goBack();
                    return true;
                }
                return rootView.onKeyDown(i, keyEvent);
            }
        });

        return rootView;
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
        mListener = (OnFragmentInteractionListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
