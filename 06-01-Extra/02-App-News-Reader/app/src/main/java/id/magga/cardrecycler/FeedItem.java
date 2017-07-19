package id.magga.cardrecycler;

/**
 * Created by magga on 7/19/2017.
 */

public class FeedItem {
    private String title;
    private String thumbnail;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {

        return content;
    }

    private String content;

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
