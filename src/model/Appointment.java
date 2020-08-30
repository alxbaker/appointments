package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Appointment {
    public static ObservableList<Appointment> appointments = FXCollections.observableArrayList();
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
        this.displayEnd = start.format(formatter);
        appointments.add(this);
    }

    public static Appointment getAppointment(int id) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == id) {
                return appointment;
            }
        }
        return null;
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

    public static void clearAppointments() {
       appointments.clear();
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
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
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
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
        userName = user.toString();
        this.user = user;
    }

    @Override
    public String toString() {
        return title;
    }
}


