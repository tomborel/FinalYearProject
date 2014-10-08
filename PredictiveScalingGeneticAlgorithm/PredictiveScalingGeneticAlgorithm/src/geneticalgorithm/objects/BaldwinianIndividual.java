/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.objects;

/**
 *
 * @author thomasborel
 */
public class BaldwinianIndividual extends PittsburghIndividual
{
    private int plasticity = 0;
    private char[] genesToPassOn;

    public BaldwinianIndividual(int numberOfRules, int plasticity)
    {
        super(numberOfRules);
        this.plasticity = plasticity;
    }

    public BaldwinianIndividual(char[] genes, int numberOfRules) 
    {
        super(genes, numberOfRules);
    }
    
    public int getPlasticity() 
    {
        return plasticity;
    }

    public void setPlasticity(int plasticity)
    {
        this.plasticity = plasticity;
    }

    public char[] getGenesToPassOn() 
    {
        return genesToPassOn;
    }

    public void setGenesToPassOn(char[] genesToPassOn) 
    {
        this.genesToPassOn = genesToPassOn;
    }
   
    public void plasticityMutation(double mutationRate, int rate)
    {
        if(randomGenerator.nextDouble() > mutationRate)
            return;
        
        int value = randomGenerator.nextInt(rate);
        
        int rand = randomGenerator.nextInt(2);
        
        if(rand == 0 && plasticity - value > 0)
            plasticity = plasticity - value;
        else if(rand == 1 && plasticity + value < 100)
            plasticity = plasticity + value;
    }
    
}
