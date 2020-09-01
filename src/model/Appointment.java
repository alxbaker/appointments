package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class Appointment {
    public static ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    public static ObservableList<Appointment> todayAppts = FXCollections.observableArrayList();

    //this Lambda expression creates a filtered list of appointments for the logged in user
    FilteredList<Appointment> userAppointments = new FilteredList<>(Appointment.appointments, e -> e.getUser() == User.getCurrentUser());

    //this Lambda expresses creates a filtered list of appoints for the logged in user on or after today.
    FilteredList<Appointment> userCurrAppointments = new FilteredList<>(userAppointments, e -> ((e.getStart().toLocalDate().isAfter(LocalDate.now())) || (e.getStart().toLocalDate().isEqual(LocalDate.now()))));

    //this Lambda expression creates a filtered list of appointments for the current user in the current month
    FilteredList<Appointment> monthAppointments = new FilteredList<>(userAppointments, e -> e.getStart().getMonth() == LocalDate.now().getMonth());

    //this Lambda expression creates a filtered list of appointments for the current this week of the year
    FilteredList<Appointment> weekAppointments = new FilteredList<>(userAppointments, e -> e.getStart().get(WeekFields.of(Locale.getDefault()).weekOfYear()) == LocalDate.now().get(WeekFields.of(Locale.getDefault()).weekOfYear()));

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
        appointments.add(this);
       // setTodayAppt(user,this);
    }

    public static Appointment getAppointment(int id) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId() == id) {
                return appointment;
            }
        }
        return null;
    }


    public static void setTodayAppt(User u, Appointment a) {
        if ((u == User.getCurrentUser() && (a.getStart().toLocalDate().equals(LocalDate.now())))) {
            todayAppts.add(a);
        }
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
       todayAppts.clear();
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
        this.displayStart = start.format(formatter);
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
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
        userName = user.toString();
        this.user = user;
    }

    @Override
    public String toString() {
        return title;
    }
}


