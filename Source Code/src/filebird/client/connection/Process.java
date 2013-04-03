/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filebird.client.connection;

/**
 *
 * @author Faraaz Malak
 */
public class Process extends Thread {
    
    private Runnable target=null;
    
    public Process(Runnable target,String name)
    {
        super(target,name);
        this.target=target;
    }
    public Runnable getTarget()
    {
        return this.target;
    }
       @Override
    public void run() {
         
           target.run();
       }
}
