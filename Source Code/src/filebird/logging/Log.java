/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filebird.logging;

import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 *
 * @author Faraaz Malak
 */
public class Log extends LogRecord {

   

    private LoggerType category;
    
    public Log(Level level, String msg,LoggerType category) {
        super(level, msg);
        this.category=category;
        
        
    }
    
    public LoggerType getCategory()
    {
       return this.category; 
    }
}
