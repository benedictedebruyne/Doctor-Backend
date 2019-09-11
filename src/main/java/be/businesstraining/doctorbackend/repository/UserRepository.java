package be.businesstraining.doctorbackend.repository;

import be.businesstraining.doctorbackend.domain.Appointment;
import be.businesstraining.doctorbackend.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Set<Appointment> findAllByUsername(String username);
}
