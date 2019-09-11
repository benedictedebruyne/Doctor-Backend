package be.businesstraining.doctorbackend.service;

import be.businesstraining.doctorbackend.domain.Appointment;
import be.businesstraining.doctorbackend.model.security.User;
import be.businesstraining.doctorbackend.repository.AppointmentsRepository;
import be.businesstraining.doctorbackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AppointmentsServiceImpl implements AppointmentsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentsServiceImpl.class);

    @Autowired
    private AppointmentsRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Set<Appointment> allAppointments() {
        return new HashSet<>(repository.findAll());
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void createAppointment(String type, LocalDateTime start, LocalDateTime end, String username) {
        LOGGER.info("Avant User user = userRepository.findByUsername(username) ");
        try {
            // Récupérer le Cient
            User user = userRepository.findByUsername(username);

            // Créer le rendez-vous
            Appointment rdv = new Appointment(type, start, end, user);

            // Sauver le rendez-vous
            repository.save(rdv);

        } catch (Exception ex) {
            LOGGER.error("Exception lors de la création de rdv: " + ex.getMessage());
        }

    }

    @Override
    public void updateAppointment(Appointment appointment) {
        Appointment appointmentToUpdate = repository.findById(appointment.getId()).orElse(null);

        appointmentToUpdate.setType(appointment.getType());
        appointmentToUpdate.setStart(appointment.getStart());
        appointmentToUpdate.setEnd(appointment.getEnd());
        repository.save(appointmentToUpdate);
    }

    @Override
    public void deleteAppointment(Long id) {
        repository.delete(repository.findById(id).orElse(null));
    }

}
