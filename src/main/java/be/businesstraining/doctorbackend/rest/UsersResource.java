package be.businesstraining.doctorbackend.rest;


import be.businesstraining.doctorbackend.model.security.User;
import be.businesstraining.doctorbackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentsResource.class);
    private UserRepository repository;


    public UsersResource(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> allUsers() {

        return repository.findAll();
    }

}
