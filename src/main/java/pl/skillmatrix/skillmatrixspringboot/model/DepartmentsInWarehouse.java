package pl.skillmatrix.skillmatrixspringboot.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentsInWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 30)
    private String nameDepartment;

    @OneToMany(mappedBy = "departmentsInWarehouse")
    private List<Person> personList;

    @OneToMany(mappedBy = "departmentsInWarehouse")
    private List<Skills> skillsList;
}