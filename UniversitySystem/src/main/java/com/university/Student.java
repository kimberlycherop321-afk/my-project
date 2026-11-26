package com.university;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private final StringProperty firstName = new SimpleStringProperty(this, "firstName", "");
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName", "");
    private final StringProperty email = new SimpleStringProperty(this, "email", "");
    private final IntegerProperty programId = new SimpleIntegerProperty(this, "programId");
    private final StringProperty programName = new SimpleStringProperty(this, "programName", "");

    public Student(int id, String firstName, String lastName, String email,
                   int programId, String programName) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setProgramId(programId);
        setProgramName(programName);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public int getProgramId() {
        return programId.get();
    }

    public IntegerProperty programIdProperty() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId.set(programId);
    }

    public String getProgramName() {
        return programName.get();
    }

    public StringProperty programNameProperty() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName.set(programName);
    }
}
