package be.businesstraining.doctorbackend.service;

import be.businesstraining.doctorbackend.domain.Appointment;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Set;

public interface AppointmentsService {

    Set<Appointment> allAppointments();

    Appointment getAppointmentById(Long id);

    void createAppointment(String type, LocalDateTime start, LocalDateTime end,
                           @PathVariable String userName);

    void updateAppointment(Appointment appointment);

    void deleteAppointment(Long id);

}
