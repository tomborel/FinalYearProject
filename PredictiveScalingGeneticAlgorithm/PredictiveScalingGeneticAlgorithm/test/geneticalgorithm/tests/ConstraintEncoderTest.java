/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.tests;

import geneticalgorithm.encoding.BinaryEncoder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thomasborel
 */
public class ConstraintEncoderTest {
    
    public ConstraintEncoderTest() {
    }
    
    @Test
    public void TestConstraintEncoder()
    {
        BinaryEncoder encoder = new BinaryEncoder(7, 24, 10, 3, 3, 3, 3);
        
        encoder.encode("Add");
    }
}