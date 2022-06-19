package pl.skillmatrix.skillmatrixspringboot.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class TeamsInWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotNull
    @Size(max = 10)
    private String nameTeam;

    @OneToMany(mappedBy = "teamsInWarehouse")
    private List<Person> personList;
}
