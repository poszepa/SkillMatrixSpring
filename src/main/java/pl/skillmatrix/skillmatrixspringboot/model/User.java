package pl.skillmatrix.skillmatrixspringboot.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Email
    @Column(nullable = false, unique = true, length = 20)
    private String email;
    @Column(nullable = false)
    private String password;
    private int enabled;
    @ManyToOne
    private Role role;
}
