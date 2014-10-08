/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.ttest;

import geneticalgorithm.AbstractAlgorithm;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tom
 */
public class TTest 
{
    private int numberOfIterations;
    private String[] results;
    private AbstractAlgorithm algorithm;

    public TTest(int numberOFIterations,AbstractAlgorithm algorithm) 
    {
        this.numberOfIterations = numberOFIterations;
        this.algorithm = algorithm;
        
        results = new String[numberOFIterations];
    }
    
    public void execute()
    {
        for(int index = 0; index < numberOfIterations; index ++)
        {
            algorithm.execute();
            results[index] = String.valueOf(algorithm.getPopulation().getFitest().getFitness());
            algorithm.reset();
        }
        
        saveToFile();
    }
    
    private void saveToFile()
    {
        try 
        {
            File file = new File(algorithm.getClass().getName() + ".txt");
            
            if(file.exists())
                file.delete();

            file.createNewFile();

            
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            for(int index = 0; index < results.length; index ++)
            {
                bufferedWriter.write(results[index]);
                bufferedWriter.newLine();
            }
            
            bufferedWriter.close();
            fileWriter.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(TTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
