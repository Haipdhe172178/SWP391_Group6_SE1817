/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author huyca
 */
public class Author {
    private int authorID;
    private String authorName;
    private String description;
    private int status;

    public Author() {
    }

    public Author(int authorID, String authorName, String description) {
        this.authorID = authorID;
        this.authorName = authorName;
        this.description = description;
    }

    public Author(int authorID, String authorName, String description, int status) {
        this.authorID = authorID;
        this.authorName = authorName;
        this.description = description;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Author{" + "authorID=" + authorID + ", authorName=" + authorName + ", description=" + description + ", status=" + status + '}';
    }
    
}
