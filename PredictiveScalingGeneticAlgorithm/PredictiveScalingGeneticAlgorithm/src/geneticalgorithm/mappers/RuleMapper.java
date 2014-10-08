/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.mappers;

import geneticalgorithm.database.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  RULEMAPPER - Mapper class for rule table
 * @author Tom
 */
public class RuleMapper
{
    ConnectionManager manager = new ConnectionManager();
    Connection connection = manager.establishConnection();

    public RuleMapper() 
    {
    }

    public HashMap<String, String> selectQuery()
    {
        HashMap rules = new HashMap();
        ResultSet results = null;
        //The query to run 
        String query = "SELECT input, action FROM rules ";
        
        try 
        {   //Prepare and execute the query
            PreparedStatement statement = connection.prepareStatement(query);
            results = statement.executeQuery();
            
            while(results.next())
            {   // Add each selected if:then rule to a HashMap
                rules.put(results.getString("input"), results.getString("action"));
            }
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RuleMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rules;
    }
    
    public int selectCount()
    {
        ResultSet results = null;
        String rules[] = null;
        int count = 0;
        
        //The Query to run
        String query = "SELECT COUNT(*) FROM rules";
        
        try 
        {
            //Prepare and execture the query
            PreparedStatement statement = connection.prepareStatement(query);
            results = statement.executeQuery();
            
            if(!results.next())
                return count; 
            
            //Cast the number of results to an integer
            count = results.getInt("COUNT(*)");
            
            
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RuleMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count;
    }
    
    public void insertQuery(String ruleToAdd)
    {   
        //Template of query to run
        String query = "INSERT INTO rules (input, action) VALUES ( ? , ? )";
        
        String input = ruleToAdd.substring(0, 16); // Rule if portion
        String action = ruleToAdd.substring(16).replaceAll(":", ""); //Rule then portion
        action = action.replaceAll(" ", ""); //Sanitize the string
            
        try 
        {  //Prepare the query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, input); //Replace first question mark with rule input
            statement.setString(2, action); //Replace second question mark with rule action
            statement.executeUpdate(); //execute the query
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RuleMapper.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void deleteQuery()
    {
        //Delete Query
        String query = "DELETE FROM rules";
               
        try 
        {   //Prepare and execute the query
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(RuleMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
