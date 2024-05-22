package uvg.edu.gt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Model {
    private DirectedGraph graph;
    public Model(){
        createGraph();
        readFile();
    }
    public String getCenter(){
        return graph.getCenter();
    }
    public void addRoute(String from, String to, int distance){
        graph.addEdge(from, to, distance);
    }
    public void getMatrix(){
        System.out.println(Arrays.deepToString(graph.getAdjacencyMatrix()));
        System.out.println(Arrays.deepToString(graph.getFloydMatrix()));
    }
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