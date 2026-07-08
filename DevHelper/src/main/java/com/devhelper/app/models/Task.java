package com.devhelper.app.models;


public class Task {
    private int id;
    private String name;
    private boolean done;

    public Task(int id, String name, boolean done) {
        this.id = id;
        this.name = name;
        this.done = done;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public boolean isDone() { return done; }
}