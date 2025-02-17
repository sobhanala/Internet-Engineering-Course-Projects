package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


//TODO ADD the Validation

@Getter
@Setter
@ToString
public class Customer {

    @JsonProperty("ssn")
    private String Ssn;
    @JsonProperty("phone")

    private  String PhoneNumber;
    @JsonProperty("name")
    private  String  Name;
    @JsonProperty("age")
    private  int Age;




    public Customer(String ssn, String phoneNumber , String name, int age){
        this.Age=age;
        this.PhoneNumber=phoneNumber;
        this.Ssn=ssn;
        this.Name=name;

    }

    public Customer(){

    }
}
