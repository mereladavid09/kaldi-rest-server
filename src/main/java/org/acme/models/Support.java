package org.acme.models;
public class Support {
    private int id;
    private String email;
    private String username;

    private String role;

    public Support() {
    }

    public Support(int id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public Support(String email, String username, String role) {
        this.email = email;
        this.username = username;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
