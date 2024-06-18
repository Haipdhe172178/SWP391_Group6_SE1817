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
public class Feedback {

    private int feedbackId;
    private Account account;
    private Product product;
    private int rating;
    private String comments;
    private Date feedbackDate;
    private int status;

    public Feedback() {
    }

    public Feedback(int feedbackId, Account account, Product product, int rating, String comments, Date feedbackDate, int status) {
        this.feedbackId = feedbackId;
        this.account = account;
        this.product = product;
        this.rating = rating;
        this.comments = comments;
        this.feedbackDate = feedbackDate;
        this.status = status;
    }
    
    public Feedback(Account account, Product product, int rating, String comments) {
        this.account  = account;
        this.product = product;
        this.rating = rating;
        this.comments = comments;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Date getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Date feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
