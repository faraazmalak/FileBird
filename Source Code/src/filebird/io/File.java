/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filebird.io;

import filebird.logging.Log;
import java.io.IOException;
import java.util.logging.FileHandler;

/**
 *
 * @author Faraaz Malak
 */
public class File extends FileHandler implements IOPlatform {
    public final static String ROOTDIR="logs";
    public File(String file) throws IOException
    {
        super(file);
       // this.
    }

    @Override
    public void logText(Log log) {
      this.publish(log);
    }

    

  
    
}
