package be.businesstraining.doctorbackend.rest;

import be.businesstraining.doctorbackend.domain.Appointment;

import be.businesstraining.doctorbackend.repository.AppointmentsRepository;
import be.businesstraining.doctorbackend.service.AppointmentsService;
import be.businesstraining.doctorbackend.service.AppointmentsValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/appointments")
@CrossOrigin
public class AppointmentsResource {


    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentsResource.class);
    private AppointmentsRepository repository;
    private AppointmentsService service;
    private Set<Appointment> app;


    public AppointmentsResource(AppointmentsRepository repository, AppointmentsService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public List<Appointment> allAppointments() {

        return repository.findAll();
    }

    @GetMapping("/{type}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Appointment> getAppointmentByType(@PathVariable String type) {

//        return repository.findById(id).orElse(null);
        return repository.findAppointmentsByType(type);
    }

    @PostMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> addAppointment(@PathVariable String username, @RequestBody Appointment c) {
        try {
            if (AppointmentsValidationUtil.availableTimeslot(allAppointments(), c)) {
                service.createAppointment(c.getType(), c.getStart(), c.getEnd(), username);
                return new ResponseEntity<String>("Success de la création de rendez-vous", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Le timeslot est déjà pris", HttpStatus.OK);
            }
        } catch (Exception ex) {
            LOGGER.error("Exception lors de la prise de RDV: " + ex);
            return new ResponseEntity<String>("Erreur lors de la prise de RDV: " + ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> updateAppointment(@RequestBody Appointment c) {
        try {
            if (AppointmentsValidationUtil.availableTimeslot(allAppointments(), c)) {
                service.updateAppointment(c);
                return new ResponseEntity<String>("Success de la mise à jour du rendez-vous", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Le timeslot est déjà pris, le rendez-vous n'est donc pas modifié", HttpStatus.OK);
            }
        } catch (Exception ex) {
            LOGGER.error("Exception lors de la mise à jour du RDV: " + ex);
            return new ResponseEntity<String>("Erreur lors de la mise à jour du RDV: " + ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        try {
            service.deleteAppointment(id);
            return new ResponseEntity<String>("Success de l'annulation du rendez-vous", HttpStatus.OK);
        } catch (Exception ex){
            LOGGER.error("Exception lors de l'annulation du RDV: " + ex);
            return new ResponseEntity<String>("Erreur lors de l'annulation du RDV: " + ex.getMessage(), HttpStatus.CONFLICT);
        }
    }

}
