/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.tests;

import geneticalgorithm.BaldwinianAlgorithm;
import geneticalgorithm.DarwinianAlgorithm;
import geneticalgorithm.LamarckianAlgorithm;
import geneticalgorithm.objects.PittsburghIndividual;
import org.junit.Test;

/**
 *
 * @author thomasborel
 */
public class AlgorithmTest 
{
    
    public AlgorithmTest() 
    {
    }
    
    
    @Test
    public void TestPittsburgh()
    {
        //problem with Gray encoding/decoding
        
        DarwinianAlgorithm algorithm = new DarwinianAlgorithm(2000, 90, 0.005, 0.001 ,100,10,"Pittsburgh");
        
        algorithm.execute();
        
        ((PittsburghIndividual)algorithm.getPopulation().getFitest()).toString();
    }
    
    @Test
    public void TestLamarck()
    {
        LamarckianAlgorithm algorithm = new LamarckianAlgorithm(2000, 90, 0.01, 0 ,100,10, "Pittsburgh");
        
        algorithm.execute();
        
        System.out.println(algorithm.getPopulation().getFitest().toString());
    }
    
    @Test
    public void TestBaldwin()
    {
        //refactor pittsburgh individual to have plasticity on it and delte baldwinian individual
        
        BaldwinianAlgorithm algorithm = new BaldwinianAlgorithm(2000, 90, 0.0005, 0.0001 ,100, 10, "Baldwinian");
        
        algorithm.execute();
        
        System.out.println(algorithm.getPopulation().getFitest().toString());
    }
}