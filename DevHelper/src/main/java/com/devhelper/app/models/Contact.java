package com.devhelper.app.models;

public class Contact {
    private String name;
    private String role;
    private String email;

    public Contact(String name, String role, String email) {
        this.name = name;
        this.role = role;
        this.email = email;
    }

    public String getName() { return name; }
    public String getRole() { return role; }
    public String getEmail() { return email; }
}