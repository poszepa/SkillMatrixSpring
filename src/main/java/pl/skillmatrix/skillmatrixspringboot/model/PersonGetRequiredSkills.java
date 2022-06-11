package pl.skillmatrix.skillmatrixspringboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PersonGetRequiredSkills {
    private int personID;
    private List<Double> valuePercentGainedSkill = new ArrayList<>();
}
