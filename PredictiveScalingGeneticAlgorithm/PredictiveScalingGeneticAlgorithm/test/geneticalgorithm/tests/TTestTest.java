/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.tests;

import geneticalgorithm.BaldwinianAlgorithm;
import geneticalgorithm.DarwinianAlgorithm;
import geneticalgorithm.LamarckianAlgorithm;
import geneticalgorithm.ttest.TTest;
import org.junit.Test;

/**
 *
 * @author Tom
 */
public class TTestTest 
{
    
    
    @Test
    public void ttestDarwin()
    {
       TTest ttest = new TTest(100, new DarwinianAlgorithm(1000, 90, 0.0005, 0.0001 ,100,10, "Pittsburgh")); 
       ttest.execute();
    }
    
    @Test
    public void ttestLamarck()
    {
       TTest ttest = new TTest(100, new LamarckianAlgorithm(2000, 90, 0.01, 0 ,100,10, "Pittsburgh"));
       ttest.execute();
    }
    
    @Test
    public void ttestBaldwin()
    {
       TTest ttest = new TTest(100, new BaldwinianAlgorithm(1000, 90, 0.0005, 0.0001 ,100, 10, "Baldwinian"));
       ttest.execute();
    }
}
