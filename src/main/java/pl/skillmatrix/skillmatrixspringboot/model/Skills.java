package pl.skillmatrix.skillmatrixspringboot.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 60)
    private String nameSkill;

    @NotNull
    private Boolean isRequired = false;

    @NotNull
    private Boolean gainSkill = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private DepartmentsInWarehouse departmentsInWarehouse;

    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Person> personList;

    @CreationTimestamp
    @Column(name = "create_time")
    private LocalDate createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private LocalDate updateTime;






}