package com.aabramov.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Andrii Abramov on 12/15/15.
 */
@Entity
@Table(name = "feedback")
public class Feedback implements Serializable {
    
    private int id;
    private String title;
    private String body;
    private LocalDate date;
    private String author;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }
    
    
    public void setId(int id) {
        this.id = id;
    }
    
    
    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                ", author='" + author + '\'' +
                '}';
    }
    
    
    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }
    
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    
    @Basic
    @Column(name = "body")
    public String getBody() {
        return body;
    }
    
    
    public void setBody(String body) {
        this.body = body;
    }
    
    
    @Column(name = "date")
    public LocalDate getDate() {
        return date;
    }
    
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    
    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }
    
    
    public void setAuthor(String author) {
        this.author = author;
    }
}
