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
public class CmdRemovePoint implements Command
{
    private DrawingModel drawingModel;
    private Point point;

    public CmdRemovePoint(DrawingModel drawingModel, Point point) {
        this.drawingModel = drawingModel;
        this.point = point;
    }

    @Override
    public void execute()
    {
        drawingModel.removeShape(point);
    }

    @Override
    public void unexecute()
    {
        drawingModel.add(point);
    }
    
}
