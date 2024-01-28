package com.mmajewski.experimental.fields.jsonserializer.jsonwriter;

import com.mmajewski.experimental.fields.jsonserializer.data.Address;
import com.mmajewski.experimental.fields.jsonserializer.data.Company;
import com.mmajewski.experimental.fields.jsonserializer.data.Person;

class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Address companyAddress = new Address("CompanyStreet", (short) 20);
        Company company = new Company("Nice Job!", companyAddress);

        Address personAddress = new Address("Lovely home st", (short) 30);
        Person person = new Person("John", true, 29, 100.546f, personAddress, company);

        System.out.println(JsonWriter.objectToJson(person,0));
    }
}
