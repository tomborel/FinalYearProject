/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.fitness;

import geneticalgorithm.configuration.Configuration;
import geneticalgorithm.objects.AbstractIndividual;
import geneticalgorithm.objects.PittsburghIndividual;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author thomasborel
 */
public class FitnessCalculator 
{ 

    private Configuration configuration;
    
    public FitnessCalculator(Configuration configuration) 
    {
        this.configuration = configuration;
    }

    
    public void calculateFitness(AbstractIndividual individual)
    {
        if(individual instanceof PittsburghIndividual)
            calculatePittsburghFitness((PittsburghIndividual) individual);
    }
    

    
    private void calculatePittsburghFitness(PittsburghIndividual individual)
    {
        int fitness = 0;
        //Load the learning data, loop over each data entry
        for(String objective: configuration.getObjectives())
        {
            char[] objectiveBits = objective.toCharArray();
            //Loop over each rule of the individual 
            for(int geneIndex = 0; geneIndex < individual.getGenes().length; geneIndex = geneIndex + individual.getRuleLength())
            {
                int length = individual.getRuleLength();
                char[] individualGenes = new char[length];
                System.arraycopy(individual.getGenes(), geneIndex, individualGenes, 0, individual.getRuleLength());
                
                //Penalty for rules wich violate constraints 
                for(String constraint: configuration.getConstraints())
                {
                    if(constraint.toCharArray() == individualGenes)
                        fitness--;
                }
                
                // If the rule doesnt match the data entry loop to next rule
                if(!compareIndividuals(0, objectiveBits.length - 2, objectiveBits, individualGenes))
                    continue;
                
                
                 // If the action bits match the data entry has been successfuly classified
                if(individualGenes[length - 2] == objectiveBits[length - 2] && individualGenes[length - 1] == objectiveBits[length - 1])
                {  
                    fitness++; //Individual is rewarded
                    break;
                }
            }
        }
        
        individual.setFitness(fitness);
    }
    
    //Compare two ibndividuals between the indexes passed in 
    private boolean compareIndividuals(int startIndex, int endIndex, char[] individualOne, char[] individualTwo)
    {
        for(int index = startIndex; index <= endIndex; index ++)
        {
            if(individualOne[index] == '#')
                continue;
            
            if(individualTwo[index] == '#')
                continue;
            
            if(individualOne[index] != individualTwo[index])
                return false;
        }
        
        return true;
    }
    
}
