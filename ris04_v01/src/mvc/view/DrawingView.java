/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author loshmi
 */
public class DrawingView extends JFrame
{

    private final DrawingPanel drawingPanel;
    private final JPanel buttonPanel;
    private final JPanel jListPanel;
    
    JButton btnOpen;
    JButton btnSave;
    JList list;
    
    public DrawingView()
    {
        drawingPanel = new DrawingPanel ();
        buttonPanel = new JPanel();
        jListPanel = new JPanel ();
    }
    
    private void initDrawingPanel ()
    {
        this.add (drawingPanel, BorderLayout.CENTER);
    }
    
    private void initButtons ()
    {        
	buttonPanel.setLayout(new GridLayout (1, 2));
        btnOpen = new JButton ("Open");
        btnSave = new JButton ("Save");
        buttonPanel.add(btnOpen);
        buttonPanel.add(btnSave);        
        this.add (buttonPanel, BorderLayout.NORTH);
    }
    
    private void initJlist ()
    {       
        list = new JList();
        JScrollPane scrollPane = new JScrollPane (list);
        jListPanel.add(scrollPane);
        this.add (jListPanel, BorderLayout.SOUTH);
    }
    
    public void initView ()
    {
        initButtons();
        initDrawingPanel();
        initJlist();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setSize(600, 400);
        this.setTitle("ris04_v01");
        setVisible(true);
    }

    public DrawingPanel getDrawingPanel()
    {
        return drawingPanel;
    }

    public void addDrawingListener (MouseListener listenForMouseClick)
    {
        drawingPanel.addMouseListener(listenForMouseClick);
    }
    
    public void addActionListenerOpen (ActionListener al)
    {
        btnOpen.addActionListener(al);
    }
    
    public void addActionListenerSave (ActionListener al)
    {
        btnSave.addActionListener(al);
    }
    
    public void setListData (Object [] data)
    {
        list.setListData(data);
    }
}
