package pl.skillmatrix.skillmatrixspringboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class PersonCount {
    private int countPeople;
    private String departmentName;
}
