package com.example.xmlparserdemo;

import com.example.xmlparserdemo.bean.Person;
import com.example.xmlparserdemo.bean.SimpleBean;
import com.example.xmlparserdemo.bean.Address;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class XmlMapperTest {

    private XmlMapper xmlMapper;

    @BeforeEach
    void setUp() {
        xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Test
    void simpleBeanToXml() throws JsonProcessingException {
        SimpleBean simpleBean = new SimpleBean();
        simpleBean.setX(1);
        simpleBean.setY(2);
        String xml = xmlMapper.writeValueAsString(simpleBean);
        System.out.println(xml);
    }

    @Test
    void xmlToSimpleBean() throws IOException {
        File file = new File("src/test/resources/simple_bean.xml");
        String xml = inputStreamToString(new FileInputStream(file));
        SimpleBean simpleBean = xmlMapper.readValue(xml, SimpleBean.class);
        System.out.println(simpleBean);
    }

    @Test
    void personToXml() throws JsonProcessingException {
        Person person = this.getTestPerson();
        String xml = xmlMapper.writeValueAsString(person);
        System.out.println(xml);
    }

    @Test
    void xmlToPerson() throws IOException {
        File file = new File("src/test/resources/person.xml");
        String xml = inputStreamToString(new FileInputStream(file));
        Person person = xmlMapper.readValue(xml, Person.class);
        System.out.println(person);

    }

    Person getTestPerson() {
        Person person = new Person();
        person.setFirstName("Rohan");
        person.setLastName("Daye");
        person.setPhoneNumber(Arrays.asList("9911034731", "9911033478"));
        List<Address> address = new ArrayList<>();
        address.add(new Address("Name1", "City1"));
        address.add(new Address("Name2", "City2"));
        person.setAddress(address);
        return person;
    }

    String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
