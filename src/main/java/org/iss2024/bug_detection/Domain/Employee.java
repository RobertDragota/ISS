package org.iss2024.bug_detection.Domain;

public class Employee extends Entity<String>{
    private String username;
    private String password;

    public Employee(String username, String password) {
        this.username = username;
        this.password = password;
        setId(username);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
