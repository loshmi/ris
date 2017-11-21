/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.util.ArrayList;

/**
 *
 * @author loshmi
 */
public class DrawingModel
{
    private final ArrayList<Shape> shapes = new ArrayList<>();

    public ArrayList<Shape> getShapes() {
        return shapes;
    }
    
    public void add (Shape shape)
    {
        shapes.add(shape);
    }
    
    public Shape getShape (int index)
    {
        return shapes.get(index);
    }
    
    public boolean removeShape (Shape shape)
    {
        return shapes.remove(shape);
    }
}
