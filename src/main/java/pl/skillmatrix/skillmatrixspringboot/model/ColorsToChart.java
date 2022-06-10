package pl.skillmatrix.skillmatrixspringboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
public class ColorsToChart {
    private int firstColor;
    private int secondColor;
    private int thirdColor;
    private double opacity;

}
