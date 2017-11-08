/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author loshmi
 */
public class Drawing
{
    private final DrawingFrame drawingFrame;
    
    public Drawing ()
    {
        drawingFrame = new DrawingFrame ();
        drawingFrame.init();
    }
}
