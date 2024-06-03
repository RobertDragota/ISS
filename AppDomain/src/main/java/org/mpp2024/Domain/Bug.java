package org.mpp2024.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@jakarta.persistence.Entity
@Table(name = "bugs")
public class Bug extends Entity<Long> {
    @Column(name = "title", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;



    @ManyToOne
    @JoinColumn(name = "validator")
    private Validator validator;

    @ManyToOne
    @JoinColumn(name = "programmer")
    private Programmer programmer;

    public Bug() {
    }
    public Bug(String name, String description, Validator validator) {
        this.name = name;
        this.description = description;

        this.validator = validator;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public Programmer getProgrammer() {
        return programmer;
    }

    public void setProgrammer(Programmer programmer) {
        this.programmer = programmer;
    }

}
