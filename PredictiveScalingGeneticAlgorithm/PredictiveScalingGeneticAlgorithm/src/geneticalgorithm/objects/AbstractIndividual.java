/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.objects;

import java.util.Random;

/**
 *  ABSTRACTINDIVIDUAL - Override class based on representation requirements 
 * 
 * @author thomasborel
 */
public abstract class AbstractIndividual 
{
    protected char[] genes;
    protected double fitness;
    protected Random randomGenerator = new Random();
    protected int constraintViolations;

    public AbstractIndividual() 
    {
        
    }
    
    public AbstractIndividual(char genes[])
    {
        this.genes = genes;
    }

    public double getFitness()
    {
        return fitness;
    }

    public void setFitness(double fitness) 
    {
        this.fitness = fitness;
    }

    public char[] getGenes() 
    {
        return genes;
    }

    public void setGenes(char[] genes)
    {
        this.genes = genes;
    }
    
    public void genesString(String genes)
    {
        for(int index = 0; index < genes.length(); index ++)
        {
            this.genes[index] = genes.charAt(index);
        }
    }

    public int getConstraintViolations() 
    {
        return constraintViolations;
    }

    public void setConstraintViolations(int constraintViolations) 
    {
        this.constraintViolations = constraintViolations;
    }
    
    
    protected abstract char[] generateRandomGenes(int phenotypeLength);
        
    public abstract void mutation(double mutationRate);
    
    public abstract void actionMutation(double actionMutationRate);
}

