package iecBilling;

import java.io.Serializable;

public class Address implements Serializable{
    private static final long serialVersionUID = -261181606702265807L;
	private String city;
    private String street;
    private int buildingNumber;
    private String apartmentNumber;

    public Address(String city, String street, int buildingNumber, String apartmentNumber) {
        this.city = city;
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    
    }
    public String toString() {
		return " City:" + getCity() + " Street: " +getStreet() + " Number: "+getBuildingNumber() + " Apartment: " + getApartmentNumber();
	}
    
}

