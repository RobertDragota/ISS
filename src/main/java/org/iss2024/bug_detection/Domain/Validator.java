package org.iss2024.bug_detection.Domain;

public class Validator extends Employee {
    public Validator(String username, String password) {
        super(username, password);
    }

    @Override
    public String toString() {
        return "Validator{" +
                "username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}
