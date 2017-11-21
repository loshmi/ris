/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import mvc.model.*;

/**
 *
 * @author loshmi
 */
public class CmdAddPoint implements Command
{
    private DrawingModel drawingModel;
    private Point point;

    public CmdAddPoint(DrawingModel drawingModel, Point point)
    {
        this.drawingModel = drawingModel;
        this.point = point;
    }

    @Override
    public void execute()
    {
        drawingModel.add(point);
    }

    @Override
    public void unexecute()
    {
        drawingModel.removeShape(point);
    }
    
}
