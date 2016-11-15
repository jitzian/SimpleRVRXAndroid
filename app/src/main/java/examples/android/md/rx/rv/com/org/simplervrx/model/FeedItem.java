package examples.android.md.rx.rv.com.org.simplervrx.model;

/**
 * Created by User on 11/4/2016.
 */

public class FeedItem {

    private String title;
    private String thumbnail;

    public FeedItem(){}

    public FeedItem(String title, String thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
