/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filebird.logging;

import filebird.io.IOPlatform;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Faraaz Malak
 */
public class LogEngine extends HashMap implements Runnable {
     @Override
    public void run() {
     this.run();
    }
    synchronized public void log(Log log)
    {
       
        if(this.containsKey(log.getCategory()))
        { 
            Logger logger=(Logger)this.get(log.getCategory());
          
           logger.publishLog(log);
           
            //System.out.println(log.getMessage());
        }
    }
    synchronized public  void addLogger(Logger logger)
    {
        //logger.setName(name);
        this.put(logger.getType(), logger);
    }
    
   synchronized  public Logger getLogger(LoggerType type)
    {
        if(this.containsKey(type))
        {
            return (Logger)this.get(type);
        }
       return null;
    }
    
    synchronized public void addHandler(LoggerType type,IOPlatform handler)
    {
        if(this.containsKey(type))
        {
            Logger logger= (Logger)this.get(type);
            logger.addHandler(handler);
        }
    }

   
}
