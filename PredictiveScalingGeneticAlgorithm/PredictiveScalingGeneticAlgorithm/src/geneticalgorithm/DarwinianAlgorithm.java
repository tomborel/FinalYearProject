/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import geneticalgorithm.encoding.GrayEncoder;
import geneticalgorithm.objects.AbstractIndividual;

/**
 *  ORIGINAL GENETIC ALGORITHM
 * 
 * @author thomasborel
 */
public class DarwinianAlgorithm extends AbstractAlgorithm
{
    public DarwinianAlgorithm(int numberOfGenerations, int crossoverRate, double mutationRate, double actionMutationRate, int populationSize,
            int tournamentSize, String individualType) {
        
        super(numberOfGenerations, crossoverRate, mutationRate, actionMutationRate, populationSize, tournamentSize, individualType);

    }

    @Override
    public void execute()
    {   // Loop for specified generations 
        
        System.out.println("Running Darwinian Algorithm");
        
        for (int index = 0; index < numberOfGenerations; index++)
        {   // calculate each individual's fitness
            for (AbstractIndividual individual: population) 
                fitness.calculateFitness(individual);

            while (currentGeneration.size() < population.size())
            {   //Select population to reproduce using tournament selection
                AbstractIndividual individualToAdd = selection.tournamentSelection(population, tournamentSize);
                currentGeneration.add(individualToAdd);
            }
            instantiateIndividualToKeep();  //Elitism 

            for (AbstractIndividual individualToEncode : currentGeneration) 
            {   //Gray encode individuals before reproduction
                char[] encoded = GrayEncoder.encode(individualToEncode.getGenes());
                individualToEncode.setGenes(encoded);
            }
            //Perform the crossover
            crossover.uniform(currentGeneration);

            for (AbstractIndividual individualToEncode : currentGeneration) 
            {   //Individual mutation
                individualToEncode.mutation(mutationRate);
                individualToEncode.actionMutation(actionMutationRate);
                //Decode each individual
                char[] decoded = GrayEncoder.decode(individualToEncode.getGenes());
                individualToEncode.setGenes(decoded);
            }

            double averageFitness = currentGeneration.getTotalFitness() / (double) currentGeneration.getPopulationSize();
            //Print the average fitness and best fitness of the generation
            System.out.println("Generation: " + (index + 1) + " Mean Fitness: [" + averageFitness + "] Best Fitness: [" + population.getFitest().getFitness() + "]");

            if(index == numberOfGenerations - 1)
                System.out.println(currentGeneration.getFitest().toString());
            
            population.clear();
            //The new generation replaces the old one
            for (AbstractIndividual individual: currentGeneration)
                population.add(individual);
            
            population.remove(population.getLeastFit());
            population.add(individualToKeep);      
            currentGeneration.clear();
        }
    }
}
