/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import geneticalgorithm.encoding.GrayEncoder;
import geneticalgorithm.objects.AbstractIndividual;

/**
 *
 *  LAMARCKIAN MEMETIC ALGORITHM
 * 
 * @author thomasborel
 */
public class LamarckianAlgorithm extends AbstractAlgorithm
{
    private int localSearchRate = 90;
    
    public LamarckianAlgorithm(int numberOfGenerations, int crossoverRate, double mutationRate, double actionMutationRate, int populationSize,
            int tournamentSize, String individualType) {
        
        super(numberOfGenerations, crossoverRate, mutationRate, actionMutationRate, populationSize, tournamentSize, individualType);

    }

    @Override
    public void execute()
    {
        System.out.println("Running Lamarckian Algorithm");
        
        for (int index = 0; index < numberOfGenerations; index++)
        {
            for (int individualIndex = 0; individualIndex < population.size(); individualIndex++) 
            {       //Perform local search on 90% of the population
                    if(random.nextInt(100) < localSearchRate && !population.get(individualIndex).equals(population.getFitest()))
                        localSearch(population.get(individualIndex));
                    //If individual doesn't adapt calculate fitness normally
                    else
                        fitness.calculateFitness(population.get(individualIndex));
            }

            while (currentGeneration.size() < population.size())
            {
                AbstractIndividual individualToAdd = selection.tournamentSelection(population, tournamentSize);
                currentGeneration.add(individualToAdd);
            }
            
            instantiateIndividualToKeep(); //ELitism 

            for (AbstractIndividual individualToEncode : currentGeneration) 
            {
                char[] decodedGenes = GrayEncoder.encode(individualToEncode.getGenes());
                individualToEncode.setGenes(decodedGenes);
            }

            //Crossover
            crossover.uniform(currentGeneration);

            for (AbstractIndividual individualToEncode : currentGeneration) 
            {
                //Mutation
                individualToEncode.mutation(mutationRate);
                individualToEncode.actionMutation(actionMutationRate);
                
                //Decode from Gray
                char[] encodedGenes = GrayEncoder.decode(individualToEncode.getGenes());
                individualToEncode.setGenes(encodedGenes);
            }

            double averageFitness = currentGeneration.getTotalFitness() / (double) currentGeneration.getPopulationSize();

            //Ouput some stats
            System.out.println("Generation: " + (index + 1) + " Mean Fitness: [" + averageFitness + "] Best Fitness: [" + population.getFitest().getFitness() + "]");

            if(index == numberOfGenerations - 1)
                System.out.println(currentGeneration.getFitest().toString());
            
            population.clear();
            
            //The old generation replaces the new one
            for (AbstractIndividual individual: currentGeneration)
            {
                population.add(individual);
            }
            
            population.remove(population.getLeastFit());
            population.add(individualToKeep);
            
            currentGeneration.clear();
        }
    }
}
