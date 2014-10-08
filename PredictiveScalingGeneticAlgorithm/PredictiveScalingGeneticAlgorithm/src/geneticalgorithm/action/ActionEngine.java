/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tom
 */
public class ActionEngine 
{
    public String apiCall(String action)
    {
        String call = "";
        
        try 
        {
            Properties properties = new Properties();
            properties.load(new FileInputStream(getClass().getResource("action.properties").getFile()));
            
            if(action.equals("10"))
                call = properties.getProperty("add_server");
            else if(action.equals("01"))
                call = properties.getProperty("delete_server");
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(ActionEngine.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ActionEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return call;
    }
}
