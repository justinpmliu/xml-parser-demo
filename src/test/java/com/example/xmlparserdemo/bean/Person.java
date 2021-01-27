package com.example.xmlparserdemo.bean;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Person {
    private String firstName;
    private String lastName;
    @JacksonXmlElementWrapper(localName = "phoneNumbers")
    private List<String> phoneNumber = new ArrayList<>();
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Address> address = new ArrayList<>();

}
