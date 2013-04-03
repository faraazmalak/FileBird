/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filebird.client.ui;

/**
 *
 * @author Faraaz Malak
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileFilter;
import org.apache.commons.net.ftp.FTPFile;

/**
 * This code uses a JList in two forms (layout orientation vertical & horizontal
 * wrap) to display a File[]. The renderer displays the file icon obtained from
 * FileSystemView.
 */
public class FileBrowser extends JList<File> {

    public FileBrowser(File[] files) {
        //super(files);
        
      this.initialize();
       
      
    }
    
    public FileBrowser() {
        
         super( (new File(System.getProperty("user.dir"))).listFiles(new TextFileFilter()));
         
           this.initialize();
        
           
    }
    
    public void update(final String[] list)
    {
       
      this.setModel((new javax.swing.AbstractListModel() {
            String[] strings = list;
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        }));
    }
    private void initialize()
    {  
        this.setCellRenderer(new FileRenderer(false));
        this.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP); 
        this.setVisibleRowCount(9);
        
    }



    /*  public static void main(String[] args) {
     SwingUtilities.invokeLater(new Runnable() {
     public void run() {
     File f = new File(System.getProperty("user.home"));
     FileBrowser fl = new FileBrowser();
     Component c1 = fl.getGui(f.listFiles(new TextFileFilter()),true);

     //f = new File(System.getProperty("user.home"));
     Component c2 = fl.getGui(f.listFiles(new TextFileFilter()),false);

     JFrame frame = new JFrame("File List");
     JPanel gui = new JPanel(new BorderLayout());
     gui.add(c1,BorderLayout.WEST);
     gui.add(c2,BorderLayout.CENTER);
     c2.setPreferredSize(new Dimension(375,100));
     gui.setBorder(new EmptyBorder(3,3,3,3));

     frame.setContentPane(gui);
     frame.pack();
     frame.setLocationByPlatform(true);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setVisible(true);
     }
     });
     }*/
}

class TextFileFilter implements FileFilter {

    public boolean accept(File file) {
        // implement the logic to select files here..
        String name = file.getName().toLowerCase();
        //return name.endsWith(".java") || name.endsWith(".class");
        return name.length() < 20;
    }
}

