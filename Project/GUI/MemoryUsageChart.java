package Project.GUI;

import Project.MemoryInfo;
import Project.Settings;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.axis.NumberAxis;


import java.awt.*;
import java.util.ArrayList;

public class MemoryUsageChart {
    private DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
    private final ArrayList<Float> memoryUsageHistory = new ArrayList<Float>();
    private MemoryInfo memoryInfo;

    public MemoryUsageChart(MemoryInfo memoryInfo){

        for(int i = 0; i < 60; i++){
            categoryDataset.setValue(0, "Amount", String.valueOf(i));
        }

        this.memoryInfo = memoryInfo;
    }

    public ChartPanel createChart(){
        JFreeChart lineChart = ChartFactory.createLineChart("", "Memory Usage", "", categoryDataset);

        lineChart.removeLegend();
        lineChart.setBorderVisible(false);
        lineChart.setBackgroundPaint(Color.decode(Settings.Colors.HARDWARE_PANEL));

        CategoryPlot categoryPlot = (CategoryPlot) lineChart.getCategoryPlot();
        
        NumberAxis rangeAxis = (NumberAxis)categoryPlot.getRangeAxis();
        rangeAxis.setRange(0.0, 100.0);

        categoryPlot.getRangeAxis().setLabelPaint(Color.white);
        categoryPlot.getDomainAxis().setLabelPaint(Color.white);
        categoryPlot.getDomainAxis().setAxisLineVisible(false);
        categoryPlot.getDomainAxis().setTickLabelsVisible(false);
        categoryPlot.getDomainAxis().setTickMarksVisible(false);

        // BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        // renderer.setSeriesPaint(0, Color.RED);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setBackground(Color.decode(Settings.Colors.HARDWARE_PANEL));

        return chartPanel;
    }

    public void updateDataset(){
        if(memoryUsageHistory.size() == 60){
            memoryUsageHistory.remove(0);
        }

        memoryUsageHistory.add(memoryInfo.getUsage());

        for(int i = 0; i < memoryUsageHistory.size(); i++){
            categoryDataset.setValue(memoryUsageHistory.get(i), "Amount", String.valueOf(i));
        }
    }

}