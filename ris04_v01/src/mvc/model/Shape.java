/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author loshmi
 */
public abstract class Shape implements Moveable
{
    private Color color;
    
    public Shape(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    @Override
    public String toString ()
    {
        return color.toString();
    }
}
