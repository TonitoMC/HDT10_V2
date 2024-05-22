package uvg.edu.gt;

import java.util.*;

public class DirectedGraph {
    private int[][] adjacencyMatrix;
    private int[][] floydMatrix;
    private int[][] routeMatrix;
    private ArrayList<String> nodeList;

    public DirectedGraph(int numNodes){
        adjacencyMatrix = new int[numNodes][numNodes];
        for (int i = 0; i < numNodes; i++){
            Arrays.fill(adjacencyMatrix[i], Integer.MAX_VALUE);
            adjacencyMatrix[i][i] = 0;
        }
        nodeList = new ArrayList<String>();
    }
    public ArrayList<String> getNodeList(){
        return nodeList;
    }
    public int[][] getAdjacencyMatrix(){
        return adjacencyMatrix;
    }
    public void addNode(String node){
        if (!nodeList.contains(node)){
            nodeList.add(node);
        }
    }
    public void addEdge(String fromNode, String toNode, int weight){
        int fromIndex = nodeList.indexOf(fromNode);
        int toIndex = nodeList.indexOf(toNode);
        if (fromIndex != -1 && toIndex != -1){
            adjacencyMatrix[fromIndex][toIndex] = weight;
        }
        updateFloyd();
    }
    public void removeEdge(String fromNode, String toNode){
        int fromIndex = nodeList.indexOf(fromNode);
        int toIndex = nodeList.indexOf(toNode);
        if (fromIndex != -1 && toIndex != -1){
            adjacencyMatrix[fromIndex][toIndex] = Integer.MAX_VALUE;
        }
        updateFloyd();
    }
    private void updateFloyd(){
        int numNodes = nodeList.size();
        floydMatrix = new int[numNodes][numNodes];
        routeMatrix = new int[numNodes][numNodes];
        for (int i = 0; i < numNodes; i++){
            for (int j = 0; j < numNodes; j++){
                floydMatrix[i][j] = adjacencyMatrix[i][j];
            }
        }
        //Recorre todos los nodos tratandolos como nodos intermedios
        for (int k = 0; k < numNodes; k++){
            for (int i = 0; i < numNodes; i++){
                for (int j = 0; j < numNodes; j++){
                    if (floydMatrix[i][k] != Integer.MAX_VALUE && floydMatrix[k][j] != Integer.MAX_VALUE &&
                            floydMatrix[i][k] + floydMatrix[k][j]< floydMatrix[i][j]){
                        floydMatrix[i][j] = floydMatrix[i][k] + floydMatrix[k][j];
                        routeMatrix[i][j] = k;
                    }
                }
            }
        }
    }
    public int[][] getFloydMatrix(){
        return floydMatrix;
    }
    public String getPath(String startNode, String endNode){
        int fromNode = nodeList.indexOf(startNode);
        int toNode = nodeList.indexOf(endNode);
        ArrayList<String> path = new ArrayList<>();
        if (floydMatrix[fromNode][toNode] == Integer.MAX_VALUE){
            return "No hay ruta";
        }
        return "1";
    }
    private void pathRecursive(int fromNode, int toNode, ArrayList<String> path){
        int intermediate = routeMatrix[fromNode][toNode];

    }
    public String getCenter(){
        int numNodes = nodeList.size();
        int nodePosition = 0;
        int lowest = Integer.MAX_VALUE;
        for (int i = 0; i < numNodes; i++){
            int highest = 0;
            for (int j = 0; j < numNodes; j++){
                int distance = floydMatrix[i][j];
                if (distance != Integer.MAX_VALUE && distance > highest){
                    highest = distance;
                }
            }
            if (highest < lowest && highest != 0){
                lowest = highest;
                nodePosition = i;
            }
        }
        return nodeList.get(nodePosition);
    }
}