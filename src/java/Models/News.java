package Models;

import java.util.Date;
import java.util.List;

public class News {
    private int newId;
    private Topic topic;
    private String title;
    private String content;
    private String imgNews;
    private Date dateUpload;
    private String source;
    private List<String> tags;
    private boolean status;

    // Getters and setters for all fields
    public int getNewId() {
        return newId;
    }

    public void setNewId(int newId) {
        this.newId = newId;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgNews() {
        return imgNews;
    }

    public void setImgNews(String imgNews) {
        this.imgNews = imgNews;
    }

    public Date getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(Date dateUpload) {
        this.dateUpload = dateUpload;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
