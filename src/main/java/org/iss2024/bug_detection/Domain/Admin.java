package org.iss2024.bug_detection.Domain;

public class Admin extends Employee{

    public Admin(String username, String password) {
        super(username, password);
    }
    @Override
    public String toString() {
        return "Admin{" +
                "username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}
