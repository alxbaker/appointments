package util;

import controller.CalendarController;
import model.Appointment;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Time {

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
