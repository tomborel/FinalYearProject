/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.tests;

import geneticalgorithm.LamarckianAlgorithm;
import geneticalgorithm.objects.PittsburghIndividual;
import org.junit.Test;

/**
 *
 * @author thomasborel
 */
public class LocalSearchTest 
{
    
    @Test
    public void testLocalSearch()
    {
        LamarckianAlgorithm algorithm = new LamarckianAlgorithm(100, 50, 1, 20, 100, 5, "Pittsburgh");
        //PittsburghIndividual individual = new PittsburghIndividual(1);
        
        PittsburghIndividual individual = new PittsburghIndividual("1##110#10".toCharArray(), 1);
        
        algorithm.localSearch(individual);
        
    }
    
    
}