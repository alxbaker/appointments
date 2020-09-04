package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Appointment {
    //create observable lists for all appointments and today's appointments
    private static ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    private static ObservableList<Appointment> todayAppointments = FXCollections.observableArrayList();

    //date time formatter is used to display a clean date format in the table
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm");
    private int appointmentId;
    private String title;
    private String type;
    private LocalDateTime start;
    private String displayStart;
    private LocalDateTime end;
    private String displayEnd;
    private Customer customer;
    private String customerName;
    private User user;

    private String userName;

    public Appointment(int appointmentId, String title, String type, LocalDateTime start, LocalDateTime end, Customer customer, User user) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customer = customer;
        this.user = user;
        this.customerName = customer.getCustomerName();
        this.userName = user.getUserName();
        this.displayStart = start.format(formatter);
        this.displayEnd = end.format(formatter);
        //add appointments to the observable list when they get created
        appointments.add(this);
    }

    //getter for ObservableList of all appointments
    public static ObservableList<Appointment> getAppointments() { return appointments; }

    //method to remove an appointment from the ObservableList of all appointments
    public static void removeAppointment(Appointment a) {
        appointments.remove(a);
    }

    //getter for ObservableList of today's appointments
    public static ObservableList<Appointment> getTodayAppointments() {
        return todayAppointments;
    }

    //method to clear all ObservableLists for appointments
    public static void clearAppointments() {
       appointments.clear();
       todayAppointments.clear();
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
        //update display date when setting start date
        this.displayStart = start.format(formatter);
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
        //update display date when setting end date
        this.displayEnd = end.format(formatter);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        customerName = customer.toString();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        userName = user.toString();
    }

    @Override
    public String toString() {
        return title;
    }

    public String getDisplayStart() {
        return displayStart;
    }

    public void setDisplayStart(String displayStart) {
        this.displayStart = displayStart;
    }

    public String getDisplayEnd() {
        return displayEnd;
    }

    public void setDisplayEnd(String displayEnd) {
        this.displayEnd = displayEnd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}


