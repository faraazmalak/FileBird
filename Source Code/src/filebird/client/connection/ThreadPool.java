package filebird.client.connection;

import java.net.*;
import java.util.*;

/**
 *@Purpose A class, which represents a pool of socket connections. Threads pick up socket connections from
 * this pool
 * @author Faraaz Malak
 */
public class ThreadPool extends HashMap {
  
    public void addConnection(Process connection,String connectionName)
    {
       
        this.put(connection, connectionName);
    }
    public void removeConnection(String connectionName)
    {
        if(this.containsKey(connectionName))
        {
            this.remove(connectionName);
        }
       
    }
    public Connection getConnection(String connectionName)
    {
        if(this.containsKey(connectionName))
        {
            return (Connection)this.get(connectionName);
        }
        return null;
    }
}
