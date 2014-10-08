/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.objects;

/**
 *
 * @author thomasborel
 */
public class PittsburghIndividual extends AbstractIndividual
{
    private int numberOfRules;
    private int ruleLength = 18;

    public PittsburghIndividual(int numberOfRules) 
    {
        this.numberOfRules = numberOfRules;
        genes = generateRandomGenes(numberOfRules * getRuleLength());
    }

    public PittsburghIndividual(char[] genes, int numberOfRules)
    {
        super(genes);
        this.numberOfRules = numberOfRules;
    }
    
    public int getRuleLength()
    {
        return ruleLength;
    }

    @Override
    public void mutation(double mutationRate) 
    {
        int count = 0;
        
        for(int index = 0; index < getGenes().length; index ++)
        {
            count ++;
            //Algorithm does not mutate action bits
            if(count == getRuleLength() - 1)
                continue;
            //Reset the counter if second action bit is reached
            if(count == getRuleLength())
            {
                count = 0;
                continue;
            }
            //Each gene has a chance of mutating
            if(randomGenerator.nextDouble() > mutationRate)
                continue;
            
            int rand = randomGenerator.nextInt(2);
            //Perform the mutation based on rand variable
            switch(genes[index])
            {
                case '0':
                    if(rand == 0)
                        genes[index] = '1';
                    else
                        genes[index] = '#';
                    break;
                    
                case '1':
                    if(rand == 0)
                        genes[index] = '0';
                    else
                        genes[index] = '#';
                    break;
                    
                case '#':
                    if(rand == 0)
                        genes[index] = '0';
                    else
                        genes[index] = '1';
                    break;
            }
        }
    }

    @Override
    public void actionMutation(double actionMutationRate) 
    {
        //Mutation from actions bits which can not be generalised
        for(int index = getRuleLength() - 2; index < genes.length; index = index + getRuleLength())
        {
            if(genes[index] == '0')
                genes[index] = '1';
            else
                genes[index] = '0'; 

            index ++;

            if(genes[index] == '0')
                genes[index] = '1';
            else
                genes[index] = '0'; 

            index ++;
        
        } 
    }
    
    //Print the rules in a human readable format
    @Override
    public String toString()
    {
        String stringToPrint = "";
        int index = 0;
        
        while(index < getGenes().length)
        {
            int ruleIndex = 0;
            
            while(ruleIndex < getRuleLength())
            {
                stringToPrint += getGenes()[index];
                index++;
                ruleIndex++;
                
                if(ruleIndex == getRuleLength() - 2)
                    stringToPrint += " : ";
            }
            
            stringToPrint += "\n";
        }
        
        return stringToPrint;
    }
    
    @Override
    protected char[] generateRandomGenes(int phenotypeLength)
    {
        char[] genesToReturn = new char[phenotypeLength];
        int count = 0;
        
        for(int index = 0; index < phenotypeLength; index ++)
        {
            count++;
            
            int rand;
            
            if(count == getRuleLength() - 1)
                rand = randomGenerator.nextInt(2);
            
            else if(count == getRuleLength())
            {   //Action bits can't be generalised
                rand = randomGenerator.nextInt(2);
                count = 0;
            }
            
            else
               rand = randomGenerator.nextInt(3); 
            
            if(rand == 0)
                genesToReturn[index] = '0';
            else if(rand ==1)
                genesToReturn[index] = '1';
            else if(rand==2)
                genesToReturn[index] = '#';
            
        }
        
        return genesToReturn;
       
    }
}
