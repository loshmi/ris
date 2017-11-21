/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import mvc.model.DrawingModel;
import mvc.model.Point;
import mvc.view.DrawingView;
import mvc.view.ViewConstants;

/**
 *
 * @author loshmi
 */
public class DrawingController
{
    private final DrawingModel drawingModel;
    private final DrawingView drawingView;

    public DrawingController(DrawingModel drawingModel, DrawingView drawingView)
    {
        this.drawingModel = drawingModel;
        this.drawingView = drawingView;
        this.drawingView.initiView();
     
        this.drawingView.addDrawingListener (new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        Point point = new Point(e.getX(), e.getY(), Color.BLUE);
                        drawingModel.add(point);
                    }
                });
        this.drawingView.getDrawingPanel().setShapes(this.drawingModel.getShapes());
    }
}
