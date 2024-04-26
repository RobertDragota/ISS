package org.iss2024.bug_detection.Domain;

public class Programmer extends Employee{

    public Programmer(String username, String password) {
        super(username, password);
    }
    @Override
    public String toString() {
        return "Programmer{" +
                "username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}
