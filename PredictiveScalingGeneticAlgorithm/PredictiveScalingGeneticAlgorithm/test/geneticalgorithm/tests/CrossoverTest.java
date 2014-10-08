/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.tests;

import geneticalgorithm.crossover.Crossover;
import geneticalgorithm.objects.PittsburghIndividual;
import geneticalgorithm.objects.Population;
import org.junit.Test;

/**
 *
 * @author thomasborel
 */
public class CrossoverTest 
{

    @Test
    public void TestCrossover()
    {
        double total = 0;

        for(int index = 0; index < 100; index ++)
        {
            char[] genesOne = "1011100110".toCharArray();
            char[] genesTwo = "0100011001".toCharArray();

            Population population = new Population();
            PittsburghIndividual individualOne = new PittsburghIndividual(genesOne, 1);
            PittsburghIndividual individualTwo = new PittsburghIndividual(genesOne, 1);

            population.add(individualOne);
            population.add(individualTwo);

            Crossover crossover = new Crossover(50);

            crossover.uniform(population);

            double distance = calculateHammingDistance(population.get(0).getGenes(), genesOne);
 
            total += distance * 10;
        }
        
        System.out.println("Average Crossover Percentage "  + total/100);
    }
    
    private int calculateHammingDistance(char[] first, char[] second)
    {
        if(first.length != second.length)
            return Integer.MAX_VALUE;
        
        int hammingDistance = 0;
        
        for(int index = 0; index < first.length; index ++)
        {
            if(first[index] == '#'| second[index] == '#')
                continue;
            
            if(first[index] != second[index])
                hammingDistance ++;
        }
        
        return hammingDistance;
    }
}