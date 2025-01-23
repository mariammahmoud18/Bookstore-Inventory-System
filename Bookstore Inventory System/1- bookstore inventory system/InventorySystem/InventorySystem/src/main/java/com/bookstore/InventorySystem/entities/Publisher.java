package com.bookstore.InventorySystem.entities;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.regex.Pattern;

@Entity
@Table(name="publisher")

public class Publisher {
    //Create fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int ID;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "address")
    String address;

    @Column(name = "phone", nullable = false)
    String phone;

    @Column(name = "email", nullable = false)
    String email;

    @OneToMany(mappedBy = "publisherId")
    List<Book> books;

    //Create Constructor

    public Publisher() {
        // Required by JPA
    }

    public Publisher(String name, String adress, String phone, String email) {
        this.name = name;
        this.address = adress;
        if(validatePhone(phone))
            this.phone = phone;
        else
            throw new RuntimeException("Phone Entered Is Not Corrct - "+ phone);

        if(validateEmail(email))
            this.email = email;
        else
            throw new RuntimeException("Email Entered Is Not Corrct - "+ email);    }

    //Create Setters&getters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private Boolean validateEmail(String email){
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex,email);
    }
    private Boolean validatePhone(String phone){
        String phoneRegex = "^\\+?[0-9]{1,3}?[-.\\s]?\\(?[0-9]{1,4}?\\)?[-.\\s]?[0-9]{1,4}[-.\\s]?[0-9]{1,9}$";
        return Pattern.matches(phoneRegex, phone);
    }

    //Create toString func


    @Override
    public String toString() {
        return "Publisher{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
