/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filebird.client;

import filebird.client.connection.Connection;
import filebird.client.connection.ThreadPool;
import filebird.client.ui.Console;
import filebird.io.File;
import filebird.logging.Log;
import filebird.logging.LogEngine;
import filebird.logging.LoggerType;
import filebird.client.connection.Process;
import filebird.client.ui.PrintConsole;
import filebird.client.ui.UI;
import filebird.logging.Logger;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.logging.Level;

/**
 *
 * @author Faraaz Malak
 */
public class Client {

    private Process logThread;
    private Process uiThread;
    private ThreadPool connectionPool = new ThreadPool();

    public static void main(String args[]) {
       
        new Client();
       
    }

    public Client() {
         this.initializeUI();
        this.initializeLogging();
        this.connect("127.0.0.1", 21,"faraaz", "");
    }

    public void initializeUI() {
        this.uiThread = new Process(new UI(this), "UI");
    }

    private void initializeLogging() {
        LogEngine logEngine = new LogEngine();
        this.logThread = new Process(logEngine, "LogEngine");


        try {
            logEngine.addLogger( new Logger(new filebird.io.File("filebirdlog.xml"), Level.SEVERE,LoggerType.SYSTEM));

            Logger ftpLogger=new Logger(Level.INFO,LoggerType.FTP);
            ftpLogger.addHandler(((UI) uiThread.getTarget()).getConsole());
            ftpLogger.addHandler(new PrintConsole());
            
            logEngine.addLogger(ftpLogger);
            
            //Enable::            logEngine.addLogger(LoggerType.FTP, new FTPLogger(((UI) uiThread.getTarget()).getConsole(), Level.INFO));
   //          logEngine.addLogger(LoggerType.FTP, new FTPLogger(System., Level.INFO));


        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Enable:: ((UI) this.uiThread.getTarget()).setLogEngine(logEngine);
    }

    public void connect(String server,int port,String username,String password) {
       Process process;
      
            process = new Process(new Connection.Builder().ip(server).password(password).username(username).logger(this.getLogger()).ui(this.getUI()).build(), server); 
            process.start();
            // connectionPool.addConnection(process,server);
       
      
       
       // System.out.println("Main Thread");
        
        //((Connection)process.getTarget()).test();
        
       // this.disconnect(server);
       // System.out.println("Dummy Connection");

    }
    public LogEngine getLogger()
    {
       return (LogEngine)logThread.getTarget();
       
    }
    
    public UI getUI()
    {
        return (UI)uiThread.getTarget();
    }
}
