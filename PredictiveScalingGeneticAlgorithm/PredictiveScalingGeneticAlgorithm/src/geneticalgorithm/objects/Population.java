/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.objects;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author thomasborel
 */
public class Population extends ArrayList<AbstractIndividual>
{
    private int populationSize;
    private int numberOfRules;
    
    public Population() 
    {
    }
    
    
    //Constructor for Pittsburgh population
    public Population(int populationSize, int numberOfRules) 
    {
        this.populationSize = populationSize;
        this.numberOfRules = numberOfRules;
        
        for(int i = 0; i < populationSize; i++)
        {
            PittsburghIndividual individual = new PittsburghIndividual(numberOfRules);
            this.add(individual);
        }
    }
    
    //Constructor for baldwinian individual
    public Population(int populationSize, int numberOfRules, int maxPlasticity)
    {
        this.populationSize = populationSize;
        this.numberOfRules = numberOfRules;
        
        for(int i = 0; i < populationSize; i++)
        {
            BaldwinianIndividual individual = new BaldwinianIndividual(numberOfRules, 0);
            this.add(individual);
        }
    }

    public int getPopulationSize()
    {
        if(populationSize != 0)
            return populationSize;
        
        return this.size();
    }

    public int getNumberOfRules()
    {
        return numberOfRules;
    }
    
    public int getTotalFitness()
    {
        int fitnessToReturn = 0;
        
            for(AbstractIndividual individual : this)
            {
                fitnessToReturn += individual.getFitness();
            }
        
        return fitnessToReturn;
    }
    
    public AbstractIndividual getFitest()
    {
        AbstractIndividual individualToReturn = this.get(0);
        
        for(AbstractIndividual individual: this)
        {
            if(individual.getFitness() > individualToReturn.getFitness())
                individualToReturn = individual;
        }
        
        return individualToReturn;
    }
    
    public AbstractIndividual getLeastFit()
    {
        AbstractIndividual individualToReturn = this.get(0);
        
        for(AbstractIndividual individual: this)
        {
            if(individual.getFitness() <  individualToReturn.getFitness())
                individualToReturn = individual;
        }
        
        return individualToReturn;
    }
    
    public AbstractIndividual getMostConstraintViolations()
    {
        AbstractIndividual individualToReturn = this.get(0);
        
        for(AbstractIndividual individual: this)
        {
            if(individual.getConstraintViolations()> individualToReturn.getConstraintViolations())
                individualToReturn = individual;
        }
        
        return individualToReturn;
    }
    
    public Population getUniqueIndividuals()
    {
        Population uniqueIndividuals = new Population();
        
        for(AbstractIndividual individual: this)
        {
            if(!uniqueIndividuals.contains(individual))
                uniqueIndividuals.add(individual);
        }
        
        return uniqueIndividuals;
    }
    
}
