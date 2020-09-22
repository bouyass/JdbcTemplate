package com.example.demo.model;

public class Contact {
    private String contact_name;
    private String email;
    private String address;
    private String telephone;

    public Contact(String name, String email, String address, String phone) {
        this.contact_name = name;
        this.email = email;
        this.address = address;
        this.telephone = phone;
    }

    public Contact() {
        super();
    }

    public String getName() {
        return contact_name;
    }

    public void setName(String name) {
        this.contact_name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return telephone;
    }

    public void setPhone(String phone) {
        this.telephone = phone;
    }

    public String toString() {
        return String.format("[%s - %s - %s - %s]", contact_name, email, address, telephone);
    }
}