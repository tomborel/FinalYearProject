/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.tests;

import geneticalgorithm.database.ConnectionManager;
import org.junit.Test;

/**
 *
 * @author Tom
 */
public class ConnectionManagerTest 
{
    @Test
    public void testConnection()
    {
        ConnectionManager conection = new ConnectionManager();
        
        conection.establishConnection();
    }
}
