package be.businesstraining.doctorbackend.service;

import be.businesstraining.doctorbackend.domain.Appointment;

import java.util.List;

public class AppointmentsValidationUtil {

    public static Boolean availableTimeslot(List<Appointment> agenda, Appointment appointment) {
        Boolean ret = true;
        for (Appointment a : agenda) {
            // Différent thérapeute
            if (a.getType().equals(appointment.getType()) && (
                    (       // a.start <= appointment.start < a.end
                            (a.getStart().isBefore(appointment.getStart()) || a.getStart().isEqual(appointment.getStart()))
                            &&
                            (a.getEnd().isAfter(appointment.getStart())))
                            // OR a.start < appointment.end <= a.end
                    ) || (
                            (a.getStart().isBefore(appointment.getEnd()))
                            &&
                            (a.getEnd().isAfter(appointment.getEnd()) || (a.getEnd().isEqual(appointment.getEnd())))
                    )
            )
            {
                ret = false;
            }
        }
        return ret;
    }
}
