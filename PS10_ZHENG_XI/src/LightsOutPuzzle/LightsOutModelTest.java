package LightsOutPuzzle;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import java.util.Random;
import org.junit.Test;

/**
* JUnit tests for LightsOutModel
*/
public class LightsOutModelTest
{
   //test the constructor
    @Test
    public void LightsOutModelTest ()
    {
        LightsOutModel model = new LightsOutModel(5, 5);
        model. MakeLightsOn(0, 0);
        model.Toggle(0, 0);
       
        assertEquals(false, model.isManualSetupMode());
        model.setManualSetupMode(true);
        assertEquals(true, model.isManualSetupMode());
        model.setManualSetupMode(false);
        assertEquals(false, model.isManualSetupMode());
        model.newGame();
    }
    
    // test the light out exception
    @Test (expected = IllegalArgumentException.class)
    public void LightsOutModelTest1 ()
    {
        LightsOutModel model = new LightsOutModel(0, 5);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testMakeLighsOn()
    {
        LightsOutModel model = new LightsOutModel(5, 5);
        model.MakeLightsOn(0, -3);
    }
    
    
    @Test (expected = IllegalArgumentException.class)
    public void testtestMakeLighsOn2 ()
    {
        LightsOutModel model = new LightsOutModel(5, 5);
        model.MakeLightsOn(7, 0);
    }
    
    
    //test toggle
    @Test (expected = IllegalArgumentException.class)
    public void testToggle()
    {
        LightsOutModel model = new LightsOutModel(5, 5);
        model.Toggle(-1, 0);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void testToggleLight1 ()
    {
        LightsOutModel model = new LightsOutModel(5, 5);
        model.Toggle(0, 6);
    }
    
    
    //test getOccpuant
    @Test(expected = IllegalArgumentException.class)
    public void testGetOccupant1 ()
    { 
        LightsOutModel model = new LightsOutModel(5, 5);
        model.getOccupant(12, 7);
    }
    

    @Test(expected = IllegalArgumentException.class)
    public void testGetOccupant2 ()
    {
        LightsOutModel model = new LightsOutModel(5, 5);
        model.getOccupant(66, 13);
    }
    

    @Test(expected = IllegalArgumentException.class)
    public void testGetOccupant3 ()
    {
        LightsOutModel model = new LightsOutModel(5, 5);
        model.getOccupant(-11, 6);
    }
    
    

    @Test(expected = IllegalArgumentException.class)
    public void testGetOccupant4 ()
    {
        LightsOutModel model = new LightsOutModel(5, 5);
        model.getOccupant(6, -1);
    }
    
   
}

