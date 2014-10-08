/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import geneticalgorithm.action.ActionEngine;
import geneticalgorithm.database.RuleEngine;
import java.util.Scanner;

/**
 *
 * @author Tom
 */
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        System.out.println("Generating Rule Base...");
        
        BaldwinianAlgorithm algorithm = new BaldwinianAlgorithm(2000, 90, 0.0005, 0.0001, 100, 10,  "Baldwinian");
        
        algorithm.execute();
        
        while(algorithm.getPopulation().getFitest().getFitness() != 40)
        {
            algorithm = new BaldwinianAlgorithm(2000, 90, 0.0005, 0.0001 ,100, 10, "Baldwinian");
            algorithm.execute();
        }
        
        RuleEngine engine = new RuleEngine();
        
        engine.writeRules(algorithm.getPopulation().getFitest());
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter input: ");
        
        String input = scanner.nextLine();
        
        String returnedAction = engine.selectAction(input);
        
        ActionEngine actionEngine = new ActionEngine();
        
        System.out.println(actionEngine.apiCall(returnedAction));
    }
}
