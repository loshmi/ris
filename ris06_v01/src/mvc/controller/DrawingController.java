/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

import java.util.logging.Logger;
import command.CmdAddPoint;
import command.Command;
import helper.Util;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import javax.swing.filechooser.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import mvc.model.DrawingModel;
import mvc.model.Point;
import mvc.view.DrawingView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author loshmi
 */
public class DrawingController
{
    private DrawingModel drawingModel;
    private final DrawingView drawingView;
    public final static Logger LOGGER = Logger.getLogger("logger");
    private ArrayList<String> listEntries;
    private File file;
    private LinkedList<Command> commands;
    private int indexCmd;

    public DrawingController(DrawingModel drawingModel, DrawingView drawingView)
    {
        this.listEntries = new ArrayList<> ();
        this.drawingModel = drawingModel;
        this.drawingView = drawingView;
        this.drawingView.initView();
        this.commands= new LinkedList<Command> ();
     
        this.drawingView.addDrawingListener (new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        Point point = new Point(e.getX(), e.getY(), Point.DEFAULT_COLOR);
                        CmdAddPoint cmdAddPoint = new CmdAddPoint(drawingModel, point);
                        doCmd(cmdAddPoint);
                        drawingView.setEnbBtnUndo(true);
                    }
                });
        this.drawingView.getDrawingPanel().setShapes(this.drawingModel.getShapes());
        
        try
        {
            File dir = new File("log");
            boolean kreiran = dir.mkdir();
            String fileName = dir.getAbsolutePath() + "/log" + System.currentTimeMillis();
            file = new File (fileName);
            if (!file.exists())
            {
                file.createNewFile();
            }
            
            FileHandler fileHandler = new FileHandler (fileName);
            LOGGER.addHandler(fileHandler);
            LOGGER.setLevel(Level.INFO);
            
            ActionListenerOpen actLstOpen = new ActionListenerOpen ();
            this.drawingView.addActionListenerOpen(actLstOpen);
            
            ActionListenerSave actLstSave = new ActionListenerSave ();
            this.drawingView.addActionListenerSave(actLstSave);
            
            ActionListenerUndo actLstUndo = new ActionListenerUndo ();
            this.drawingView.addActionListenerUndo(actLstUndo);
            
            ActionListenerRedo actLstRedo = new ActionListenerRedo ();
            this.drawingView.addActionListenerRedo(actLstRedo);
            
            ActionListenerOpenModel actLstOpenModel = new ActionListenerOpenModel ();
            this.drawingView.addActionListenerOpenModel(actLstOpenModel);
            
            ActionListenerSaveModel actLstSaveModel = new ActionListenerSaveModel ();
            this.drawingView.addActionListenerSaveModel(actLstSaveModel);
            
            ActionListenerDeleteShape actLstDelteShape = new ActionListenerDeleteShape();
            this.drawingView.addActionListenerDeleteShape(actLstDelteShape);
        }
        catch(Exception ex)
        {
            
        }
    }
    
    private void doCmd (Command cmd)
    {
        cmd.execute();
        if (commands.size() > indexCmd)
        {
            for (int i = commands.size()-1; i >= indexCmd; i--)
            {
                commands.remove(i);
            }
        }
        LOGGER.log(Level.INFO, cmd.toString());
        commands.add(cmd);
        updateJList();
        indexCmd++;
        
    }

    private void updateJList ()
    {
        ArrayList<String> tempList = Util.convertPointsToStrings(drawingModel.getShapes());
        Collections.reverse(tempList);
        drawingView.setListData(tempList.toArray());
    }
    
    private class ActionListenerOpen implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser fileChooser = new JFileChooser();
            int rVal = fileChooser.showOpenDialog(drawingView);
            if (rVal == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();
                try 
                {
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
                    dbFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();                
                    Document doc = dBuilder.parse(file);


                    doc.getDocumentElement().normalize();
                    NodeList nList = doc.getElementsByTagName("record");

                    for (int temp = 0; temp < nList.getLength(); temp++)
                    {

                        Node nNode = nList.item(temp);

                        if (nNode.getNodeType() == Node.ELEMENT_NODE)
                        {

                            Element eElement = (Element) nNode;

                            String message = eElement.getElementsByTagName("message").item(0).getTextContent();

                            Point tempPoint = Util.parsePoint(message);

                            CmdAddPoint cmd = new CmdAddPoint(drawingModel, tempPoint);
                            cmd.execute();
                            DrawingController.LOGGER.log(Level.INFO, cmd.toString());
                        }                    
                    }

                    ArrayList<String> tempList = Util.convertPointsToStrings(drawingModel.getShapes());
                    Collections.reverse(tempList);

                    drawingView.setListData(tempList.toArray());
                }
                catch (Exception ex)
                {
                  ex.printStackTrace();
                }
            }
            else
            {
                System.out.println("File access cancelled by user.");
            }
        }
    }
    
    private class ActionListenerSave implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String newFileName;
            String newDirName;
            JFileChooser fileChooser = new JFileChooser();
            int rVal = fileChooser.showSaveDialog(drawingView);
            if (rVal == JFileChooser.APPROVE_OPTION)
            {
                newFileName = fileChooser.getSelectedFile().getName();
                newDirName = fileChooser.getCurrentDirectory().toString();

                String newFileNameFull = newDirName + "/" + newFileName;

                File newFile = new File(newFileNameFull);

                copyFile (newFile);
                closeTag(newFile);
            }
        }

        private void copyFile (File dest)
        {
            FileChannel sourceChannel = null;
            FileChannel destChannel = null;
            try
            {
                try {
                    sourceChannel = new FileInputStream(file).getChannel();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ActionListenerSave.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    destChannel = new FileOutputStream(dest).getChannel();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ActionListenerSave.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
                } catch (IOException ex) {
                    Logger.getLogger(ActionListenerSave.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            finally
            {
                try {
                    sourceChannel.close();
                    destChannel.close();
                } catch (IOException ex) {
                    Logger.getLogger(ActionListenerSave.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        private void closeTag (File file)
        {
            try {
                Writer output;
                output = new BufferedWriter(new FileWriter(file.getAbsolutePath(), true));
                output.append("</log>");
                output.close();
            } catch (IOException ex) {
                Logger.getLogger(ActionListenerSave.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private class ActionListenerUndo implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e)
        {
            if (indexCmd > 0)
            {
                indexCmd--;
                Command cmd = commands.get(indexCmd);
                cmd.unexecute();
                LOGGER.log(Level.INFO, "UNDO: " + cmd.toString());
                updateJList();
                drawingView.repaint();
                drawingView.setEnbBtnRedo(true);
                if (indexCmd == 0)
                {
                    drawingView.setEnbBtnUndo(false);
                }
            }
        }
    }
    
    private class ActionListenerRedo implements ActionListener
    {
        @Override
        public void actionPerformed (ActionEvent e)
        {
            if (commands.size() != indexCmd)
            {
                Command cmd = commands.get(indexCmd);
                cmd.execute();
                LOGGER.log(Level.INFO, "REDO: " + cmd.toString());
                updateJList();
                indexCmd++;
                drawingView.repaint();
                if (commands.size() == indexCmd) drawingView.setEnbBtnRedo(false);
                drawingView.setEnbBtnUndo(true);
            }
        }
    }
    
    private class ActionListenerOpenModel implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String newFileName;
            String newDirName;
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileFilter()
                                                {
                                                    public String getDescription()
                                                    {
                                                        return "Seriazable files (*.ser)";
                                                    }
 
                                                    public boolean accept(File f)
                                                    {
                                                        if (f.isDirectory())
                                                        {
                                                            return true;
                                                        }
                                                        else
                                                        {
                                                            return f.getName().toLowerCase().endsWith(".ser");
                                                        }
                                                    }
                                                });
            fileChooser.setAcceptAllFileFilterUsed(true);
            int rVal = fileChooser.showOpenDialog(drawingView);
            if (rVal == JFileChooser.APPROVE_OPTION)
            {
                newFileName = fileChooser.getSelectedFile().getName();
                newDirName = fileChooser.getCurrentDirectory().toString();

                String newFileNameFull = newDirName + "/" + newFileName;

                File newFile = new File(newFileNameFull);

                try
                {
                    FileInputStream fileInputStream = new FileInputStream(newFile);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    DrawingModel model = (DrawingModel) objectInputStream.readObject();
                    drawingModel = model;
                    drawingView.getDrawingPanel().setShapes(drawingModel.getShapes());
                    ArrayList<String> tempList = Util.convertPointsToStrings(drawingModel.getShapes());
                    Collections.reverse(tempList);
                    drawingView.setListData(tempList.toArray());
                    drawingView.repaint();
                    
                    objectInputStream.close();
                    fileInputStream.close();
                }
                catch (FileNotFoundException fnfException)
                {
                    fnfException.printStackTrace();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
                catch (ClassNotFoundException cnfException)
                {
                    cnfException.printStackTrace();
                }
            }
        }
    }
    
    private class ActionListenerSaveModel implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileFilter()
                                                {
                                                    public String getDescription()
                                                    {
                                                        return "Seriazable files (*.ser)";
                                                    }
 
                                                    public boolean accept(File f)
                                                    {
                                                        if (f.isDirectory())
                                                        {
                                                            return true;
                                                        }
                                                        else
                                                        {
                                                            return f.getName().toLowerCase().endsWith(".ser");
                                                        }
                                                    }
                                                });
            fileChooser.setAcceptAllFileFilterUsed(true);
            int rVal = fileChooser.showSaveDialog(drawingView);
            if (rVal == JFileChooser.APPROVE_OPTION)
            {
                File file = fileChooser.getSelectedFile();

                try
                {
                    FileOutputStream fileOut = new FileOutputStream(file);
                    ObjectOutputStream outObject = new ObjectOutputStream(fileOut);
                    outObject.writeObject(drawingModel);
                    outObject.close();
                    fileOut.close();
                }
                catch (FileNotFoundException fnfException)
                {
                    fnfException.printStackTrace();
                }
                catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }
            }
        }
    }
    
    private class ActionListenerDeleteShape implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            ArrayList<String> selectedItems = new ArrayList<> (drawingView.getList().getSelectedValuesList());

            for (String item : selectedItems)
            {
                Point tempPoint = Util.parsePoint(item);
                drawingModel.removeShape(tempPoint);            
            }

            ArrayList<String> tempList = Util.convertPointsToStrings(drawingModel.getShapes());
            Collections.reverse(tempList);
            drawingView.setListData(tempList.toArray());
            drawingView.repaint();
        }
    }
}
