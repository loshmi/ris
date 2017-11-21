/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.awt.Graphics;
import mvc.model.Point;
import mvc.model.Shape;

/**
 *
 * @author loshmi
 */
public class ShapeDrawer
{
    public static void drawShape (Graphics g, Shape shape)
    {
        g.setColor(shape.getColor());
        if (shape instanceof Point)
        {
            drawPoint (g, shape);
        }
    }
    
    private static void drawPoint (Graphics g, Shape shape)
    {
        Point point = (Point) shape;
        g.drawLine(point.getX() - ViewConstants.CROSS_SIZE, point.getY(), point.getX() + ViewConstants.CROSS_SIZE, point.getY());
        g.drawLine(point.getX(), point.getY() + ViewConstants.CROSS_SIZE, point.getX(), point.getY() - ViewConstants.CROSS_SIZE);
    }
}
