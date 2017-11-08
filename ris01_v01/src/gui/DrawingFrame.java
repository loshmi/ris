/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import rekapitulacija.Point;

/**
 *
 * @author loshmi
 */
public class DrawingFrame extends JFrame
{
    private final DrawingPanel drawingPanel;

    public DrawingFrame()
    {
        drawingPanel = new DrawingPanel();
    }
    
    public void init ()
    {
        
	drawingPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	drawingPanel.setLayout(new BorderLayout(0, 0));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	setVisible(true);
        
        getContentPane().add(drawingPanel);
        
        drawingPanel.addMouseListener
        (
                new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        Point point = new Point(e.getX(), e.getY(), Color.BLUE);
                        drawingPanel.getShapes().add(point);
                    }
                }
        );
        
    }
    
    
}
