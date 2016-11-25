
package examples.android.md.rx.rv.com.org.simplervrx.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpotifyResult {

    @SerializedName("artists")
    @Expose
    private Artists artists;

    /**
     * 
     * @return
     *     The artists
     */
    public Artists getArtists() {
        return artists;
    }

    /**
     * 
     * @param artists
     *     The artists
     */
    public void setArtists(Artists artists) {
        this.artists = artists;
    }

}
