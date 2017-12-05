/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import mvc.view.DrawingView;

/**
 *
 * @author loshmi
 */
public class ActionListenerSave implements ActionListener
{
    private DrawingView dv;
    private File file;

    public void setDv(DrawingView dv) {
        this.dv = dv;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String newFileName;
        String newDirName;
        JFileChooser fileChooser = new JFileChooser();
        int rVal = fileChooser.showSaveDialog(dv);
        if (rVal == JFileChooser.APPROVE_OPTION)
        {
            newFileName = fileChooser.getSelectedFile().getName();
            newDirName = fileChooser.getCurrentDirectory().toString();
            
            String newFileNameFull = newDirName + "/" + newFileName;
            
            File newFile = new File(newFileNameFull);
            
            copyFile (this.file, newFile);
            closeTag(newFile);
        }
    }
    
    private static void copyFile (File source, File dest)
    {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        try
        {
            try {
                sourceChannel = new FileInputStream(source).getChannel();
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
