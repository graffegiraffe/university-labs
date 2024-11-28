package by.rublevskaya.grapheditor;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class RunnerTest {

    @Test
    public void testMain() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream testOut = new PrintStream(baos);
        System.setOut(testOut);
        Runner.main(new String[]{});
        System.setOut(originalOut);
        String output = baos.toString().trim().replace("\r\n", "\n");
        String expectedOutput = "Vertices: [A, B, C]\n" +
                "Edges: [(A, B), (A, C)]\n" +
                "Contains vertex A: true\n" +
                "Contains edge A-B: true\n" +
                "Vertex: A\n" +
                "Vertex: B\n" +
                "Vertex: C\n" +
                "Edge: (A, B)\n" +
                "Edge: (A, C)\n" +
                "\nIterating vertices:\n" +
                "Vertex: A\n" +
                "Vertex: B\n" +
                "Vertex: C\n" +
                "\nIterating edges:\n" +
                "Edge: (A, B)\n" +
                "Edge: (A, C)\n" +
                "\nIterating vertices in reverse order:\n" +
                "Vertex: C\n" +
                "Vertex: B\n" +
                "Vertex: A\n" +
                "\nIterating edges in reverse order:\n" +
                "Edge: (A, C)\n" +
                "Edge: (A, B)\n" +
                "\nIterating adjacent vertices of 'A':\n" +
                "Adjacent Vertex: B\n" +
                "Adjacent Vertex: C\n" +
                "\nIterating adjacent vertices of 'A' in reverse order:\n" +
                "Adjacent Vertex: C\n" +
                "Adjacent Vertex: B\n" +
                "\nRemoving vertex 'A':\n" +
                "Vertex: B\n" +
                "Vertex: C\n" +
                "\nRemoving edge 'B-C':\n" +
                "Edge: []";
        if (!expectedOutput.equals(output)) {
            System.out.println("Expected Output:\n" + expectedOutput);
            System.out.println("Actual Output:\n" + output);
        }
        assertEquals(expectedOutput, output);
    }
}