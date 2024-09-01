package com.example.rest_service.userprofile;

import java.util.HashMap;
import java.util.Map;

public class UserProfile {

    private String firstName;
    private String lastName;
    private int age;
    private Map<String, Object> additionalFields;

    public UserProfile(String firstName, String lastName, int age) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        this.additionalFields = new HashMap<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        this.age = age;
    }

    public Map<String, Object> getAdditionalFields() {
        return additionalFields;
    }

    public void setAdditionalFields(Map<String, Object> additionalFields) {
        if (additionalFields == null) {
            throw new IllegalArgumentException("Additional fields cannot be null");
        }
        this.additionalFields = additionalFields;
    }

    // Method to add a user-defined field with validation
    public void addField(String key, Object value) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Field key cannot be null or empty");
        }
        additionalFields.put(key, value);
    }

    // Method to get a user-defined field with key validation
    public Object getField(String key) {
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalArgumentException("Field key cannot be null or empty");
        }
        return additionalFields.get(key);
    }

    // Override toString method for better readability
    @Override
    public String toString() {
        return "UserProfile{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", additionalFields=" + additionalFields +
                '}';
    }
}
