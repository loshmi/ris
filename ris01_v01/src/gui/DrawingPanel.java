/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import rekapitulacija.Shape;

/**
 *
 * @author loshmi
 */
public class DrawingPanel extends JPanel
{
    private final ArrayList<Shape> shapes = new ArrayList<>();

    public ArrayList<Shape> getShapes() {
        return shapes;
    }
    
    @Override
    public void paint (Graphics g)
    {
        for (Shape shape : shapes)
        {
            shape.draw(g);
        }
        
        repaint();
    }
    
}
