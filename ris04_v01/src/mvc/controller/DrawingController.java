/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

import java.util.logging.Logger;
import command.CmdAddPoint;
import helper.ActionListenerOpen;
import helper.ActionListenerSave;
import helper.Util;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import mvc.model.DrawingModel;
import mvc.model.Point;
import mvc.view.DrawingView;

/**
 *
 * @author loshmi
 */
public class DrawingController
{
    private final DrawingModel drawingModel;
    private final DrawingView drawingView;
    public final static Logger LOGGER = Logger.getLogger("logger");
    private ArrayList<String> listEntries;

    public DrawingController(DrawingModel drawingModel, DrawingView drawingView)
    {
        listEntries = new ArrayList<> ();
        this.drawingModel = drawingModel;
        this.drawingView = drawingView;
        this.drawingView.initView();
     
        this.drawingView.addDrawingListener (new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        Point point = new Point(e.getX(), e.getY(), Point.DEFAULT_COLOR);
                        CmdAddPoint cmdAddPoint = new CmdAddPoint(drawingModel, point);
                        cmdAddPoint.execute();
                        LOGGER.log(Level.INFO, cmdAddPoint.toString());
                        
                        ArrayList<String> tempList = Util.convertPointsToStrings(drawingModel.getShapes());
                        Collections.reverse(tempList);
                        drawingView.setListData(tempList.toArray());
                    }
                });
        this.drawingView.getDrawingPanel().setShapes(this.drawingModel.getShapes());
        
        try
        {
            File dir = new File("log");
            boolean kreiran = dir.mkdir();
            String fileName = dir.getAbsolutePath() + "/log" + System.currentTimeMillis();
            File file = new File (fileName);
            if (!file.exists())
            {
                file.createNewFile();
            }
            
            FileHandler fileHandler = new FileHandler (fileName);
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.INFO);
            
            ActionListenerSave actLstSave = new ActionListenerSave ();
            actLstSave.setDv(this.drawingView);
            actLstSave.setFile(file);        
            this.drawingView.addActionListenerSave(actLstSave);
            
            ActionListenerOpen actLstOpen = new ActionListenerOpen ();
            actLstOpen.setDrawingView(this.drawingView);
            actLstOpen.setDrawingModel(this.drawingModel);
            this.drawingView.addActionListenerOpen(actLstOpen);
            
        }
        catch(Exception ex)
        {
            
        }
    }
}
