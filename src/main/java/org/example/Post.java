package org.example;

public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;

    public Post(int userId, int id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }
}
