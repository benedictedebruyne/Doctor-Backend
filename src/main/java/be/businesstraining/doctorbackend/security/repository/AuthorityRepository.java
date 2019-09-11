package be.businesstraining.doctorbackend.security.repository;

import be.businesstraining.doctorbackend.model.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
