/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filebird.client.ui;

import filebird.io.IOPlatform;
import filebird.logging.Log;


/**
 *
 * @author Faraaz Malak
 */
public class PrintConsole  implements IOPlatform {

    @Override
    public void logText(Log log) {
       System.out.println(log.getMessage());
    }
    
}
