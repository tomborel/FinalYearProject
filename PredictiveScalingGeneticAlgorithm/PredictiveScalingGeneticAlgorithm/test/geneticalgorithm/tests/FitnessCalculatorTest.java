/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.tests;

import geneticalgorithm.configuration.Configuration;
import geneticalgorithm.fitness.FitnessCalculator;
import geneticalgorithm.objects.AbstractIndividual;
import geneticalgorithm.objects.PittsburghIndividual;
import java.util.ArrayList;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thomasborel
 */
public class FitnessCalculatorTest 
{
    Configuration configuration;
    FitnessCalculator fitness;
    AbstractIndividual individual;
    Random random = new Random();
    
    public FitnessCalculatorTest() 
    {
        configuration = new Configuration();
        fitness = new FitnessCalculator(configuration);
         
        individual = new PittsburghIndividual(1);
    }
    
    @Test
    public void testCalculateFitness()
    {
        ArrayList<AbstractIndividual> individuals = new ArrayList<AbstractIndividual>();
        
        for(int index = 0; index < 50; index++)
        {
            AbstractIndividual individualToCalculate = new PittsburghIndividual(1);
            fitness.calculateFitness(individualToCalculate);
            individuals.add(individualToCalculate);
        }
        
        assertTrue(individuals.size() > 0 );  
    }
}