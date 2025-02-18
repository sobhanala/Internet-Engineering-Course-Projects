package org.example;

import Exeptions.MissingRequiredFieldException;
import Exeptions.ValidationException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Customer {

    @JsonProperty("ssn")
    private String Ssn;

    @JsonProperty("phone")
    private String PhoneNumber;

    @JsonProperty("name")
    private String Name;

    @JsonProperty("age")
    private int Age;

    @JsonCreator
    public Customer(
            @JsonProperty("ssn") String ssn,
            @JsonProperty("phone") String phoneNumber,
            @JsonProperty("name") String name,
            @JsonProperty("age") int age) {
        setSsn(ssn);
        setPhoneNumber(phoneNumber);
        setName(name);
        setAge(age);}


    public Customer() {
    }

    public void setSsn(String ssn) {
        if (ssn == null || ssn.isBlank()) {
            throw new MissingRequiredFieldException("cannot be blank.",ssn);
        }
        if (!ssn.matches("^\\d{10}$")) {
            throw new ValidationException("Persian SSN must be exactly 10 digits.");
        }

        this.Ssn = ssn;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            throw new MissingRequiredFieldException("cannot be blank.",phoneNumber);
        }
        if (!phoneNumber.matches("^\\+?[0-9]{10,15}$")) {
            throw new ValidationException("Phone number must be between 10 and 15 digits.");
        }
        this.PhoneNumber = phoneNumber;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new MissingRequiredFieldException(" cannot be blank.",name);
        }
        if (name.length() < 2 || name.length() > 50) {
            throw new ValidationException("Name must be between 2 and 50 characters.");
        }
        this.Name = name;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new ValidationException("Age must be a positive number.");
        }
        if (age > 120) {
            throw new ValidationException("Age must be less than or equal to 120.");
        }
        this.Age = age;
    }
}