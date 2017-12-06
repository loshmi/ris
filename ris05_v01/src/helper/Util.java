/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.ArrayList;
import mvc.model.Point;
import mvc.model.Shape;

/**
 *
 * @author loshmi
 */
public class Util
{
    public static ArrayList<String> convertPointsToStrings (ArrayList<Shape> points)
    {
        ArrayList<String> stringList = new ArrayList<> ();
        
        for (Shape shape : points)
        {
            stringList.add (shape.toString());
        }
        
        return stringList;
    }
    
    public static Point parsePoint (String message)
    {
        return new Point (getX(message), getY(message), Point.DEFAULT_COLOR);
    }
    
    private static int getX (String message)
    {
        int startIndex = message.indexOf("(");
        int endIndex = message.indexOf(",");
        
        String x = message.substring(startIndex + 1, endIndex);
        
        return Integer.parseInt(x);
    }
    
    private static int getY (String message)
    {
        int startIndex = message.indexOf(",");
        int endIndex = message.indexOf(")");
        
        String y = message.substring(startIndex + 2, endIndex);
        
        return Integer.parseInt(y);
    }
}
