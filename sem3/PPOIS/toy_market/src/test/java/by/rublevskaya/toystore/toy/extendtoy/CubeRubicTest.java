package by.rublevskaya.toystore.toy.extendtoy;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class CubeRubicTest {

    @Test
    public void testToString() {
        CubeRubic cubeRubic = new CubeRubic("Test Cube");
        String result = cubeRubic.toString();
        String expected = "Кубик рубик { имя ='Test Cube', уровень заряда =0}";
        assertEquals(expected, result);
    }

    @Test
    public void testPlayCubic() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        CubeRubic cubeRubic = new CubeRubic("Test Cube");
        cubeRubic.playCubic();
        String expectedOutput = "Ты играешь с кубиком\n";
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(originalOut);
    }

}