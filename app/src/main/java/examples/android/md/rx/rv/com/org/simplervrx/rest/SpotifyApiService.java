package examples.android.md.rx.rv.com.org.simplervrx.rest;

import examples.android.md.rx.rv.com.org.simplervrx.constants.SpotifyApiConstants;
import examples.android.md.rx.rv.com.org.simplervrx.model.SpotifyResult;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by User on 11/22/2016.
 */

public interface SpotifyApiService {
    @GET(SpotifyApiConstants.ARTIST_SEARCH_URL)
    Observable<SpotifyResult>searchArtist(@Query(SpotifyApiConstants.QUERY_TO_SEARCH)String qry);
}
