/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.tests;

import geneticalgorithm.configuration.Configuration;
import geneticalgorithm.fitness.FitnessCalculator;
import geneticalgorithm.objects.PittsburghIndividual;
import geneticalgorithm.objects.Population;
import geneticalgorithm.selection.SelectionMethod;
import java.util.Random;
import org.junit.Test;

/**
 *
 * @author thomasborel
 */
public class SelectionTest
{
    SelectionMethod selection = new SelectionMethod();
    FitnessCalculator calculator = new FitnessCalculator(new Configuration());
    Random random = new Random();
    
    @Test
    public void testSelection()
    {
        double totalPercentage = 0;

        for(int i = 0; i <100; i++)
        {
            Population population = new Population(50, 50);
            int percentage = 0;
            
            for(int individualIndex = 0; individualIndex < population.size(); individualIndex ++)
            {
                PittsburghIndividual individual = (PittsburghIndividual) population.get(individualIndex);
                individual.setFitness(random.nextInt(25));
            }
            
            int index = 1;

            while(index <= 100)
            {
                PittsburghIndividual selected = (PittsburghIndividual)selection.rouletteWheelSelection(population);
                if(selected.getFitness() == population.getFitest().getFitness())
                    percentage++;

                index ++;  
            }

            System.out.println("Selected Fittest : " + percentage);
            
            totalPercentage += percentage;

        }
        
        System.out.println(totalPercentage / 100);
    }
}
