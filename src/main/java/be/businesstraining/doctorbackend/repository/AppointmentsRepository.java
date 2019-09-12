package be.businesstraining.doctorbackend.repository;

import be.businesstraining.doctorbackend.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentsRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAppointmentsByType(String type);

}
