/*
 * Vertex.java
 * Igor Podsechin
 * The class which defines a vertex
 */

import java.util.*;

public class Vertex {
    //Growable array of edges
    Vector<Edge> edges = new Vector<Edge>();

    public void addEdge(int vertex, int weight) {
        edges.add(new Edge(vertex, weight));
    }

    public Edge getEdge(int index) {
        return edges.get(index);
    }

    public Vector printEdges() {
        return edges;
    }

    public int getNumberOfEdges() {
        return edges.size();
    }
    //find a vertices smallest edge by looping through all of them
    //Used in Boruvka's algorithm
    public Edge getSmallestEdge() {
        int smallest = Integer.MAX_VALUE;
        int indexSmallest = -1;
        for (int i = 0; i < edges.size(); i++) {
            if (getEdge(i).getWeight() < smallest) {
                smallest = getEdge(i).getWeight();
                indexSmallest = i;
            }
        }
        return getEdge(indexSmallest);
    }
    //Finds smallest available edge that is not in the argument array, otherwise returns null
    //Used in Prim's and Boruvka's Algorithms
    public Edge getSmallestFreeEdge(int[] arrayOfTakenVertices, int itemsInArray) {
        int smallest = Integer.MAX_VALUE;
        int indexSmallest = -1;
        boolean taken = false;
        //We loop through the edges that the vertex is connected to
        for (int i = 0; i < edges.size(); i++) {
            //Check if the vertex at the end of the edge is part of the argument array
            for (int j = 0; j < itemsInArray; j++) {
                if (arrayOfTakenVertices[j] == getEdge(i).getVertex()) {
                    taken = true;
                }
            }
            //If it is not part of the array and also has the smallest weight then save its index
            if (getEdge(i).getWeight() <= smallest && taken == false) {
                smallest = getEdge(i).getWeight();
                indexSmallest = i;
            }
            taken = false;
        }
        //Return its index, but if no such vertex exists then returns null
        if (indexSmallest != -1) {
            return getEdge(indexSmallest);
        }
        return null;
    }
}