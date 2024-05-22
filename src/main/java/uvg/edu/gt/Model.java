package uvg.edu.gt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Esta clase se encarga del funcionamiento interno de la aplicacion, lee el archivo y hace uso de DirectedGraph
 * @author Jose Merida
 * @since 21-05-2024
 * @version 1.0
 */
public class Model {
    private DirectedGraph graph;
    public Model(){
        createGraph();
        readFile();
    }
    /**
     * Retorna el centro del grafo
     * @return el centro del grafo
     */
    public String getCenter(){
        return graph.getCenter();
    }
    /**
     * Remueve una ruta del grafo
     * @param from ciudad de partida
     * @param to ciudad de destino
     */
    public void blockRoute(String from, String to){
        graph.removeEdge(from, to);
    }
    /**
     * Regresa un String detallando el costo y la ruta de una ciudad a otra
     * @param startNode ciudad de partida
     * @param endNode ciudad de destino
     * @return un String con los detalles concatenados
     */
    public String getPath(String startNode, String endNode){
        ArrayList<String> outList = graph.getPath(startNode,endNode);
        if (outList != null) {
            String out = "Costo: ";
            out += outList.get(0) + " | " + outList.get(1);
            for (int i = 2; i < outList.size(); i++) {
                out += " -> " + outList.get(i);
            }
            return out;
        } else{
            return "No hay ruta";
        }
    }
    /**
     * Agrega una ruta
     * @param fromNode ciudad de partida
     * @param toNode ciudad de destino
     * @param distance distancia entre ciudades
     */
    public void addPath(String fromNode, String toNode, int distance){
        graph.addEdge(fromNode,toNode, distance);
    }
    /**
     * Crea el grafo con la cantidad de nodos que se encuentran en el archivo txt
     */
    public void createGraph(){
        String line;
        ArrayList<String> cityList = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader("guategrafo.txt"))){
            while ((line = br.readLine()) != null){
                String[] separated = line.split(" ");
                String from = separated[0];
                String to = separated[1];
                if (!cityList.contains(from)){
                    cityList.add(from);
                }
                if (!cityList.contains(to)){
                    cityList.add(to);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        graph = new DirectedGraph(cityList.size());
    }
    /**
     * Lee el archivo y carga los datos al grafo
     */
    public void readFile(){
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader("guategrafo.txt"))){
            while ((line = br.readLine()) != null){
                String[] separated = line.split(" ");
                String from = separated[0];
                String to = separated[1];
                int distance = Integer.parseInt(separated[2]);
                graph.addNode(from);
                graph.addNode(to);
                graph.addEdge(from, to, distance);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}