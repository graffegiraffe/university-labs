package by.rublevskaya.grapheditor.show;

import by.rublevskaya.grapheditor.graph.UndirectedGraph;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class GraphPrinterTest {

    private UndirectedGraph<String> graph;

    @Before
    public void setUp() {
        graph = new UndirectedGraph<>();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
    }

    @Test
    public void printGraph() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        GraphPrinter.printGraph(ps, graph);
        String output = baos.toString().trim().replace("\r\n", "\n");
        String expectedOutput = "Vertex: A\n" +
                "Vertex: B\n" +
                "Edge: (A, B)";
        if (!expectedOutput.equals(output)) {
            System.out.println("Expected Output:\n" + expectedOutput);
            System.out.println("Actual Output:\n" + output);
        }
        assertEquals(expectedOutput, output);
    }
}