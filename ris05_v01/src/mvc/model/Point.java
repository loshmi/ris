/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.awt.Color;

/**
 *
 * @author loshmi
 */
public class Point extends Shape
{
    public static final Color DEFAULT_COLOR = Color.BLUE;
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
    
    @Override
    public void moveTo (int x, int y)
    {
        setX (x);
        setY (y);
    }
    
    @Override
    public void moveBy (int x, int y)
    {
        setX (this.x + x);
        setY (this.y + y);
    }
    
    @Override
    public String toString ()
    {
       return "(" + x + ", " + y + ")"; 
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Point)
        {
            Point point = (Point)obj;
            if (x == point.getX() && y == point.getY())
            {
                return true;
            }
            else
            {
                return false;
            }
	}
        else
        {
            return false;
        }
    }
}
