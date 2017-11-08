/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rekapitulacija;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author loshmi
 */
public class Point extends Shape
{
    public static int cross = 4;
    private int x;
    private int y;

    public Point(int x, int y, Color color)
    {
        super(color);
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public void draw (Graphics g)
    {
        g.setColor(this.getColor());
        g.drawLine(x - cross, y, x + cross, y);
        g.drawLine(x, y - cross, x, y + cross);
    }
    
    public void moveTo (int x, int y)
    {
        setX (x);
        setY (y);
    }
    
    public void moveBy (int x, int y)
    {
        setX (this.x + x);
        setY (this.y + y);
    }
}
