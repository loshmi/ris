/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import mvc.model.DrawingModel;
import mvc.model.Point;

/**
 *
 * @author loshmi
 */
public class CmdUpdatePoint implements Command
{
    private Point pointOriginal;
    private Point pointNewState;
    private Point pointOldState;

    public CmdUpdatePoint(Point pointOriginal, Point pointNewState)
    {
        this.pointOriginal = pointOriginal;
        this.pointNewState = pointNewState;
        this.pointOldState = new Point (pointOriginal.getX(), pointOriginal.getY(), pointOriginal.getColor());
    }   
    
    @Override
    public void execute()
    {
        pointOldState.setX(pointOriginal.getX());
        pointOldState.setY(pointOriginal.getY());
        pointOldState.setColor(pointOriginal.getColor());
        pointOriginal.moveTo(pointNewState.getX(), pointNewState.getY());
        pointOriginal.setColor(pointNewState.getColor());
    }

    @Override
    public void unexecute()
    {
        pointOriginal.moveTo(pointOldState.getX(), pointOldState.getY());
        pointOriginal.setColor(pointOldState.getColor());
    }    
}
