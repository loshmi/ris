/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFileChooser;
import mvc.view.DrawingView;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import mvc.model.DrawingModel;
import mvc.model.Point;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 *
 * @author loshmi
 */
public class ActionListenerOpen implements ActionListener
{
    private DrawingView drawingView;
    private DrawingModel drawingModel;

    public void setDrawingView(DrawingView drawingView) {
        this.drawingView = drawingView;
    }

    public void setDrawingModel(DrawingModel drawingModel) {
        this.drawingModel = drawingModel;
    }
    
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

			drawingModel.add(tempPoint);

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
