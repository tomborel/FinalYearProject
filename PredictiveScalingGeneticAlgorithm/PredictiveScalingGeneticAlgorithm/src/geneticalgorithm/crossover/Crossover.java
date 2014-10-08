/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.crossover;

import geneticalgorithm.objects.AbstractIndividual;
import geneticalgorithm.objects.BaldwinianIndividual;
import geneticalgorithm.objects.Population;
import java.util.Random;

/**
 *  CROSSOVER - Reproduction of two individuals of a Population object
 * 
 * @author thomasborel
 */
public class Crossover 
{
    private Random generator = new Random();
    private int crossoverRate;

    public Crossover(int crossoverRate) 
    {
        this.crossoverRate = crossoverRate;
    }
    
    public Population singlePoint(Population individuals)
    {
        for(int index = 0; index < individuals.size(); index = index + 2)
        {
            //Loop over Pairs of individuals 
            if(generator.nextInt(100) >= crossoverRate)
                continue;

            AbstractIndividual individualOne = individuals.get(index);
            AbstractIndividual individualTwo = individuals.get(index + 1);
            
            int pointOfCrossover = generator.nextInt(individualOne.getGenes().length);
            //Perfrom the crossover on every gene past this point
            for(int i = pointOfCrossover; i < individualOne.getGenes().length; i++)
            {              
                    char tempGene = individualOne.getGenes()[i];
                    individualOne.getGenes()[i] = individualTwo.getGenes()[i];
                    individualTwo.getGenes()[i] = tempGene;
             }
        }
        
        return individuals;
    }
    
    public void uniform(Population individuals)
    {
        for(int index = 0; index < individuals.size(); index = index + 2)
        {   //Loop over population in pairs
            AbstractIndividual individualOne = individuals.get(index);
            AbstractIndividual individualTwo = individuals.get(index + 1);
            
            //Loop over each gene index of the pair
            for(int i = 0; i < individualOne.getGenes().length; i++)
            {              
                int random = generator.nextInt(100);
                //Each gene has a chance of crossing over
                if(random <= crossoverRate)
                {   
                    //Perform the crossover 
                    char tempGene = individualOne.getGenes()[i];
                    individualOne.getGenes()[i] = individualTwo.getGenes()[i];
                    individualTwo.getGenes()[i] = tempGene;
                }    
             }
        }
    }
    
    //Method called in BaldwinianAlgorithm only
    public void baldwinianUniform(Population individuals)
    {
        for(int index = 0; index < individuals.size(); index = index + 2)
        {
            BaldwinianIndividual individualOne = (BaldwinianIndividual)individuals.get(index);
            BaldwinianIndividual individualTwo = (BaldwinianIndividual)individuals.get(index);
            
            
            for(int i = 0; i < individualOne.getGenes().length; i++)
            {              
                int random = generator.nextInt(100);

                if(random <= crossoverRate)
                {
                    char tempGene = individualOne.getGenes()[i];
                    individualOne.getGenes()[i] = individualTwo.getGenes()[i];
                    individualTwo.getGenes()[i] = tempGene;
                }    
             }
            
            //Both individuals keep the highest plasticity of the pair
            if(individualOne.getPlasticity() > individualTwo.getPlasticity())
                individualTwo.setPlasticity(individualOne.getPlasticity());
            else
                individualOne.setPlasticity(individualTwo.getPlasticity());
            
        }
        
    }
    
//    
//    private Population crossover(int beginningIndex, int endIndex)
//    {
//        Population populationToReturn = new Population()
//    }
}

