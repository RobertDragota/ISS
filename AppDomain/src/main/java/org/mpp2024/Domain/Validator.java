package org.mpp2024.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "validators")
public class Validator extends Employee {
    public Validator() {
    }

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
