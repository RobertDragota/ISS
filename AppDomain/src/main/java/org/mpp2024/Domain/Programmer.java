package org.mpp2024.Domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "programmers")
public class Programmer extends Employee{

    public Programmer() {
    }
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
