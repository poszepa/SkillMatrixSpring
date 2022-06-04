package pl.skillmatrix.skillmatrixspringboot.model;

import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@Table(name = "Persons")
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotNull
    @Range(min = 300000, max = 9999999)
    private Integer expertis;

    @Pattern(regexp="^[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]*$",message = "Invalid Input")
    @Size(min = 3)
    @NotNull
    @Size(max = 50)
    @Column(name = "last_name")
    private String surname;

    @Pattern(regexp="^[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]*$",message = "Invalid Input")
    @Size(min = 3)
    @NotNull
    @Size(max = 50)
    @Column(name = "first_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "departments_id")
    private DepartmentsInWarehouse departmentsInWarehouse;

    @ManyToOne
    @JoinColumn(name = "function_id")
    private FunctionInWarehouse functionInWarehouse;

    @ManyToOne
    @JoinColumn(name = "groups_id")
    private GroupsInWarehouse groupsInWarehouse;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private TeamsInWarehouse teamsInWarehouse;

    @ManyToMany(mappedBy = "personList")
    private List<Skills> skillsList;

    private Boolean active = true;

    @OneToMany(mappedBy = "person")
    private List<OwnedSkill> ownedSkills;


}
