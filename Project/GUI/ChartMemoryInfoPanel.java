package Project.GUI;

import Project.MemoryInfo;
import Project.Settings;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;

public class ChartMemoryInfoPanel {
    private MemoryInfo memoryInfo;
    DefaultPieDataset barDataset = new DefaultPieDataset();
    public ChartMemoryInfoPanel(MemoryInfo memoryInfo){
        this.memoryInfo = memoryInfo;
    }


    public ChartPanel createChart(){
        updateDataset();

        JFreeChart pieChart = ChartFactory.createPieChart("", barDataset, false, false, false);

        pieChart.setBackgroundPaint(Color.decode(Settings.Colors.HARDWARE_PANEL));
        pieChart.setBorderVisible(false);
        
        PiePlot piePlot = (PiePlot) pieChart.getPlot();

        piePlot.setSectionPaint(Settings.MemoryChart.FREE_MEMORY_LABEL, Color.decode(Settings.MemoryChart.FREE_MEMORY_COLOR));
        piePlot.setSectionPaint(Settings.MemoryChart.USED_MEMORY_LABEL, Color.decode(Settings.MemoryChart.USED_MEMORY_COLOR));
        piePlot.setSectionPaint(Settings.MemoryChart.CACHE_MEMORY_LABEL, Color.decode(Settings.MemoryChart.CACHE_MEMORY_COLOR));

        piePlot.setBackgroundPaint(Color.decode(Settings.Colors.HARDWARE_PANEL));
        piePlot.setShadowPaint(null);

        ChartPanel barChartPanel = new ChartPanel(pieChart);

        barChartPanel.setBackground(Color.decode(Settings.Colors.HARDWARE_PANEL));

        return barChartPanel;
    }

    public void updateDataset(){
        barDataset.setValue(Settings.MemoryChart.FREE_MEMORY_LABEL, memoryInfo.getFreeMem());
        barDataset.setValue(Settings.MemoryChart.USED_MEMORY_LABEL, memoryInfo.getUsedMem());
        barDataset.setValue(Settings.MemoryChart.CACHE_MEMORY_LABEL, memoryInfo.getCacheMem());
    }
}