package pl.skillmatrix.skillmatrixspringboot.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class OwnedSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skills skills;

    @Column(name = "gain_skill")
    private boolean gainSkill = false;
}
