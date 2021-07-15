package com.example.model;

import com.example.rabbitmqsender.Address;

import java.time.LocalDate;
import java.util.Objects;

public class Client {
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private Address address;

    public Client() {
    }

    public Client(String name, String lastName, LocalDate birthDate, Address address) {
        this.name = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) && Objects.equals(lastName, client.lastName) && Objects.equals(birthDate, client.birthDate) && Objects.equals(address, client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, birthDate, address);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", address=" + address +
                '}';
    }
}
