package Project.GUI;

import java.awt.*;

public class UsagePerCentComponent {
    private String title;
    private int percent;
    private int x;
    private int y;

    public UsagePerCentComponent(String text, int x, int y){
        this.title = text;
        this.x = x;
        this.y = y;
    }

    public void setPercent(int val){
        percent = val;
    }

    public int getPercent(){
        return percent;
    }

    public  void draw (Graphics g){
        String percentString = "" + percent + "%";
        FontMetrics matrics = g.getFontMetrics();

        g.drawString(title,
                (int)(x - (matrics.stringWidth(title)/2)), y);

        g.drawString(percentString,
                (int)(x - (matrics.stringWidth(percentString)/2)), y + 32);
    };

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
