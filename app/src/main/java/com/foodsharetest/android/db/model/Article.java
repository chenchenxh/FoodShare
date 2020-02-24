package com.foodsharetest.android.db.model;

import android.util.Log;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

/**
 * 文章和作者是一对多的关系，但是LitePal中会有一个问题：Article中使用User的话，在User数据库table中没有任何一项有关于Article。
 * 所以解决办法：比如该项目User和Article，那么User定义一个Article List，Article定义一个User，如果想要从Article直接拿到user，那么可以多定义一个user_id
 * 其他：犯过一个错误，那就关联类中的初始化不能没有关联的类，也就是Article的构造函数必须要有User
* */
public class Article extends LitePalSupport {
    private long id;
    private String title;
    private String text;
    private int state;  //0代表未发布，1代表发布
    private User author;
    private long author_id;

    public Article(String title, String text, int state, User author, long author_id) {
        this.title = title;
        this.text = text;
        this.state = state;
        this.author = author;
        this.author_id = author_id;
    }

    public Article(String title, String text, int state, User author) {
        this.title = title;
        this.text = text;
        this.state = state;
        this.author = author;
        this.author_id = author.getId();
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", state=" + state +
                ", author_id=" + author_id +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(long author_id) {
        this.author_id = author_id;
    }
}
