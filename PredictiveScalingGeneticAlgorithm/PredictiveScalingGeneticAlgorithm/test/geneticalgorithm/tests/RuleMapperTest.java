/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.tests;

import geneticalgorithm.mappers.RuleMapper;
import org.junit.Test;

/**
 *
 * @author Tom
 */
public class RuleMapperTest 
{
    @Test
    public void testSelect()
    {
        RuleMapper mapper = new RuleMapper();
        mapper.selectQuery();
    }
    
    @Test
    public void testSelectCount()
    {
        RuleMapper mapper = new RuleMapper();
        mapper.selectCount();
    }
    
    @Test
    public void testInsert()
    {
        String rule  = "1#1##10#0###101#01";
        RuleMapper mapper = new RuleMapper();
        mapper.insertQuery(rule);
    }
    
    @Test
    public void testDelete()
    {
        RuleMapper mapper = new RuleMapper();
        mapper.deleteQuery();
    }
        
}
