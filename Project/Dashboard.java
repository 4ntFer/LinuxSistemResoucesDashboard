package Project;

import Project.GUI.HardwarePanel;
import Project.GUI.ProcessListPanel;
import Project.GUI.DashBoardWindow;
import Project.GUI.ProcessListPanel;

public class Dashboard implements Runnable {
    private CpuInfo cpuInfo = new CpuInfo();
    private MemoryInfo memInfo = new MemoryInfo();
    private ProcessList processList = new ProcessList();
    private DashBoardWindow dashBoardWindow;
    private ProcessListPanel processListPanel;
    private HardwarePanel hardwarePanel;

    private Thread thread;



    public Dashboard(){
        processListPanel = new ProcessListPanel(processList);
        hardwarePanel = new HardwarePanel(cpuInfo, memInfo);
        dashBoardWindow = new DashBoardWindow();

        dashBoardWindow.add(hardwarePanel);
        dashBoardWindow.add(processListPanel);

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run(){

        while(true){

            updateInfo();

            hardwarePanel.hardwarePanelUpdate();
            processListPanel.update();
            processListPanel.repaint();
            hardwarePanel.repaint();

            try{
                Thread.sleep(Settings.UPDATE_PERIOD);
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }



    }

    /**
     * Atualiza todas as informações.
     */
    private void updateInfo(){
        cpuInfo.update();
        memInfo.update();
        processList.update();
    }


}
