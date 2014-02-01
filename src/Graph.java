/*
 * Graph.java
 * Igor Podsechin
 * The class which defines and holds a graph
 */

import java.io.*;
import java.util.*;

public class Graph {

    int x = 5000;
    //This array holds the vertices
    Vertex[] vertex = new Vertex[x];
    Vertex[] tree = new Vertex[x];
    //number of vertices
    int n = 0;
    //numer of edges
    int e = 0;
    
    //Contructor initalizes the vertices
    public Graph() {
        for (int j = 0; j < x; j++) {
            vertex[j] = new Vertex();
        }
    }

    public void addVertices(int v1, int v2, int weight) {
        //Here we assign the edges to vertices
        vertex[v1].addEdge(v2, weight);
        vertex[v2].addEdge(v1, weight);
        //The following lines are for checking for the largest vertice
        //index and assigning it to n.
        if (v1 > n) {
            n = v1;
        }
        if (v2 > n) {
            n = v2;
        }
    }
    
    //This function is for handling the input file
    public void readInput() {
        //Temporary variables for holding values
        int v1;
        int v2;
        int weight;
        StringTokenizer st;
        //Read the text file line by line
        try {
            //NOTICE: change the string to the correct directory on your system!
            BufferedReader in = new BufferedReader(new FileReader("input.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                e++;
                st = new StringTokenizer(str);
                v1 = Integer.parseInt(st.nextToken());
                v2 = Integer.parseInt(st.nextToken());
                weight = Integer.parseInt(st.nextToken());
                addVertices(v1, v2, weight);
            }
            in.close();
        } catch (IOException e) {
        }
    }
    
    //Outputs the graph as text
    public void printGraph() {
    //Iterate through the vertices
        for (int i = 1; i < n + 1; i++) {
            System.out.println("Vertex " + (i));
            //Iterate through the edges it is connected to
            for (int j = 0; j < vertex[i].getNumberOfEdges(); j++) {
                System.out.println("edge " + vertex[i].getEdge(j).getVertex() + ", w: "
                        + vertex[i].getEdge(j).getWeight());
            }
        }
    }

    //Returns an array of a non-adjacency list of dges that represents the graph
    public UnlinkedEdge[] getUnlinkedEdgeArray() {
        UnlinkedEdge[] edges = new UnlinkedEdge[e];
        int iterator = 0;
        int v1;
        int v2;
        int weight;
        StringTokenizer st;
        //Read the text file line by line
        try {
            BufferedReader in = new BufferedReader(new FileReader("input.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                st = new StringTokenizer(str);
                v1 = Integer.parseInt(st.nextToken());
                v2 = Integer.parseInt(st.nextToken());
                weight = Integer.parseInt(st.nextToken());
                edges[iterator++] = new UnlinkedEdge(v1, v2, weight);
            }
            in.close();
        } catch (IOException e) {
        }
        return edges;
    }

    public Vertex getVertex(int index) {
        return vertex[index];
    }

    public int getOrder() {
        return n;
    }

    public int getNumOfEdges() {
        return e;
    }
}
