package util;

import controller.CalendarController;
import javafx.collections.transformation.FilteredList;
import model.Appointment;
import model.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Time {

    //this Lambda expression creates a filtered list of appointments for the logged in user
    FilteredList<Appointment> userAppointments = new FilteredList<>(Appointment.appointments, e -> e.getUser() == User.getCurrentUser());

    //this Lambda expresses creates a filtered list of appoints for the logged in user on or after today.
    FilteredList<Appointment> userCurrAppointments = new FilteredList<>(userAppointments, e -> ((e.getStart().toLocalDate().isAfter(LocalDate.now())) || (e.getStart().toLocalDate().isEqual(LocalDate.now()))));


    public static void timeAlert() {
        LocalTime currentTime = LocalTime.now();
        for (Appointment a : Appointment.todayAppts) {
            LocalTime startTime = a.getStart().toLocalTime();

            long timeDifference = ChronoUnit.MINUTES.between(currentTime, startTime);

            if (timeDifference >= 0 && timeDifference <= 5) {
                Alerts.generateInfoAlert("Upcoming event","You have a meeting with " + a.getCustomerName() + " at " + a.getStart());
            }
        }
    }
}
