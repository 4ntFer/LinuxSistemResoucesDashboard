package Project.GUI;

import Project.CpuInfo;
import Project.MemoryInfo;
import org.jfree.chart.ChartPanel;

import Project.Settings;

import javax.swing.*;
import java.awt.*;

public class HardwarePanel extends JPanel {
    private static final int PANEL_WIDTH = (int)(DashBoardWindow.WINDOW_WIDTH*0.7);
    private static final int PANEL_HEIGHT = (int)(DashBoardWindow.WINDOW_HEIGHT);

    private static final String PROCESSES_STRING = "Processes: ";
    private static final String THREADS_STRING = "Threads: ";
    private static final String IDLE_TIME_STRING = "Idle Time: ";

    private static final Font fonte=  new Font("Arial", Font.BOLD, 24);
    private UsagePerCentComponent cpuComponent;
    private UsagePerCentComponent memComponent;
    private CpuInfo cpu;
    private MemoryInfo mem;
    private StringComponent processes = new StringComponent(PROCESSES_STRING);
    private StringComponent threads = new StringComponent(THREADS_STRING);
    private StringComponent idleTime = new StringComponent(IDLE_TIME_STRING);
    private ChartMemoryInfoPanel memoryChart;
    private MemoryUsageChart memoryUsageChart;
    private CpuUsageChart cpuUsageChart;

    public HardwarePanel(CpuInfo cpu, MemoryInfo mem){
        super();
        this.cpu = cpu;
        this.mem = mem;
        this.setLayout(null);

        setBackground(Color.decode(Settings.Colors.HARDWARE_PANEL));
        setBounds((int)(DashBoardWindow.WINDOW_WIDTH*0.3), 0,
                (int)(DashBoardWindow.WINDOW_WIDTH*0.7), (int)DashBoardWindow.WINDOW_HEIGHT);
        setForeground(Color.WHITE);

        cpuComponent =  new UsagePerCentComponent("CPU usage:",
                (int)(PANEL_WIDTH*1/4), (int)(PANEL_HEIGHT*0.17));

        memComponent = new UsagePerCentComponent("RAM usage:",
                (int)(PANEL_WIDTH*3/4), (int)(PANEL_HEIGHT*0.17));

        initExtraInfoPosition();
        initChart();
    }



    public UsagePerCentComponent getCpuComponente(){
        return cpuComponent;
    }

    public UsagePerCentComponent getMemoryComponente(){
        return memComponent;
    }

    public void hardwarePanelUpdate(){
        cpuComponent.setPercent((int)cpu.getUsage());
        memComponent.setPercent((int)mem.getUsage());
        processes.setString(PROCESSES_STRING + cpu.getNumberOfProcess());
        threads.setString(THREADS_STRING + cpu.getNumberOfThreads());
        idleTime.setString(IDLE_TIME_STRING + (int)cpu.getIdleTime() + "%");
        memoryChart.updateDataset();
        memoryUsageChart.updateDataset();
        cpuUsageChart.updateDataset();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setFont(fonte);
        cpuComponent.draw(g);
        memComponent.draw(g);

        processes.draw(g);
        threads.draw(g);
        idleTime.draw(g);
    }

    private void initExtraInfoPosition(){
        processes.setX((int)(PANEL_WIDTH*1/8));
        threads.setX((int)(PANEL_WIDTH*1/8));
        idleTime.setX((int)(PANEL_WIDTH*1/8));

        processes.setY((int)(PANEL_HEIGHT*0.17) + 100);
        threads.setY((int)(PANEL_HEIGHT*0.17) + 140);
        idleTime.setY((int)(PANEL_HEIGHT*0.17) + 180);
    }

    private void initChart(){
        memoryChart = new ChartMemoryInfoPanel(mem);
        memoryUsageChart = new MemoryUsageChart(mem);
        cpuUsageChart = new CpuUsageChart(cpu);

        ChartPanel memChart = memoryChart.createChart();
        ChartPanel memUsageChart = memoryUsageChart.createChart();
        ChartPanel cpuUsageChartPanel = cpuUsageChart.createChart();
        
        memChart.setBounds((int)(PANEL_WIDTH*3/4 - Settings.MemoryChart.WIDTH/2), (int)((PANEL_HEIGHT*0.17 + 60)),
                Settings.MemoryChart.WIDTH, Settings.MemoryChart.HEIGHT);

        memUsageChart.setBounds((int)(PANEL_WIDTH*2/4 - Settings.MemoryUsageChart.WIDTH/2), (int)((PANEL_HEIGHT*0.3 + 150)),
                Settings.MemoryUsageChart.WIDTH, Settings.MemoryUsageChart.HEIGHT);

        cpuUsageChartPanel.setBounds((int)(PANEL_WIDTH*2/4 - Settings.MemoryUsageChart.WIDTH/2), (int)((PANEL_HEIGHT*0.3 + Settings.MemoryUsageChart.HEIGHT + 150)),
            Settings.MemoryUsageChart.WIDTH, Settings.MemoryUsageChart.HEIGHT);

        add(memChart);
        add(memUsageChart);
        add(cpuUsageChartPanel);
    }
}
