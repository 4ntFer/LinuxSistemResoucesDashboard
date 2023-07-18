package Project.GUI;

import Project.ProcessInfo;
import Project.ProcessList;
import Project.ProcessState;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ProcessListPanel extends JPanel {
    private static final Color PANEL_COLOR = new Color(37, 33, 54);
    private static final int PROCESS_PANEL_WIDTH = (int)(DashBoardWindow.WINDOW_WIDTH*0.3);
    private ArrayList<ProcessInfo> processes;
    JScrollPane scrollPanel;
    DefaultTableModel model;
    JTable table;
    JLabel title;

    public ProcessListPanel(ProcessList proc){
        super();
        processes = proc.getProcesses();

        setBackground(PANEL_COLOR);
        setBounds(0,0, (int) (PROCESS_PANEL_WIDTH), DashBoardWindow.WINDOW_HEIGHT);
        setLayout(null);

        initTitle();
        initScrollPanel();
    }

    public void update(){
        String[] row = new String[4];

        model.setRowCount(0);

        for(int i = 0 ;  i<processes.size() ; i++){
            row[0] = "" + processes.get(i).getPID();
            row[1] = "" + processes.get(i).getUser();
            row[2] = "" + processes.get(i).getCpuUsage();
            row[3] = "" + processes.get(i).getMemUsage();
            model.addRow(row);
        }

        table.removeAll();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
    }

    private void initTitle(){
        title = new JLabel("Processes");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(0,0,PROCESS_PANEL_WIDTH,80);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        this.add(title, BorderLayout.CENTER);
    }
    private void initScrollPanel(){
        model = new DefaultTableModel(){
            public boolean isCellEditable(int rowIndex, int mColIndex){
                return false;
            }
        };
        model.addColumn("PID");
        model.addColumn("User");
        model.addColumn("CPU%");
        model.addColumn("RAM%");
        table = new JTable(model);
        table.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() > 1){
                    int row = table.rowAtPoint(e.getPoint());
                    openProcessScreen(row);
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        update();
        scrollPanel = new JScrollPane(table);
        scrollPanel.setBounds((int)(PROCESS_PANEL_WIDTH*0.1) ,80,
                (int)(PROCESS_PANEL_WIDTH*0.8),(int)(DashBoardWindow.WINDOW_HEIGHT*0.78));

        add(scrollPanel);
    }

    private void openProcessScreen(int row){
        int pid = Integer.parseInt((String)table.getValueAt(row, 0));

        ProcessInfo process = processes.stream().
                filter(p->
                        p.getPID() == pid).
                findFirst().orElse(null);

        JFrame processWindow = new JFrame();
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);

        tableModel.setColumnCount(2);

        String[] commandString = {"Command", process.getCommand()};
        tableModel.addRow(commandString);

        String[] PIDString = {"PID", ""+process.getPID()};
        tableModel.addRow(PIDString);

        String[] stateString = {"state", "" + ProcessState.getStringFromProcessState(process.getState())};
        tableModel.addRow(stateString);

        String[] virtualMemString = {"Virual Memory Size","" + process.getVirtualMemorySize()+"KB"};
        tableModel.addRow(virtualMemString);

        String[] currentPhysicalMemoryUsageString = {"Current Physical Memory Usage", "" + process.getCurrentPhysicalMemoryUsage()+"KB"};
        tableModel.addRow(currentPhysicalMemoryUsageString);

        String[] heapString = {"Heap Size","" + process.getHeapSize()+"KB"};
        tableModel.addRow(heapString);

        String[] stackString = {"Stack Size","" + process.getStackSize()+"KB"};
        tableModel.addRow(stackString);

        String[] dataString = {"Data Size","" + process.getDataSize()+"KB"};
        tableModel.addRow(dataString);

        String[] threadsString = {"Thread","" + process.getNumberOfThreads()};
        tableModel.addRow(threadsString);

        processWindow.add(table);

        processWindow.setSize(PROCESS_PANEL_WIDTH, DashBoardWindow.WINDOW_HEIGHT);
        processWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        processWindow.setVisible(true);

    }
}
