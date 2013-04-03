/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filebird.client.connection;

import filebird.client.ThreadPool;
import filebird.client.ui.UI;
import filebird.logging.Log;
import filebird.logging.LogEngine;
import filebird.logging.LoggerType;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import filebird.client.*;
import filebird.client.ui.FileBrowser;
import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 *
 * @author Faraaz Malak
 */
import javax.swing.ListModel;

public class Connection extends FTPClient implements Runnable {

    private InetAddress ip;
    
    private int port;
    private int refreshTimeout;
    
    private String username;
    private String password;
    
    private LogEngine logger;
    private UI ui;
    

    /*public Connection(InetAddress ip, String username, String password, Client client, Object... params) {
     this.ip = ip;

     if (params[0] instanceof Integer) {
     this.port = (int) params[0];

     }


     this.username = username;
     this.password = password;

     this.logger = client.getLogger();
     this.ui = client.getUI();
     this.setControlKeepAliveTimeout(this.refreshTimeout);

     }

     public Connection(String host, int port, String username, String password, Client client) throws UnknownHostException {
     this.ip = InetAddress.getByName(host);
     this.port = port;

     this.username = username;
     this.password = password;

     this.logger = client.getLogger();
     this.ui = client.getUI();

     this.setControlKeepAliveTimeout(this.refreshTimeout);
     }*/
    public Connection(Builder builder) {
        this.ip = builder.ip;
        
        this.port=builder.port;
        this.refreshTimeout=builder.refreshTimeout;
        
        this.username=builder.username;
        this.password=builder.password;
        
        this.logger=builder.logger;
        this.ui=builder.ui;
        
        setControlKeepAliveTimeout(this.refreshTimeout);
    }

    public void run() {
        try {
//Socket soc=new Socket("127.0.0.1",21);
//System.out.println(soc.isBound());
//soc.connect(null);
            if (this.connect(ip, port, username, password)) {
               // System.out.println(this.printWorkingDirectory());
              // this.execCmd();
                Socket soc=this._openDataConnection_("PWD",null);
                if(soc==null)
                {
                    System.err.println("ERROR");
                }
                //System.out.println("Data Connection Status: "+soc.isConnected());
           // String[] fileList = this.listNames();
           // System.out.println("Working Dir: "+this.printWorkingDirectory());
              //  FileBrowser fb = ui.getRemoteBrowser();
            //    fb.update(fileList);
                // this.logEngine.log(new Log(Level.INFO,this.getServerFeatures(),LoggerType.FTP));
                
                
               /* for (int i = 0; i < fileList.length; i++) {
                    System.out.println(fileList[i].getName());
                }*/
            }



        } catch (Exception e) {
            System.err.println("Exception occored while attemting to run thread: " + e.getMessage());
            e.printStackTrace();

        }
    }
    
    private void execCmd()
    {
       
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String cmd;
            String params;
            do{
                
                System.out.println("Cmd: ");
                cmd=br.readLine();
                
                System.out.println("Params: ");
                params=br.readLine();
                
                if(this.doCommand(cmd,params))
                {
                    System.out.println("Commad Successful - "+this.getReplyString());
                }
                else
                {
                    System.out.println("Command Failed - "+this.getReplyString());
                }
                
            }while(this.isConnected());
            
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    public void setRefreshTimeout(int timeout) {
        this.refreshTimeout = timeout;
        this.setControlKeepAliveTimeout(this.refreshTimeout);
    }

    private boolean connect(InetAddress ip, int port, String username, String password) {
        try {

           // logger.log(new Log(Level.INFO, "Connecting to " + ip.getHostName() + "...", LoggerType.FTP));

            this.connect(ip, port);

            logger.log(new Log(Level.INFO, "Successfully Connected", LoggerType.FTP));

            if (FTPReply.isPositiveCompletion(this.getReplyCode())) {

                logger.log(new Log(Level.INFO, "Attempting to login... ", LoggerType.FTP));

                if (this.login(username, password)) {
                    logger.log(new Log(Level.INFO, "Successfully Logged in!!", LoggerType.FTP));
                } else {
                    logger.log(new Log(Level.INFO, "Sorry, login failed. Server said: \"" + this.getReplyString() + "\"", LoggerType.FTP));
                }


                return true;
            }


        } catch (SocketException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public static class Builder {

        private InetAddress ip;
        
        private int port = DEFAULT_PORT;
        private int refreshTimeout = 300;
        
        private String username;
        private String password;
        
        private LogEngine logger;
        private UI ui;

        public Builder ip(String ip) {
            try {
                this.ip = InetAddress.getByName(ip);
            } catch (UnknownHostException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
            return this;
        }

        public Builder ip(InetAddress ip) {
            this.ip = ip;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder refreshTimeout(int timeout) {
            this.refreshTimeout = timeout;
            return this;
        }

        public Builder logger(LogEngine logger) {
            this.logger = logger;
            return this;
        }

        public Builder ui(UI ui) {
            this.ui = ui;
            return this;
        }

        public Connection build() {
            return new Connection(this);
        }
    }
}
