package util;

import model.Appointment;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Time {

    //method to generate alert if appointment is withint 5 minutes of user log in
    public static void timeAlert() {
        LocalTime currentTime = LocalTime.now();
        for (Appointment a : Appointment.getTodayAppointments()) {
            LocalTime startTime = a.getStart().toLocalTime();

            long timeDifference = ChronoUnit.MINUTES.between(currentTime, startTime);

            if (timeDifference >= 0 && timeDifference <= 5) {
                Alerts.generateInfoAlert("Upcoming event","You have a meeting with " + a.getCustomerName() + " at " + a.getStart());
            }
        }
    }
}
