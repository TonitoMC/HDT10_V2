package uvg.edu.gt;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;

public class DirectedGraphTest extends TestCase {

    public void testGetNodeList() {
    }

    public void testGetAdjacencyMatrix() {
    }

    public void testAddNode() {
        DirectedGraph graph = new DirectedGraph(2);
        graph.addNode("Guatemala");
        ArrayList<String> nodeList = graph.getNodeList();
        assertEquals(nodeList.size(), 1);
        assertEquals(nodeList.get(0), "Guatemala");
    }

    public void testAddEdge() {
        DirectedGraph graph = new DirectedGraph(3);
        graph.addNode("Guatemala");
        graph.addNode("Peten");
        graph.addNode("Zacapa");
        graph.addEdge("Guatemala", "Peten", 300);
        graph.addEdge("Guatemala", "Zacapa", 400);
        graph.addEdge("Peten", "Zacapa", 200);
        int[][] matrix = graph.getAdjacencyMatrix();
        assertEquals(matrix[0][1],300);
        assertEquals(matrix[0][2],400);
        assertEquals(matrix[1][2],200);
    }

    public void testRemoveEdge() {
        DirectedGraph graph = new DirectedGraph(3);
        graph.addNode("Guatemala");
        graph.addNode("Peten");
        graph.addNode("Zacapa");
        graph.addEdge("Guatemala", "Peten", 300);
        graph.addEdge("Guatemala", "Zacapa", 400);
        graph.addEdge("Peten", "Zacapa", 200);
        graph.removeEdge("Peten", "Zacapa");
        int[][] matrix = graph.getAdjacencyMatrix();
        assertEquals(matrix[1][2],Integer.MAX_VALUE);
    }
    public void testFloydMatrix(){
        DirectedGraph graph = new DirectedGraph(3);
        graph.addNode("Guatemala");
        graph.addNode("Peten");
        graph.addNode("Zacapa");
        graph.addEdge("Guatemala", "Peten", 300);
        graph.addEdge("Guatemala", "Zacapa", 800);
        graph.addEdge("Peten", "Zacapa", 200);
        int[][] floydMatrix = graph.getFloydMatrix();
        assertEquals(floydMatrix[0][1], 300);
        assertEquals(floydMatrix[0][2], 500);
        assertEquals(floydMatrix[1][2], 200);
    }
}