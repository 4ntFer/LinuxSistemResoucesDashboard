package Project.GUI;

import java.awt.*;

public class StringComponent {
    private String string;
    private int x = 0;
    private int y = 0;

    public StringComponent(String string){
        this.string = string;
    }

    public void draw(Graphics g){
        g.drawString(string, x, y);
    }

    public void setBoxInCenter(Graphics g){
        FontMetrics fontMetrics = g.getFontMetrics();
        x = x - fontMetrics.stringWidth(string)/2;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
