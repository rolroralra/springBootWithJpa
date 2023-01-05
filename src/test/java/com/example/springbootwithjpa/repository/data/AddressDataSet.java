package com.example.springbootwithjpa.repository.data;

import com.example.springbootwithjpa.domain.Address;

public class AddressDataSet {
    public static Address testData() {
        return testData("Seoul", "Songpa", "10011");
    }
    public static Address testData(String city, String street, String zipcode) {
        return new Address(city, street, zipcode);
    }
}
