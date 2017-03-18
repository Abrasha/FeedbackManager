package com.aabramov.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Andrii Abramov on 12/15/15.
 */
@Entity
@Table(name = "address")
public class Address implements Serializable {
    
    private int id;
    private String street;
    private int buildingNumber;
    private int postalCode;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }
    
    
    public void setId(int id) {
        this.id = id;
    }
    
    
    @Basic
    @Column(name = "build_number")
    public int getBuildingNumber() {
        return buildingNumber;
    }
    
    
    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }
    
    
    @Basic
    @Column(name = "street")
    public String getStreet() {
        return street;
    }
    
    
    public void setStreet(String street) {
        this.street = street;
    }
    
    
    @Basic
    @Column(name = "postal_code")
    public int getPostalCode() {
        return postalCode;
    }
    
    
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
    
    
    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", buildingNumber=" + buildingNumber +
                ", postalCode=" + postalCode +
                '}';
    }
    
    
}
