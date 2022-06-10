package pl.skillmatrix.skillmatrixspringboot.service;

import org.springframework.stereotype.Service;
import pl.skillmatrix.skillmatrixspringboot.model.ColorsToChart;

import java.util.ArrayList;
import java.util.List;

@Service
public class ColorService {

    public List<ColorsToChart> colorsToChartList(double opacity) {
        List<ColorsToChart> colorsToChartList = new ArrayList<>();
        colorsToChartList.add(new ColorsToChart(98,219,226,opacity));
        colorsToChartList.add(new ColorsToChart(82,140,138,opacity));
        colorsToChartList.add(new ColorsToChart(235,106,139,opacity));
        colorsToChartList.add(new ColorsToChart(120,69,138,opacity));
        colorsToChartList.add(new ColorsToChart(201,174,150,opacity));
        colorsToChartList.add(new ColorsToChart(132,229,227,opacity));
        colorsToChartList.add(new ColorsToChart(137,236,64,opacity));
        colorsToChartList.add(new ColorsToChart(102,72,105,opacity));
        colorsToChartList.add(new ColorsToChart(64,236,241,opacity));
        colorsToChartList.add(new ColorsToChart(209,8,23,opacity));
        colorsToChartList.add(new ColorsToChart(81,102,253,opacity));
        colorsToChartList.add(new ColorsToChart(65,28,211,opacity));
        colorsToChartList.add(new ColorsToChart(106,17,128,opacity));
        colorsToChartList.add(new ColorsToChart(166,89,237,opacity));
        colorsToChartList.add(new ColorsToChart(130,121,66,opacity));
        return colorsToChartList;
    }




}
