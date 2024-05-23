/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.util.Date;

/**
 *
 * @author huyca
 */
public class News {
    private int newId;
    private Topic topic;
    private String title;
    private String content;
    private String imgNews1;
    private String imgNews2;
    private Date dateUpload;
    private String source;

    public News() {
    }

    public News(int newId, Topic topic, String title, String content, String imgNews1, String imgNews2, Date dateUpload, String source) {
        this.newId = newId;
        this.topic = topic;
        this.title = title;
        this.content = content;
        this.imgNews1 = imgNews1;
        this.imgNews2 = imgNews2;
        this.dateUpload = dateUpload;
        this.source = source;
    }


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

    public String getImgNews1() {
        return imgNews1;
    }

    public void setImgNews1(String imgNews1) {
        this.imgNews1 = imgNews1;
    }

    public String getImgNews2() {
        return imgNews2;
    }

    public void setImgNews2(String imgNews2) {
        this.imgNews2 = imgNews2;
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
    
}
