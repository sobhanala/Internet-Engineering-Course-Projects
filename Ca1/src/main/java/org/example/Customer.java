package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


//TODO ADD the Validation

@Getter
@Setter
@ToString
public class Customer {

    private String Ssn;
    private  String PhoneNumber;
    private  String  Name;

    private  int Age;

    public Customer(String ssn, String phoneNumber , String name, int age){
        this.Age=age;
        this.PhoneNumber=phoneNumber;
        this.Ssn=ssn;
        this.Name=name;

    }
}
