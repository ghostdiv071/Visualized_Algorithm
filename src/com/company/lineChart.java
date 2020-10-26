package com.company;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.util.ArrayList;

public class lineChart extends ApplicationFrame {

    private static final String ARRAY_SIZE = "Array size";
    private static final String AMOUNT_SW_COMP = "Amount of swaps and comparisons";
    private static final String AMOUNT_SW_IS = "Amount of swaps in insert sort";
    private static final String AMOUNT_COMP_IS = "Amount of comparisons in insert sort";
    private static final String AMOUNT_SW_BIS = "Amount of swaps in binary insert sort";
    private static final String AMOUNT_COMP_BIS = "Amount of comparisons in binary insert sort";
    private static final String SORT_AN = "Sort analysis";
    private static final String GRAPH_DEP = "Graph of dependence";

    private lineChart(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        JFreeChart xyLineChart = ChartFactory.createXYLineChart(chartTitle,
                ARRAY_SIZE,
                AMOUNT_SW_COMP,
                createDataSet(),
                PlotOrientation.VERTICAL,true,true,false);

        XYSplineRenderer renderer = new XYSplineRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN );
        renderer.setSeriesPaint(2, Color.YELLOW );
        renderer.setSeriesPaint(3, Color.BLUE );

        XYPlot plot = xyLineChart.getXYPlot();
        plot.setRenderer(renderer);

        ChartPanel chartPanel = new ChartPanel(xyLineChart);
        chartPanel.setPreferredSize(new Dimension(1200,600));
        setContentPane(chartPanel);
    }

    private XYDataset createDataSet() {
        XYSeries seriesInsertSwaps = new XYSeries(AMOUNT_SW_IS);
        XYSeries seriesInsertComps = new XYSeries(AMOUNT_COMP_IS);
        XYSeries seriesBinaryInsertSwaps = new XYSeries(AMOUNT_SW_BIS);
        XYSeries seriesBinaryInsertComps = new XYSeries(AMOUNT_COMP_BIS);

        ArrayList<ArrayList<Integer>> lists = generateArrays(1000);
        drawInsertSort(lists, seriesInsertSwaps, seriesInsertComps);
        drawBinaryInsertSort(lists, seriesBinaryInsertSwaps, seriesBinaryInsertComps);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesInsertSwaps);
        dataset.addSeries(seriesInsertComps);
        dataset.addSeries(seriesBinaryInsertSwaps);
        dataset.addSeries(seriesBinaryInsertComps);

        return dataset;
    }

    private static void drawInsertSort(ArrayList<ArrayList<Integer>> lists,
                                       XYSeries seriesInsertSwaps,
                                       XYSeries seriesInsertComps){
        for (ArrayList<Integer> list : lists) {
            ArrayList<Integer> a = new ArrayList<>(list);
            Counter counter = Sort.insertSort(a);
            seriesInsertSwaps.add(a.size(), counter.getCountSwap());
            seriesInsertComps.add(a.size(), counter.getCountComp());
        }
    }

    private static void drawBinaryInsertSort(ArrayList<ArrayList<Integer>> lists,
                                             XYSeries seriesBinaryInsertSwaps,
                                             XYSeries seriesBinaryInsertComps){
        for (ArrayList<Integer> list : lists) {
            ArrayList<Integer> a = new ArrayList<>(list);
            Counter counter = Sort.binaryInsertSort(a);
            seriesBinaryInsertSwaps.add(a.size(), counter.getCountSwap());
            seriesBinaryInsertComps.add(a.size(), counter.getCountComp());
        }
    }

    private static ArrayList<ArrayList<Integer>> generateArrays(int size){
        int tSize;
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for (int i = 1; i <= size; i+=100){
            ArrayList<Integer> list = new ArrayList<>();
            tSize = i;
            for (int j = 0; j < tSize/3; j++){
                list.add(2);
            }
            for (int j = tSize/3; j < tSize; j++){
                list.add(1);
            }
            lists.add(list);
        }
        return lists;
    }

    public static void main(String[] args) {
        lineChart chart = new lineChart(SORT_AN, GRAPH_DEP);
        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}