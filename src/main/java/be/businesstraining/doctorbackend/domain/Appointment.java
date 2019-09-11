package be.businesstraining.doctorbackend.domain;


import be.businesstraining.doctorbackend.model.security.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Appointments")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private LocalDateTime start;
    private LocalDateTime end;

    @JsonIgnore
    @ManyToOne
    private User user;

    public Appointment(String type, LocalDateTime start, LocalDateTime end, User user) {
        this.type = type;
        this.start = start;
        this.end = end;
        this.user = user;
    }
}
