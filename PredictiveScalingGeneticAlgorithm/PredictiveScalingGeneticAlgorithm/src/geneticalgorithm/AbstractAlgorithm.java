/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import geneticalgorithm.configuration.Configuration;
import geneticalgorithm.crossover.Crossover;
import geneticalgorithm.fitness.FitnessCalculator;
import geneticalgorithm.objects.AbstractIndividual;
import geneticalgorithm.objects.BaldwinianIndividual;
import geneticalgorithm.objects.PittsburghIndividual;
import geneticalgorithm.objects.Population;
import geneticalgorithm.selection.SelectionMethod;
import java.util.Random;

/**
 *
 * @author thomasborel
 */
public abstract class AbstractAlgorithm 
{
    protected int numberOfGenerations;
    protected int populationSize;
    protected double mutationRate;
    protected double actionMutationRate;
    protected int tournamentSize;
    protected Configuration configuration;
    protected Population population;
    protected Crossover crossover;
    protected FitnessCalculator fitness;
    protected AbstractIndividual individualToKeep;
    protected SelectionMethod selection;
    protected String individualType;  
    protected Random random = new Random();
    protected int numberOfRules = 6;
    Population currentGeneration = new Population();

    public AbstractAlgorithm (int numberOfGenerations, int crossoverRate, double mutationRate, 
            double actionMutationRate, int populationSize, int tournamentSize, String individualType) 
    {
        this.numberOfGenerations = numberOfGenerations; // Number of iterations of the algorithm 
        this.mutationRate = mutationRate; //Individual mutation rate
        this.tournamentSize = tournamentSize; // Tournament size for selection
        this.actionMutationRate = actionMutationRate; //Mutation rate for rule actions
        this.individualType = individualType; // Either simple pittsburgh all Baldwinian pittsburgh
        this.populationSize = populationSize; // The total size of the population 

        selection = new SelectionMethod(); // Used for performing selection
        crossover = new Crossover(crossoverRate); //Used for performing crossover
        configuration = new Configuration(); // Contains the learning data
        fitness = new FitnessCalculator(configuration); //Used for calculating individual fitness
        
        if(this instanceof DarwinianAlgorithm || this instanceof LamarckianAlgorithm)
            population = new Population(populationSize, numberOfRules);
        //Separate constructor for Baldwinian individuals with the max plasticity rate
        else if(this instanceof BaldwinianAlgorithm)
            population = new Population(populationSize, numberOfRules, 100);


    }
    
    
    public abstract void execute();
    
    
    public Population getPopulation()
    {
        return population;
    }

    public void localSearch(AbstractIndividual individual)
    {
            hillClimber((PittsburghIndividual)individual);
    }
    protected void instantiateIndividualToKeep()
    {
        switch (individualType) 
        {
                case "Pittsburgh":
                    individualToKeep = new PittsburghIndividual(population.getFitest().getGenes(), numberOfRules);
                    break;
                case "Baldwinian":
                    individualToKeep = new BaldwinianIndividual(population.getFitest().getGenes(), numberOfRules);
                    int plasticity = ((BaldwinianIndividual)population.getFitest()).getPlasticity();
                    ((BaldwinianIndividual)individualToKeep).setPlasticity(plasticity);
                    break;
        }
            
            individualToKeep.setFitness(population.getFitest().getFitness());
            individualToKeep.setConstraintViolations(population.getFitest().getConstraintViolations());
    }

        
    private void hillClimber(PittsburghIndividual individual)
    {
        double originalFitness = individual.getFitness();
        final char[] originalGenes = individual.getGenes();
        int count = 0;
        int ruleLength = ((PittsburghIndividual)individual).getRuleLength();
        //Loop over each gene of the individual 
        for(int index = 0; index < individual.getGenes().length; index++)
        {
            count ++;
            //Action bits should not be modified
            if(count == ruleLength - 1)
                continue;
            
            if(count == ruleLength)
            {
                count = 0;
                continue;
            }
            //Modify the gene to evaluate neighbour 
            char[] modifiedGenes = flipSingleBit(individual.getGenes(), index);

            individual.setGenes(modifiedGenes);
            //Calculate neighbour's fitness
            fitness.calculateFitness(individual);

            if(individual.getFitness() > originalFitness)
            {
               individual.setGenes(modifiedGenes); //Neighbour replaces current solution
               break;
            }
            else
            {   //Neighbour is not an improvement
                individual.setGenes(originalGenes); //Individual doesn't change, evaluate next neighbour
                individual.setFitness(originalFitness);
            }
        }
    }
    
    
    private char[] flipSingleBit(char[] genesToModify, int index)
    {
        int rand = random.nextInt(2);
        
        switch(genesToModify[index])
        {
            case '0':
            {
                if(rand == 0)
                    genesToModify[index] = '1';
                else
                    genesToModify[index] = '#';
                break;
            }
            case '1':
            {
                if(rand == 0)
                    genesToModify[index] = '0';
                else
                    genesToModify[index] = '#';
                break;
            }
            case '#':
            {
                if(rand == 0)
                    genesToModify[index] = '0';
                else
                    genesToModify[index] = '1';
                break;
            }
        }  
            
        return genesToModify;
    }
    
    public void reset()
    {
        switch (individualType) {
            case "Pittsburgh":
                population = new Population(populationSize, numberOfRules);
                break;
            case "Baldwinian":
                population = new Population(populationSize, numberOfRules, 100);
                break;
        }
    }
    
}
