package examples.android.md.rx.rv.com.org.simplervrx.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import examples.android.md.rx.rv.com.org.simplervrx.R;
import examples.android.md.rx.rv.com.org.simplervrx.adapter.RVAdapter;
import examples.android.md.rx.rv.com.org.simplervrx.adapter.RVMessageAdapter;
import examples.android.md.rx.rv.com.org.simplervrx.listeners.ClickListener;
import examples.android.md.rx.rv.com.org.simplervrx.model.Example;
import examples.android.md.rx.rv.com.org.simplervrx.model.Result;
import examples.android.md.rx.rv.com.org.simplervrx.rest.RetrofitRest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MessagesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MessagesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessagesFragment extends Fragment {
    private static final String TAG = MessagesFragment.class.getSimpleName();
    private View rootView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //Recyclerview
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RVMessageAdapter rvMessageAdapter;

    //Retrofit
    String BASE_URL_STACK_TIPS = "http://stacktips.com/";


    public MessagesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MessagesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessagesFragment newInstance(String param1, String param2) {
        MessagesFragment fragment = new MessagesFragment();
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

        //Not a best practice
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //-------------------

        rootView = inflater.inflate(R.layout.fragment_messages, container, false);
        initRecyclerView();
        return rootView;
    }

    public void initRecyclerView(){
        Log.d(TAG, "initRecyclerView");
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerViewMessages);
        layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnItemTouchListener(new MessagesFragment.RecyclerTouchListener(rootView.getContext(),
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                Log.d(TAG, "addOnItemTouchListener::onClick");
                Toast.makeText(rootView.getContext(), "Single Click on position: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Log.d(TAG, "addOnItemTouchListener::onLongClick");
                Toast.makeText(rootView.getContext(), "Long press on position :" + position, Toast.LENGTH_LONG).show();
            }
        }));

        //Retrofit
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL_STACK_TIPS)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitRest.RetrofitService retrofitService = retrofit.create(RetrofitRest.RetrofitService.class);
        Call <List<Example>>call = retrofitService.getStackTips();

        if(call != null){
            Log.d(TAG, "CALL NOT NULL");
            call.enqueue(new Callback<List<Example>>() {
                @Override
                public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                    Log.d(TAG, "onResponse::" + response.message());
                }

                @Override
                public void onFailure(Call<List<Example>> call, Throwable t) {
                    Log.d(TAG, "onFailure::" + t.getMessage());
                }
            });
        }else{
            Log.d(TAG, "CALL NULL");
        }


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

    //This class handles the click event on the RecyclerView
    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{
        private final String TAG = RecyclerTouchListener.class.getName();
        private ClickListener clickListener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener){
            Log.d(TAG, "RecyclerTouchListener::RecyclerTouchListener");
            this.clickListener = clickListener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clickListener != null){
                        clickListener.onLongClick(child, recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            Log.d(TAG, "onInterceptTouchEvent");
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clickListener != null && gestureDetector.onTouchEvent(e)){
                clickListener.onClick(child, rv.getChildAdapterPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            Log.d(TAG, "onTouchEvent");
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            Log.d(TAG, "onRequestDisallowInterceptTouchEvent");
        }
    }
}
