package com.samuelvialle.tocourssimplecrud;

public class TodoModel {

    String id, title, content;
    int priority;

    public TodoModel() {
    }

    public TodoModel(String id, String title, String content, int priority) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
