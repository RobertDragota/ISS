package org.mpp2024.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "admins")
public class Admin extends Employee{

    public Admin() {
    }
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
