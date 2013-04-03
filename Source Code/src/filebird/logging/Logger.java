/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filebird.logging;

import filebird.client.ui.Console;
import filebird.io.IOPlatform;
import filebird.logging.Log;
import filebird.logging.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;

/**
 *
 * @author Faraaz Malak
 */
 public class Logger {

    protected ArrayList<IOPlatform> handlers;
    private Level level;
    private String name;
    private LoggerType type;

    public Logger(ArrayList<IOPlatform> handlers, Level level/*, String name*/, LoggerType type) {
        this.handlers = handlers;
        this.level = level;
        //this.name = name;
        this.type = type;
    }

    public Logger(IOPlatform handler, Level level/*, String name*/, LoggerType type) {
        this.handlers = new ArrayList<IOPlatform>();
        this.handlers.add(handler);

        this.level = level;
        //this.name = name;
        this.type = type;
    }

     public Logger(Level level,LoggerType type) {
         this.type=type;
         this.level=level;
          this.handlers = new ArrayList<IOPlatform>();
          
         
     }
     
     public LoggerType getType(){
         return this.type;
     }
  


     public void addHandler(IOPlatform handler) {
     handlers.add(handler);
     
     }
    public int getTotalHandlers()
    {
        return this.handlers.size();
    }
    
    public void updateLevel(Level level) {
        this.level = level;
    }

    private boolean validateLog(Log log) {
        //Check Log Level
        if (log.getLevel().intValue() >= this.level.intValue()) {
            return true;
        }

        return false;
    }

    public void publishLog(Log log) {
        if (this.validateLog(log)) {
            
            Iterator<IOPlatform> iterator = handlers.iterator();
            while (iterator.hasNext()) {
                IOPlatform handler = iterator.next();
                handler.logText(log);
                
                //handler.test("gdgd");
            }
        }

    }
    //abstract public void publishLog();
}
