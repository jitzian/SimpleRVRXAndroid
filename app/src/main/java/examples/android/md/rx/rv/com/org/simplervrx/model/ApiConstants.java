package examples.android.md.rx.rv.com.org.simplervrx.model;


public class ApiConstants {

    public static final String BASE_URL = "https://api.spotify.com";

    public static final String VERSION_PATH = "/v1";
    public static final String SEARCH_PATH = "/search";

    public static final String TYPE_QUERY = "type";
    public static final String QUERY_TO_SEARCH = "q";

    public static final String ARTIST = "artist";

    public static final String ARTIST_SEARCH_URL = VERSION_PATH + SEARCH_PATH + "?"+ TYPE_QUERY + "=" + ARTIST;

    //https://api.spotify.com/v1/search?q=linkin%20park&type=artist
}
