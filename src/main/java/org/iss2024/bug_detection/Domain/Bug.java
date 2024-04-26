package org.iss2024.bug_detection.Domain;

public class Bug extends Entity<Long> {
    String name;
    String description;
    String status;
    Validator validator;
    Programmer programmer;

    public Bug(String name, String description, String status, Validator validator) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.validator = validator;
    }

    public Bug(String name, String description, String status, Programmer programmer) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.programmer = programmer;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
