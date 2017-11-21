/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author loshmi
 */
public class DrawingView extends JFrame
{

    private final DrawingPanel drawingPanel;
    
    public DrawingView()
    {
        drawingPanel = new DrawingPanel ();

    }
    
    public void initiView ()
    {
        drawingPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	drawingPanel.setLayout(new BorderLayout(0, 0));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setBounds(100, 100, 450, 300);
        this.add (drawingPanel);
        this.setTitle("ris02_v01");
        setVisible(true);
    }

    public DrawingPanel getDrawingPanel() {
        return drawingPanel;
    }

    public void addDrawingListener (MouseListener listenForMouseClick)
    {
        drawingPanel.addMouseListener(listenForMouseClick);
    }    
}
