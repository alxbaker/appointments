package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer {
    //an ObservableList for all customers
    public static ObservableList<Customer> customers = FXCollections.observableArrayList();
    private int customerId;
    private String customerName;
    private int addressID;
    private String address;
    private String address2;
    private String postalCode;
    private String phone;
    private City city;

    public Customer(int customerId, String customerName, int addressID, String address, String address2, String postalCode, String phone, City city) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.addressID = addressID;
        this.address = address;
        this.address2 = address2;
        this.postalCode = postalCode;
        this.phone = phone;
        this.city = city;
        //add customers to ObservableList when they are created
        customers.add(this);
    }

    //method to return customer object based on customer id
    public static Customer getCustomer(int id) {
        for (Customer c : customers) {
            if (c.getCustomerID() == id) {
                return c;
            }
        }
        return null;
    }

    //method to clear customer ObservableList
    public static void clearCustomers() {
        customers.clear();
    }

    public int getCustomerID() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getAddressID() {
        return addressID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return customerName;
    }
}
