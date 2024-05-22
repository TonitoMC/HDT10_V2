package uvg.edu.gt;

import java.util.*;

/**
 * Esta clase se encarga de implementar un grafo dirijido implementando una matriz de adyacencia, el numero de
 * nodos se determina al momento de crearlo.
 * @author Jose Merida
 * @since 21-05-2024
 * @version 1.0
 */
public class DirectedGraph {
    private int[][] adjacencyMatrix;
    private int[][] floydMatrix;
    private int[][] routeMatrix;
    private ArrayList<String> nodeList;
    /**
     * Crea un nuevo grafo dirigido
     * @param numNodes el numero de nodos que tiene el grafo
     */
    public DirectedGraph(int numNodes){
        adjacencyMatrix = new int[numNodes][numNodes];
        for (int i = 0; i < numNodes; i++){
            Arrays.fill(adjacencyMatrix[i], Integer.MAX_VALUE);
            adjacencyMatrix[i][i] = 0;
        }
        nodeList = new ArrayList<String>();
    }
    /**
     * Getter para la lista de nodos
     * @return nodeList
     */
    public ArrayList<String> getNodeList(){
        return nodeList;
    }
    /**
     * Getter para la matriz de adyacencia
     * @return adjacencyMatrix
     */
    public int[][] getAdjacencyMatrix(){
        return adjacencyMatrix;
    }
    /**
     * Agrega un nuevo nodo
     * @param node el nombre del nodo por agregar
     */
    public void addNode(String node){
        if (!nodeList.contains(node)){
            nodeList.add(node);
        }
    }
    /**
     * Agrega una nueva ruta entre dos ciudades
     * @param fromNode ciudad de partida
     * @param toNode ciudad de destino
     * @param weight distancia entre ciudades
     */
    public void addEdge(String fromNode, String toNode, int weight){
        int fromIndex = nodeList.indexOf(fromNode);
        int toIndex = nodeList.indexOf(toNode);
        if (fromIndex != -1 && toIndex != -1){
            adjacencyMatrix[fromIndex][toIndex] = weight;
        }
        updateFloyd();
    }
    /**
     * Remueve una ruta entre dos ciudades
     * @param fromNode ciudad de partida
     * @param toNode ciudad de destino
     */
    public void removeEdge(String fromNode, String toNode){
        int fromIndex = nodeList.indexOf(fromNode);
        int toIndex = nodeList.indexOf(toNode);
        if (fromIndex != -1 && toIndex != -1){
            adjacencyMatrix[fromIndex][toIndex] = Integer.MAX_VALUE;
        }
        updateFloyd();
    }
    /**
     * Actualiza una matriz de adyacencia utilizando el algoritmo de Floyd
     */
    private void updateFloyd(){
        //Crea una matriz con las rutas y copia la matriz de adyacencia actual a floydMatrix
        int numNodes = nodeList.size();
        floydMatrix = new int[numNodes][numNodes];
        routeMatrix = new int[numNodes][numNodes];
        for (int i = 0; i < numNodes; i++){
            for (int j = 0; j < numNodes; j++){
                if (adjacencyMatrix[i][j] != Integer.MAX_VALUE){
                    routeMatrix[i][j] = i;
                }
            }
        }
        for (int i = 0; i < numNodes; i++){
            for (int j = 0; j < numNodes; j++){
                floydMatrix[i][j] = adjacencyMatrix[i][j];
            }
        }
        //Recorre todos los nodos tratandolos como nodos intermedios
        for (int k = 0; k < numNodes; k++){
            //Para todos las posibles salidas y destinos
            for (int i = 0; i < numNodes; i++){
                for (int j = 0; j < numNodes; j++){
                    //Si el valor utilizando el nodo actual como nodo intermedio es menor, se actualiza
                    if (floydMatrix[i][k] != Integer.MAX_VALUE && floydMatrix[k][j] != Integer.MAX_VALUE &&
                            floydMatrix[i][k] + floydMatrix[k][j]< floydMatrix[i][j]){
                        floydMatrix[i][j] = floydMatrix[i][k] + floydMatrix[k][j];
                        routeMatrix[i][j] = k;
                    }
                }
            }
        }
    }
    /**
     * Getter para la matriz luego de utilizar Floyd
     * @return floydMatrix
     */
    public int[][] getFloydMatrix(){
        return floydMatrix;
    }
    /**
     * Metodo para obtener la ruta seguida de un destino a otro
     * @param startNode ciudad de salida
     * @param endNode ciudad de destino
     * @return un ArrayList con los nodos visitados
     */
    public ArrayList<String> getPath(String startNode, String endNode){
        //Crea un nuevo arrayList
        int fromNode = nodeList.indexOf(startNode);
        int toNode = nodeList.indexOf(endNode);
        ArrayList<String> path = new ArrayList<>();
        path.add(Integer.toString(floydMatrix[fromNode][toNode]));
        path.add(startNode);
        //Regresa null si no hay un camino
        if (floydMatrix[fromNode][toNode] == Integer.MAX_VALUE){
            return null;
        }
        pathRecursive(fromNode, toNode, path);
        path.add(endNode);
        return path;
    }
    /**
     * Ayuda a obtener la ruta de manera recursiva
     * @param fromNode nodo de salida
     * @param toNode nodo objetivo
     * @param path el ArrayList al que se agregan los intermedios
     */
    private void pathRecursive(int fromNode, int toNode, ArrayList<String> path){
        int intermediate = routeMatrix[fromNode][toNode];
        if (intermediate != fromNode){
            pathRecursive(fromNode, intermediate, path);
            path.add(nodeList.get(intermediate));
        }
    }

    /**
     * Obtiene el centro del grafo
     * @return la ciudad central
     */
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