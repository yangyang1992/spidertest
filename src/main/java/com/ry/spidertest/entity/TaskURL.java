package com.ry.spidertest.entity;

/**
 * Created by yangyang on 2016/12/25.
 */
public class TaskURL {
    private int id;
    private String url;
    private int bookId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
