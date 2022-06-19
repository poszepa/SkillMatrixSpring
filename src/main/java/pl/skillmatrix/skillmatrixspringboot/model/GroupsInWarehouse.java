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
@Table(name = "groups_in_warehouse")
public class GroupsInWarehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 10)
    @Column(name = "name_group")
    private String nameGroup;

    @OneToMany(mappedBy = "groupsInWarehouse")
    private List<Person> personList;
}