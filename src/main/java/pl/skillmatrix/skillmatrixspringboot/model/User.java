package pl.skillmatrix.skillmatrixspringboot.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @SuperBuilder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email
    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false)
    private String password;

    private boolean enabled = false;

    private String role;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createOn;

    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updateOn;
}
