/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.database;

import geneticalgorithm.mappers.RuleMapper;
import geneticalgorithm.objects.AbstractIndividual;
import geneticalgorithm.objects.BaldwinianIndividual;
import java.util.HashMap;

/**
 *  RULEENGINE - Class for selecting rule actions based on input, and inserting rules
 *  from Individuals
 * 
 * @author Tom
 */
public class RuleEngine
{
    private RuleMapper mapper = new RuleMapper();
    
    public String selectAction(String input)
    {   
        // Run the select query from mapper object
        HashMap<String, String> rules =  mapper.selectQuery();
        
        String action = "";
        //Loop over each selected rule
        for(String key: rules.keySet())
        {
            System.out.println(key);
             
            if(!compareStrings(input, key))
                continue;    
            //if the rule condition matches the input rule is selected
            action = rules.get(key);
            break;
        }
        
        return action;
    }
    
    public void deleteRuleBase()
    {
        mapper.deleteQuery();
    }
    
    public void writeRules(AbstractIndividual individual)
    {
        if(mapper.selectCount() > 0)
            mapper.deleteQuery(); //Delete old rule base
        
        BaldwinianIndividual individualToAdd = (BaldwinianIndividual) individual;
        
        int index = 0;
        //Loop over each gene
        while(index < individual.getGenes().length)
        {
            int ruleIndex = 0;
            String ruleToAdd = "";
            //Select each rule in the individual
            while(ruleIndex < individualToAdd.getRuleLength())
            {
                ruleToAdd += individualToAdd.getGenes()[index];
                index++;
                ruleIndex++;
                
                if(ruleIndex == individualToAdd.getRuleLength() - 2)
                    ruleToAdd += " : ";
            }
            //Save the rule to the database
            mapper.insertQuery(ruleToAdd);
        }
    }
    
    
    private boolean compareStrings(String input, String ruleInput)
    {
        if(input.length() != ruleInput.length())
            return false;
        
        for(int index = 0; index < input.length(); index ++)
        {
            if(ruleInput.substring(index, index + 1).equals("#"))
                continue;
            
            if(!input.substring(index, index + 1).equals(ruleInput.substring(index, index + 1)))
                return false;
        }
        
        return true;
    }
}
