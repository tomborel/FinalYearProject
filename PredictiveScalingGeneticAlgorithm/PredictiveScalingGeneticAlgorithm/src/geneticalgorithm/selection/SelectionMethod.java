/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.selection;

import geneticalgorithm.objects.AbstractIndividual;
import geneticalgorithm.objects.Population;
import java.util.Random;

/**
 *
 * @author thomasborel
 */
public class SelectionMethod 
{
    private Random generator = new Random();

    public SelectionMethod() 
    {
    }
    
    
    public AbstractIndividual rouletteWheelSelection(Population population)
    {
        //get the current total fitness of the population
        int populationTotalFitness = population.getTotalFitness();

        int desiredFitness = generator.nextInt(populationTotalFitness);
        
        populationTotalFitness = 0;
        
        int index = 0;
        
        //begin adding the total fitness again until we get to the random number between
        //0 and the current fitness. 
        
        Population tempPopulation = new Population();
        
        while(populationTotalFitness <= desiredFitness && index < population.getPopulationSize())
        {
            populationTotalFitness += population.get(index).getFitness();
            tempPopulation.add(population.get(index));
            index++;
        }
        //The last individual added is the one selected
        
        return tempPopulation.get(tempPopulation.size() - 1);       
    }
    
        
    public AbstractIndividual tournamentSelection(Population population, int tournamentSize)
    {   //Create the tournament
        Population tempPopulation = new Population();
        
        for(int index = 0; index < tournamentSize; index++)
        {   //Randomly add individuals to the tournament until the tournament size has been reached
            int individualIndex = generator.nextInt(population.getPopulationSize());
            tempPopulation.add(population.get(individualIndex));
        }
        //The fittest individual of the tournament is selected
        return tempPopulation.getFitest();
    }
}
