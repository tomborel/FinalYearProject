/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import geneticalgorithm.encoding.GrayEncoder;
import geneticalgorithm.objects.AbstractIndividual;
import geneticalgorithm.objects.BaldwinianIndividual;

/**
 *  BALDWINIAN MEMETIC ALGORITHM
 * 
 * @author thomasborel
 */
public class BaldwinianAlgorithm extends AbstractAlgorithm
{
    private int plasticityRate = 10;

    public BaldwinianAlgorithm(int numberOfGenerations, int crossoverRate, double mutationRate, double actionMutationRate, int populationSize,
            int tournamentSize, String individualType) 
    {
        
        super(numberOfGenerations, crossoverRate, mutationRate, actionMutationRate, populationSize, tournamentSize, individualType);

    }

    
@Override
    public void execute()
    {  
        System.out.println("Running Baldwinian Algorithm");
        
        for (int index = 0; index < numberOfGenerations; index++)
        {
            for (AbstractIndividual individual: population) 
            {
                BaldwinianIndividual individualToUse = (BaldwinianIndividual) individual;
                
                char[] genes = individualToUse.getGenes();
                //Individual adaption based on plasticity rate
                if(random.nextInt(100) <= individualToUse.getPlasticity() && !individualToUse.equals(population.getFitest()))
                    localSearch(individualToUse);
                //If the individual doesn't adapt calculate fitness normally
                else
                    fitness.calculateFitness(individualToUse);
                //Adapted genes are not passed on to the next generation
                individualToUse.setGenesToPassOn(genes);
            }

            while (currentGeneration.size() < population.size())
            {
                AbstractIndividual individualToAdd = selection.tournamentSelection(population, tournamentSize);
                currentGeneration.add(individualToAdd);
            }
            
            instantiateIndividualToKeep();

            for (AbstractIndividual individualToEncode : currentGeneration) 
            {
                BaldwinianIndividual indiidual = (BaldwinianIndividual) individualToEncode;
                char[] tmpGenes = indiidual.getGenes();
                indiidual.setGenes(indiidual.getGenesToPassOn());
                indiidual.setGenesToPassOn(tmpGenes); // Don't pass on adapted genes
                
                char[] decodedGenes = GrayEncoder.encode(indiidual.getGenes());
                indiidual.setGenes(decodedGenes);
            }

            crossover.baldwinianUniform(currentGeneration);

            for (AbstractIndividual individualToEncode : currentGeneration) 
            {
                BaldwinianIndividual indiidual = (BaldwinianIndividual) individualToEncode;
                
                indiidual.mutation(mutationRate);
                //Mutate the individual's plasticity rate
                ((BaldwinianIndividual)indiidual).plasticityMutation(mutationRate, plasticityRate);
                
                char[] tmpGenes = indiidual.getGenes();
                indiidual.setGenes(indiidual.getGenesToPassOn());
                indiidual.setGenesToPassOn(tmpGenes);
                //Decode from Gray
                char[] encodedGenes = GrayEncoder.decode(indiidual.getGenes());
                indiidual.setGenes(encodedGenes);
            }

            double averageFitness = currentGeneration.getTotalFitness() / (double) currentGeneration.getPopulationSize();
            //Print stats
            System.out.println("Generation: " + (index + 1) + " Mean Fitness: [" + averageFitness + "] Best Fitness: [" + population.getFitest().getFitness() + "]");
            
            if(index == numberOfGenerations - 1 || population.getFitest().getFitness() == 40)
            {
                //Print the optimal individual
                System.out.println(currentGeneration.getFitest().toString());
                break;
            }
            
            //The new generation replaces the old one
            population.clear();
            
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
