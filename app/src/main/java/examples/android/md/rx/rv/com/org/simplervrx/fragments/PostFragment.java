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

import examples.android.md.rx.rv.com.org.simplervrx.MainActivity;
import examples.android.md.rx.rv.com.org.simplervrx.R;
import examples.android.md.rx.rv.com.org.simplervrx.adapter.RVAdapter;
import examples.android.md.rx.rv.com.org.simplervrx.listeners.ClickListener;
import examples.android.md.rx.rv.com.org.simplervrx.listeners.RecyclerTouchListener;
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
 * {@link PostFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends Fragment {
    private static final String TAG = PostFragment.class.getSimpleName();
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
    private RecyclerView recyclerViewFeeds;
    private RecyclerView.LayoutManager layoutManager;
    private RVAdapter RVAdapter;
    private final String BASE_URL_STACKTIPS = "http://stacktips.com/?json=get_category_posts&slug=news&count=1";
    private final String END_POINT = "?json=get_category_posts&slug=news&count=1";

    private String BASE_URL_GITHUB = "https://api.github.com/";
    private String GITHUB_USER = "jitzian";

    //https://gist.githubusercontent.com/libinbensin/21efcf3e57cbda43c0df075aca1923db/raw/3e4adb89c32a8ea77210785595fdffa3a626ab54/cheese_list.json
    private String BASE_URL_CHEESE = "https://gist.githubusercontent.com/";
    private String USER_CHEESE = "libinbensin";


    public PostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PostFragment newInstance(String param1, String param2) {
        PostFragment fragment = new PostFragment();
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

        rootView = inflater.inflate(R.layout.fragment_post, container, false);
        initRecyclerView();
        return rootView;
    }

    public void initRecyclerView() {
        Log.d(TAG, "initRecyclerView");
        recyclerViewFeeds = (RecyclerView) rootView.findViewById(R.id.recyclerViewFeeds);
        layoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerViewFeeds.setLayoutManager(layoutManager);

        recyclerViewFeeds.addOnItemTouchListener(new RecyclerTouchListener(rootView.getContext(),
                recyclerViewFeeds, new ClickListener() {
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
                .baseUrl(BASE_URL_CHEESE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitRest.RetrofitService retrofitService = retrofit.create(RetrofitRest.RetrofitService.class);
        Call<ArrayList<String>> call = retrofitService.getCheese(USER_CHEESE);

        if (call != null) {
            Log.d(TAG, "CALL NOT NULL");
            call.enqueue(new Callback<ArrayList<String>>() {
                @Override
                public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                    Log.d(TAG, "onResponse");
                    ArrayList<String> lstRes = response.body();
                    Log.d(TAG, "::lstRes::" + lstRes.size());
                    RVAdapter = new RVAdapter(rootView.getContext(), lstRes);
                    recyclerViewFeeds.setAdapter(RVAdapter);
                }

                @Override
                public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                    Log.d(TAG, "onFailure");
                }
            });

        } else {
            Log.d(TAG, "CALL IS NULL");
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


}
