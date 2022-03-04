package task.plateau.model;

import org.junit.Test;

public class PlateauTest {

    @Test(expected = InstantiationException.class)
    public void whenXTooSmallThenThrowInstantiationException() throws Exception{
        new Plateau(-1,0);
    }

    @Test(expected = InstantiationException.class)
    public void whenYTooSmallThenThrowInstantiationException() throws Exception{
        new Plateau(0,-1);
    }

    @Test(expected = InstantiationException.class)
    public void whenYTooSmallThenThrowInstantiationExceptionNANA() throws Exception{
        new Plateau(0,-1);
    }
}
