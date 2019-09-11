package be.businesstraining.doctorbackend.repository;

import be.businesstraining.doctorbackend.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentsRepository extends JpaRepository<Appointment, Long> {

}
