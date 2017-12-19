/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author loshmi
 */
public class DrawingModel implements Serializable
{
    private static final long serialVersionUID = 6926472295622776147L;
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
