package Project.GUI;

import Project.CpuInfo;
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

public class CpuUsageChart {
    private DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
    private final ArrayList<Float> cpuUsageHistory = new ArrayList<Float>();
    private CpuInfo cpuInfo;

    public CpuUsageChart(CpuInfo cpuInfo){

        for(int i = 0; i < 60; i++){
            categoryDataset.setValue(0, "Amount", String.valueOf(i));
        }

        this.cpuInfo = cpuInfo;
    }

    public ChartPanel createChart(){
        JFreeChart lineChart = ChartFactory.createLineChart("", "Cpu Usage", "", categoryDataset);

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
        if(cpuUsageHistory.size() == 60){
            cpuUsageHistory.remove(0);
        }

        cpuUsageHistory.add(cpuInfo.getUsage());

        for(int i = 0; i < cpuUsageHistory.size(); i++){
            categoryDataset.setValue(cpuUsageHistory.get(i), "Amount", String.valueOf(i));
        }
    }

}