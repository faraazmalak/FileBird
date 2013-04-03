/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filebird.client.ui;

import filebird.io.IOPlatform;
import filebird.logging.Log;
import filebird.logging.LoggerType;
import java.awt.print.PrinterException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/**
 *
 * @author Faraaz Malak
 */
public class Console extends JTextPane implements IOPlatform {

    private StyledDocument doc;
    public Console()
    {
       
//this.logText(new Log(Level.INFO,"TEST LOG",LoggerType.SYSTEM));
        //this.setEditable(false);
     //   test("AGAGA");
       doc=this.getStyledDocument();
     
    }
  
    
    //@Override
    public void logText(Log log) {
        try {       

           doc.insertString(doc.getLength(), log.getMessage()+"\n", null);

        } catch (BadLocationException ex) {
            Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
        }
          
       
    }
    
   

 
}
