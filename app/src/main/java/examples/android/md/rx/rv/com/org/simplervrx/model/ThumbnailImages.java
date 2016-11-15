
package examples.android.md.rx.rv.com.org.simplervrx.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThumbnailImages {

    @SerializedName("full")
    @Expose
    private Full_ full;
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail_ thumbnail;
    @SerializedName("medium")
    @Expose
    private Medium_ medium;
    @SerializedName("medium_large")
    @Expose
    private MediumLarge_ mediumLarge;
    @SerializedName("large")
    @Expose
    private Large_ large;
    @SerializedName("xlarge")
    @Expose
    private Xlarge_ xlarge;
    @SerializedName("featured")
    @Expose
    private Featured_ featured;
    @SerializedName("feed")
    @Expose
    private Feed_ feed;
    @SerializedName("lay-a")
    @Expose
    private LayA_ layA;
    @SerializedName("small")
    @Expose
    private Small_ small;

    /**
     * 
     * @return
     *     The full
     */
    public Full_ getFull() {
        return full;
    }

    /**
     * 
     * @param full
     *     The full
     */
    public void setFull(Full_ full) {
        this.full = full;
    }

    /**
     * 
     * @return
     *     The thumbnail
     */
    public Thumbnail_ getThumbnail() {
        return thumbnail;
    }

    /**
     * 
     * @param thumbnail
     *     The thumbnail
     */
    public void setThumbnail(Thumbnail_ thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 
     * @return
     *     The medium
     */
    public Medium_ getMedium() {
        return medium;
    }

    /**
     * 
     * @param medium
     *     The medium
     */
    public void setMedium(Medium_ medium) {
        this.medium = medium;
    }

    /**
     * 
     * @return
     *     The mediumLarge
     */
    public MediumLarge_ getMediumLarge() {
        return mediumLarge;
    }

    /**
     * 
     * @param mediumLarge
     *     The medium_large
     */
    public void setMediumLarge(MediumLarge_ mediumLarge) {
        this.mediumLarge = mediumLarge;
    }

    /**
     * 
     * @return
     *     The large
     */
    public Large_ getLarge() {
        return large;
    }

    /**
     * 
     * @param large
     *     The large
     */
    public void setLarge(Large_ large) {
        this.large = large;
    }

    /**
     * 
     * @return
     *     The xlarge
     */
    public Xlarge_ getXlarge() {
        return xlarge;
    }

    /**
     * 
     * @param xlarge
     *     The xlarge
     */
    public void setXlarge(Xlarge_ xlarge) {
        this.xlarge = xlarge;
    }

    /**
     * 
     * @return
     *     The featured
     */
    public Featured_ getFeatured() {
        return featured;
    }

    /**
     * 
     * @param featured
     *     The featured
     */
    public void setFeatured(Featured_ featured) {
        this.featured = featured;
    }

    /**
     * 
     * @return
     *     The feed
     */
    public Feed_ getFeed() {
        return feed;
    }

    /**
     * 
     * @param feed
     *     The feed
     */
    public void setFeed(Feed_ feed) {
        this.feed = feed;
    }

    /**
     * 
     * @return
     *     The layA
     */
    public LayA_ getLayA() {
        return layA;
    }

    /**
     * 
     * @param layA
     *     The lay-a
     */
    public void setLayA(LayA_ layA) {
        this.layA = layA;
    }

    /**
     * 
     * @return
     *     The small
     */
    public Small_ getSmall() {
        return small;
    }

    /**
     * 
     * @param small
     *     The small
     */
    public void setSmall(Small_ small) {
        this.small = small;
    }

}
