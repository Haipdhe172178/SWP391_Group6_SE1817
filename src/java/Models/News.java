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
    private int topicId;
    private String title;
    private String desctiption;
    private String imgNews1;
    private String imgNews2;
    private String imgNews3;
    private Date dateUpload;

    public News() {
    }

    public News(int newId, int topicId, String title, String desctiption, String imgNews1, String imgNews2, String imgNews3, Date dateUpload) {
        this.newId = newId;
        this.topicId = topicId;
        this.title = title;
        this.desctiption = desctiption;
        this.imgNews1 = imgNews1;
        this.imgNews2 = imgNews2;
        this.imgNews3 = imgNews3;
        this.dateUpload = dateUpload;
    }

    public int getNewId() {
        return newId;
    }

    public void setNewId(int newId) {
        this.newId = newId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesctiption() {
        return desctiption;
    }

    public void setDesctiption(String desctiption) {
        this.desctiption = desctiption;
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

    public String getImgNews3() {
        return imgNews3;
    }

    public void setImgNews3(String imgNews3) {
        this.imgNews3 = imgNews3;
    }

    public Date getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(Date dateUpload) {
        this.dateUpload = dateUpload;
    }
    
}
