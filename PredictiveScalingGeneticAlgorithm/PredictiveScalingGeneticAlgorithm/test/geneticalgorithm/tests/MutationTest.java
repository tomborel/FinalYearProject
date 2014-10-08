/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.tests;

import geneticalgorithm.objects.PittsburghIndividual;
import org.junit.Test;

/**
 *
 * @author thomasborel
 */
public class MutationTest
{
    @Test
    public void testMutation()
    {
        int amountOfMutations = 0;
        
        for(int index = 0; index < 100; index ++)
        {
            String genes = "110100011111010001111101000111110100011111010001111101000111110100011111010001111101000111";
        
            PittsburghIndividual individual = new PittsburghIndividual(genes.toCharArray(),4);

            individual.mutation(0.1);
            
            if(calculateHammingDistance(genes.toCharArray(), individual.getGenes()) > 0)
                amountOfMutations ++;
            
        }
        
        System.out.println("Total Mutations: " + amountOfMutations);
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
