/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.selection;

/**
 *
 * @author thomasborel
 */
public enum SelectionType 
{
    Roulette(0),
    Tournament(1);
    
    private int selectionIndex;

    private SelectionType(int selectionIndex) 
    {
        this.selectionIndex = selectionIndex;
    }
}
