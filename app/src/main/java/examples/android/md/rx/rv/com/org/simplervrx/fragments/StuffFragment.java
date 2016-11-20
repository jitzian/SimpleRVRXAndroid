package examples.android.md.rx.rv.com.org.simplervrx.fragments;

import android.app.Service;
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
import android.widget.TextView;

import examples.android.md.rx.rv.com.org.simplervrx.R;
import examples.android.md.rx.rv.com.org.simplervrx.services.DummyService2;
import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StuffFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StuffFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StuffFragment extends Fragment {
    private static final String TAG = StuffFragment.class.getSimpleName();
    private Button mButtonStuff, mButtonStuff2;
    private EditText mEditTextStuff, mEditTextStuff2;
    private TextView mTextViewStuff;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public StuffFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StuffFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StuffFragment newInstance(String param1, String param2) {
        StuffFragment fragment = new StuffFragment();
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
        return inflater.inflate(R.layout.fragment_stuff, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");
        
        mButtonStuff = (Button) view.findViewById(R.id.mButtonStuff);
        mEditTextStuff = (EditText) view.findViewById(R.id.mEditTextStuff);
        mTextViewStuff = (TextView) view.findViewById(R.id.mTextViewStuff);

        mEditTextStuff2 = (EditText) view.findViewById(R.id.mEditTextStuff2);
        mButtonStuff2 = (Button) view.findViewById(R.id.mButtonStuff);

        mButtonStuff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mEditTextStuff.getText().toString();

                Observable<String>observableMEditTextStuff = Observable.from(new String[]{input})
                        .map(new Func1<String, String>() {
                            @Override
                            public String call(String s) {
                                s+=" in the call";
                                Log.d(TAG, s);
                                return s;
                            }
                        });

                Observer<String> observerMEditTextStuff = new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError::" + e.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.d(TAG, "onNext::" + s);
                        mTextViewStuff.setText(s);
                    }
                };

                observableMEditTextStuff.subscribe(observerMEditTextStuff);
            }
        });

        //Button 2
//        mButtonStuff2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Observable<Service>observableService = Observable.from(DummyService2)
//                        .map(new Func1<Service, Service>() {
//                            @Override
//                            public Service call(Service service) {
//                                return null;
//                            }
//                        });
//
//
//                Intent intent = new Intent(getContext(), DummyService2.class);
//                intent.putExtra("key", "initValue");
//                getActivity().startService(intent);
//            }
//        });




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
