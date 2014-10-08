/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.tests;

import geneticalgorithm.database.RuleEngine;
import org.junit.Test;

/**
 *
 * @author Tom
 */
public class RuleEngineTest 
{
    @Test
    public void testSelectRule()
    {
        RuleEngine engine = new RuleEngine();
        
        engine.selectAction("0011100011000101");
    }
}
