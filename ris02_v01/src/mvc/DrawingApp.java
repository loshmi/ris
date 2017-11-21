/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc;

import mvc.controller.DrawingController;
import mvc.model.DrawingModel;
import mvc.view.DrawingView;

/**
 *
 * @author loshmi
 */
public class DrawingApp
{
    public static void main (String []args)
    {
        DrawingView drawingView = new DrawingView ();
        DrawingModel drawingModel = new DrawingModel ();
        DrawingController drawingController = new DrawingController (drawingModel, drawingView);
    }
}
