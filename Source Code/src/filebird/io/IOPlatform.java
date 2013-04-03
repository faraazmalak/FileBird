/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filebird.io;

import filebird.logging.Log;
import java.util.logging.Level;

/**
 *
 * @author Faraaz Malak
 */
public interface IOPlatform {
    //private Level level;
    public void logText(Log log);
    /*public void clear();
    public void queue();*/
}
