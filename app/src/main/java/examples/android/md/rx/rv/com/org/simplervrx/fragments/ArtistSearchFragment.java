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
import android.widget.EditText;

import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import examples.android.md.rx.rv.com.org.simplervrx.R;
import examples.android.md.rx.rv.com.org.simplervrx.adapter.RVArtistSearchAdapter;
import examples.android.md.rx.rv.com.org.simplervrx.daggerComponents.DaggerRetrofitComponent;
import examples.android.md.rx.rv.com.org.simplervrx.daggerModules.RetrofitModule;
import examples.android.md.rx.rv.com.org.simplervrx.model.ApiConstants;
import examples.android.md.rx.rv.com.org.simplervrx.model.Item;
import examples.android.md.rx.rv.com.org.simplervrx.model.SpotifyResult;
import examples.android.md.rx.rv.com.org.simplervrx.rest.SpotifyApiService;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ArtistSearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ArtistSearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArtistSearchFragment extends Fragment {
    private static final String TAG = ArtistSearchFragment.class.getSimpleName();
    private RVArtistSearchAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @BindView(R.id.mRecyclerViewArtist)
    RecyclerView mRecyclerViewArtist;

    @BindView(R.id.mEditTextSearch)
    EditText mEditTextSearch;

    @Inject
    Retrofit retrofit;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ArtistSearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArtistSearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArtistSearchFragment newInstance(String param1, String param2) {
        ArtistSearchFragment fragment = new ArtistSearchFragment();
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
        return inflater.inflate(R.layout.fragment_artist_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated");

        ButterKnife.bind(this, view);

        //Dagger Initialization
        DaggerRetrofitComponent.builder()
                .retrofitModule(new RetrofitModule(ApiConstants.BASE_URL))
                .build()
                .inject(this);

        SpotifyApiService spotifyApiService = retrofit.create(SpotifyApiService.class);

        //RecyclerView Init
        layoutManager = new LinearLayoutManager(getContext());
        mRecyclerViewArtist.setLayoutManager(layoutManager);


        RxTextView.textChanges(mEditTextSearch)
                .subscribe(new Action1<CharSequence>() {
                    @Override
                    public void call(CharSequence charSequence) {
                        Log.d(TAG, " - " + charSequence);
                        Observable<SpotifyResult> spotifyResultObservable =  spotifyApiService
                                .searchArtist(charSequence.toString());

                        spotifyResultObservable
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<SpotifyResult>() {
                                    @Override
                                    public void onCompleted() {
                                        Log.d(TAG, "onCompleted");
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Log.d(TAG, "onError::" + e.getMessage());
                                    }

                                    @Override
                                    public void onNext(SpotifyResult spotifyResult) {
                                        Log.d(TAG, "onNext::" + spotifyResult.getArtists().getItems().size());
                                        adapter = new RVArtistSearchAdapter((ArrayList<Item>) spotifyResult.getArtists().getItems(), getContext());
                                        mRecyclerViewArtist.setAdapter(adapter);

                                    }
                                });


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
