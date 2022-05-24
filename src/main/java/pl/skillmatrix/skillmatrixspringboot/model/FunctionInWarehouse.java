package pl.skillmatrix.skillmatrixspringboot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "function_in_warehouse")
public class FunctionInWarehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Size(max = 30)
    @NotNull
    @Column(name = "function_name")
    private String functionName;

    @OneToMany(mappedBy = "functionInWarehouse")
    private List<Person> personList;
}
