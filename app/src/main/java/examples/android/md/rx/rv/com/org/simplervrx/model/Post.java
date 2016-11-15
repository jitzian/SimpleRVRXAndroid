
package examples.android.md.rx.rv.com.org.simplervrx.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("title_plain")
    @Expose
    private String titlePlain;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("excerpt")
    @Expose
    private String excerpt;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("modified")
    @Expose
    private String modified;
    @SerializedName("categories")
    @Expose
    private List<Category_> categories = new ArrayList<Category_>();
    @SerializedName("tags")
    @Expose
    private List<Tag> tags = new ArrayList<Tag>();
    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("comments")
    @Expose
    private List<Object> comments = new ArrayList<Object>();
    @SerializedName("attachments")
    @Expose
    private List<Attachment> attachments = new ArrayList<Attachment>();
    @SerializedName("comment_count")
    @Expose
    private int commentCount;
    @SerializedName("comment_status")
    @Expose
    private String commentStatus;
    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("custom_fields")
    @Expose
    private CustomFields customFields;
    @SerializedName("thumbnail_size")
    @Expose
    private String thumbnailSize;
    @SerializedName("thumbnail_images")
    @Expose
    private ThumbnailImages thumbnailImages;

    /**
     * 
     * @return
     *     The id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The slug
     */
    public String getSlug() {
        return slug;
    }

    /**
     * 
     * @param slug
     *     The slug
     */
    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

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
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The titlePlain
     */
    public String getTitlePlain() {
        return titlePlain;
    }

    /**
     * 
     * @param titlePlain
     *     The title_plain
     */
    public void setTitlePlain(String titlePlain) {
        this.titlePlain = titlePlain;
    }

    /**
     * 
     * @return
     *     The content
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     * @return
     *     The excerpt
     */
    public String getExcerpt() {
        return excerpt;
    }

    /**
     * 
     * @param excerpt
     *     The excerpt
     */
    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    /**
     * 
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param date
     *     The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * 
     * @return
     *     The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     * 
     * @param modified
     *     The modified
     */
    public void setModified(String modified) {
        this.modified = modified;
    }

    /**
     * 
     * @return
     *     The categories
     */
    public List<Category_> getCategories() {
        return categories;
    }

    /**
     * 
     * @param categories
     *     The categories
     */
    public void setCategories(List<Category_> categories) {
        this.categories = categories;
    }

    /**
     * 
     * @return
     *     The tags
     */
    public List<Tag> getTags() {
        return tags;
    }

    /**
     * 
     * @param tags
     *     The tags
     */
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    /**
     * 
     * @return
     *     The author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * 
     * @param author
     *     The author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * 
     * @return
     *     The comments
     */
    public List<Object> getComments() {
        return comments;
    }

    /**
     * 
     * @param comments
     *     The comments
     */
    public void setComments(List<Object> comments) {
        this.comments = comments;
    }

    /**
     * 
     * @return
     *     The attachments
     */
    public List<Attachment> getAttachments() {
        return attachments;
    }

    /**
     * 
     * @param attachments
     *     The attachments
     */
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    /**
     * 
     * @return
     *     The commentCount
     */
    public int getCommentCount() {
        return commentCount;
    }

    /**
     * 
     * @param commentCount
     *     The comment_count
     */
    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 
     * @return
     *     The commentStatus
     */
    public String getCommentStatus() {
        return commentStatus;
    }

    /**
     * 
     * @param commentStatus
     *     The comment_status
     */
    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

    /**
     * 
     * @return
     *     The thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 
     * @param thumbnail
     *     The thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 
     * @return
     *     The customFields
     */
    public CustomFields getCustomFields() {
        return customFields;
    }

    /**
     * 
     * @param customFields
     *     The custom_fields
     */
    public void setCustomFields(CustomFields customFields) {
        this.customFields = customFields;
    }

    /**
     * 
     * @return
     *     The thumbnailSize
     */
    public String getThumbnailSize() {
        return thumbnailSize;
    }

    /**
     * 
     * @param thumbnailSize
     *     The thumbnail_size
     */
    public void setThumbnailSize(String thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
    }

    /**
     * 
     * @return
     *     The thumbnailImages
     */
    public ThumbnailImages getThumbnailImages() {
        return thumbnailImages;
    }

    /**
     * 
     * @param thumbnailImages
     *     The thumbnail_images
     */
    public void setThumbnailImages(ThumbnailImages thumbnailImages) {
        this.thumbnailImages = thumbnailImages;
    }

}
