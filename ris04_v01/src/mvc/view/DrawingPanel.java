/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import mvc.model.Shape;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author loshmi
 */
public class DrawingPanel extends JPanel
{
    private ArrayList<Shape> shapes;

    public DrawingPanel() {
        this.shapes = new ArrayList<>();
    }

    public void setShapes(ArrayList<Shape> shapes)
    {
        this.shapes = shapes;
    }
    
    @Override
    public void paint (Graphics g)
    {
        for (Shape shape : shapes)
        {
            ShapeDrawer.drawShape(g, shape);
        }        
        repaint();
    }
}
