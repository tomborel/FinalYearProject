/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.tests;

import geneticalgorithm.encoding.GrayEncoder;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author thomasborel
 */
public class GrayEncodingTest {
    
    public GrayEncodingTest() {
    }
    
    @Test
    public void TestEncode()
    {
        assertEquals("1111".toCharArray(), GrayEncoder.encode("1010".toCharArray()));
        assertEquals("0100".toCharArray(), GrayEncoder.encode("0111".toCharArray()));
        assertEquals("1011".toCharArray(), GrayEncoder.encode("1101".toCharArray()));
        assertEquals("1000".toCharArray(), GrayEncoder.encode("1111".toCharArray()));
        assertEquals("0101".toCharArray(), GrayEncoder.encode("0110".toCharArray()));
        assertEquals("0010".toCharArray(), GrayEncoder.encode("0011".toCharArray()));
        
        System.out.println(GrayEncoder.encode("00011".toCharArray()));
    }
    
    @Test
    public void TestDecode()
    {
        assertEquals("1010".toCharArray(), GrayEncoder.decode("1111".toCharArray()));
        assertEquals("0111".toCharArray(), GrayEncoder.decode("0100".toCharArray()));
        assertEquals("1101".toCharArray(), GrayEncoder.decode("1011".toCharArray()));
        assertEquals("1111".toCharArray(), GrayEncoder.decode("1000".toCharArray()));
        assertEquals("0110".toCharArray(), GrayEncoder.decode("0101".toCharArray()));
        assertEquals("0011".toCharArray(), GrayEncoder.decode("0010".toCharArray()));
    }
    
    @Test
    public void TestEncodeWithHash()
    {
        assertEquals(GrayEncoder.encode("0111###1101##".toCharArray()), "0100###1011##".toCharArray());
    }
    
    @Test
    public void TestDecodeWithHash()
    {
        assertEquals("0111###1101##".toCharArray(), GrayEncoder.decode("0100###1011##".toCharArray()));
    }
    
    @Test
    public void Test()
    {
        System.out.println(GrayEncoder.decode("00010".toCharArray()));
    }
}