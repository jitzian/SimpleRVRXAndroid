package examples.android.md.rx.rv.com.org.simplervrx.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("pages")
    @Expose
    private int pages;
    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("posts")
    @Expose
    private List<Post> posts = new ArrayList<Post>();

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The count
     */
    public int getCount() {
        return count;
    }

    /**
     * 
     * @param count
     *     The count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 
     * @return
     *     The pages
     */
    public int getPages() {
        return pages;
    }

    /**
     * 
     * @param pages
     *     The pages
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * 
     * @return
     *     The category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * 
     * @return
     *     The posts
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     * 
     * @param posts
     *     The posts
     */
    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

}
