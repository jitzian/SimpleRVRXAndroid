
package examples.android.md.rx.rv.com.org.simplervrx.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Images {

    @SerializedName("full")
    @Expose
    private Full full;
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;
    @SerializedName("medium")
    @Expose
    private Medium medium;
    @SerializedName("medium_large")
    @Expose
    private MediumLarge mediumLarge;
    @SerializedName("large")
    @Expose
    private Large large;
    @SerializedName("xlarge")
    @Expose
    private Xlarge xlarge;
    @SerializedName("featured")
    @Expose
    private Featured featured;
    @SerializedName("feed")
    @Expose
    private Feed feed;
    @SerializedName("lay-a")
    @Expose
    private LayA layA;
    @SerializedName("small")
    @Expose
    private Small small;

    /**
     * 
     * @return
     *     The full
     */
    public Full getFull() {
        return full;
    }

    /**
     * 
     * @param full
     *     The full
     */
    public void setFull(Full full) {
        this.full = full;
    }

    /**
     * 
     * @return
     *     The thumbnail
     */
    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    /**
     * 
     * @param thumbnail
     *     The thumbnail
     */
    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 
     * @return
     *     The medium
     */
    public Medium getMedium() {
        return medium;
    }

    /**
     * 
     * @param medium
     *     The medium
     */
    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    /**
     * 
     * @return
     *     The mediumLarge
     */
    public MediumLarge getMediumLarge() {
        return mediumLarge;
    }

    /**
     * 
     * @param mediumLarge
     *     The medium_large
     */
    public void setMediumLarge(MediumLarge mediumLarge) {
        this.mediumLarge = mediumLarge;
    }

    /**
     * 
     * @return
     *     The large
     */
    public Large getLarge() {
        return large;
    }

    /**
     * 
     * @param large
     *     The large
     */
    public void setLarge(Large large) {
        this.large = large;
    }

    /**
     * 
     * @return
     *     The xlarge
     */
    public Xlarge getXlarge() {
        return xlarge;
    }

    /**
     * 
     * @param xlarge
     *     The xlarge
     */
    public void setXlarge(Xlarge xlarge) {
        this.xlarge = xlarge;
    }

    /**
     * 
     * @return
     *     The featured
     */
    public Featured getFeatured() {
        return featured;
    }

    /**
     * 
     * @param featured
     *     The featured
     */
    public void setFeatured(Featured featured) {
        this.featured = featured;
    }

    /**
     * 
     * @return
     *     The feed
     */
    public Feed getFeed() {
        return feed;
    }

    /**
     * 
     * @param feed
     *     The feed
     */
    public void setFeed(Feed feed) {
        this.feed = feed;
    }

    /**
     * 
     * @return
     *     The layA
     */
    public LayA getLayA() {
        return layA;
    }

    /**
     * 
     * @param layA
     *     The lay-a
     */
    public void setLayA(LayA layA) {
        this.layA = layA;
    }

    /**
     * 
     * @return
     *     The small
     */
    public Small getSmall() {
        return small;
    }

    /**
     * 
     * @param small
     *     The small
     */
    public void setSmall(Small small) {
        this.small = small;
    }

}
