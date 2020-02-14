package ca.bcit.kim.wilson.comp_3717_android_assignment1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article {
    @SerializedName("author")
    @Expose
    private String author;
    public String getAuthor() {
        return author;
    }

    @SerializedName("title")
    @Expose
    private String title;
    public String getTitle() {
        if (this.title == null) {
            return "N/A";
        }
        return this.title;
    }

    @SerializedName("description")
    @Expose
    private String description;
    public String getDescription() {
        if (this.description == null) {
            return "N/A";
        }
        return description;
    }

    @SerializedName("url")
    @Expose
    private String url;
    public String getUrl() {
        return this.url;
    }

    @SerializedName("urlToImage")
    @Expose
    private String urlToImage;
    public String getUrlToImage() {
        return urlToImage;
    }

    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;
    public String getPublishedAt() {
        return publishedAt;
    }

    @SerializedName("content")
    @Expose
    private String content;
    public String getContent() {
        return content;
    }
}
