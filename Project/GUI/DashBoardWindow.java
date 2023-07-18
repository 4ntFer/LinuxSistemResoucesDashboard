package Project.GUI;

import javax.swing.*;

public class DashBoardWindow {
    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;
    private JFrame window;

    public DashBoardWindow(){
        window = new JFrame();

        window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(null);
        window.setVisible(true);
    }

    public void add(JPanel panel){
        window.add(panel);
    }
}
