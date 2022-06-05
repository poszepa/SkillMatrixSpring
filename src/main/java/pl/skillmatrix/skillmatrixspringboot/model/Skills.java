package pl.skillmatrix.skillmatrixspringboot.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 60)
    private String nameSkill;

    @NotNull
    private Boolean isRequired = false;

    @ManyToOne(fetch = FetchType.EAGER)
    private DepartmentsInWarehouse departmentsInWarehouse;

    @ManyToMany
    @ToString.Exclude
    private List<Person> personList;

    @CreationTimestamp
    @Column(name = "create_time")
    private LocalDateTime createTime;

    @UpdateTimestamp
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "skills")
    @ToString.Exclude
    private List<OwnedSkill> ownedSkills;






}